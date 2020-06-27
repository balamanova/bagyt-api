package com.kz.iitu.bagyt.service;

import com.kz.iitu.bagyt.exception.MethodNotAllowedException;
import com.kz.iitu.bagyt.model.University;
import com.kz.iitu.bagyt.model.User;
import com.kz.iitu.bagyt.model.enums.Role;
import com.kz.iitu.bagyt.model.enums.UserStatus;
import com.kz.iitu.bagyt.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class UserService {

    private static final String ACCOUNT_SID = "ACa570d4f7ead285ffd5b3fef3e54daa7b";
    private static final String AUTH_TOKEN = "1dde76b51df31ccdf5778d6a24eb8a90";
    private static final String TWILIO_NUMBER = "+15005550006";
    private static final String API_URL = "https://bagyt.herokuapp.com/rest/user/";

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UniversityService universityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void verifyMsisdn(String msisdn) {

        User savedUser = userRepository.findByMsisdn(msisdn);

        if (savedUser != null) {
            log.error("user already exists");
            throw new MethodNotAllowedException("user already exists");
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        int verificationCode = (int) (Math.random() * 9999);

        User user = new User();
        user.setMsisdn(msisdn);
        user.setUserStatus(UserStatus.NOT_VERIFIED);
        user.setShortCode(verificationCode);


        Message message = Message.creator(
                new PhoneNumber(msisdn),
                new PhoneNumber(TWILIO_NUMBER),
                "Verification code " + verificationCode)
                .create();
        log.info(message.getSid() + " : " + message.getStatus());
        userRepository.insert(user);

    }

    public void verifyCode(String msisdn, int verificationCode) {
        User savedUser = userRepository.findByMsisdn(msisdn);
        if (savedUser.getShortCode() != verificationCode) {
            log.error("verification code not valid");
            throw new MethodNotAllowedException("verification code not valid");
        }

        savedUser.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(savedUser);
    }

    public void createUser(User user) {
        User savedUser = userRepository.findByMsisdn(user.getMsisdn());
        if (savedUser.getUserStatus() != UserStatus.ACTIVE) {
            log.error("user not verified");
            throw new MethodNotAllowedException("user not verified");
        }
        user.setRole(Role.USER);
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.delete(savedUser);
        userRepository.save(user);
    }

    public void verifyEmail(User user) throws MessagingException {
        User savedUser = userRepository.findByEmail(user.getEmail());

        if (savedUser != null) {
            log.error("user already exists");
            throw new MethodNotAllowedException("user already exists");
        }
        String verificationPassword = generateRandomString();
        int verificationCode = (int) (Math.random() * 9999);
        user.setShortCode(verificationCode);
        user.setUserStatus(UserStatus.NOT_VERIFIED);
        user.setPassword(passwordEncoder.encode(verificationPassword));
        userRepository.insert(user);
        String message = "<html><body>" +
                "<p>Уважаемый(ая) "+ user.getName()+", вы были зарегистрированы как админ в университет" + user.getUniversity().getName() +".<p><br/>" +
                " <h5> Ваш пароль "+ verificationPassword + "</h5>" +
                "<p>Нажмите на <a href = " +API_URL + user.getEmail() + "/" + verificationCode + ">ссылку</a> чтобы активировать аккаунт  </p>" +
                "<br/><br/><p><b>ВАЖНО!!\n" +
                "Никому не говорите пароль. </b></p>" +
                "<br/><br/><p><i>При любых вопросах обращайтесь по почте\n" +
                "bagytkz@mail.ru </i></p>" +
                "<br/><br/><p>--\n" +
                "Bagyt Platform</p>" +
                "</body></html>";
        sendMessage(user.getEmail(), message);
    }

    public String generateRandomString() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
       String password = "";
        for (int i = 0; i < 5; i++) {
            int index= (int)(AlphaNumericString.length() * Math.random());
            password += AlphaNumericString.charAt(index);
        }

        return password;
    }

    public void sendMessage(String email, String messageContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Bagyt Authentication");
        helper.setText(messageContent, true);
        mailSender.send(message);
    }

    public void verifyUniversityAdmin(String email, int verificationCode) {
        User savedUser = userRepository.findByEmail(email);
        if(verificationCode != savedUser.getShortCode()) {
            throw new MethodNotAllowedException("verification code not valid");
        }
        savedUser.setEmail(email);
        savedUser.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(savedUser);
    }

    public List<User> getUniversityAdmins() {
       return userRepository.findAllByRole(Role.UNIVERSITY_ADMIN);
    }

    public void resetPassword(String id, User user) throws MessagingException {
         User savedUser = userRepository.findById(id).get();

        if(savedUser == null) {
            throw new MethodNotAllowedException("Such user doesnt exist");
        }
        savedUser.setEmail(user.getEmail());

        if(!user.getEmail().equals(savedUser.getEmail()) || user.getShortCode()== 0 ) {
            String verificationPassword = generateRandomString();
            int verificationCode = (int) (Math.random() * 9999);
            savedUser.setShortCode(verificationCode);
            savedUser.setPassword(passwordEncoder.encode(verificationPassword));
            String message = "<html><body>" +
                    " <h5> Your pasword is "+ verificationPassword + "</h5>" +
                    "<p>Activate account with <a href = " +API_URL + user.getEmail() + "/" + verificationCode + ">link</a> </p>" +
                    "</body></html>";
            sendMessage(user.getEmail(), message);
        } else {
            savedUser.setMsisdn(user.getMsisdn());
            savedUser.setName(user.getName());
        }

        userRepository.save(savedUser);


    }

    public void deleteUser(String id) throws MessagingException {
        User savedUser = userRepository.findById(id).get();
        if(savedUser == null) {
            throw new MethodNotAllowedException("Such user doesnt exist");
        }
        userRepository.deleteById(id);
        sendMessage(savedUser.getEmail(),"<html><body>" +
                " <h3> Your account as administrator of univerity: " + savedUser.getUniversityName() +  " is deleted from service Bagyt </h3>" +
                " <h5><i>If you have some questions, you can write to this email</i> </h5>" +
                " <h6>Thank you, Bagyt Service </h6>" +
                "</body></html>" );

    }

    public University getUniversityByUser(String email) {
        User savedUser = userRepository.findByEmailContaining(email);

        return universityService.getUniversity(savedUser.getUniversity().get_id());
    }
}
