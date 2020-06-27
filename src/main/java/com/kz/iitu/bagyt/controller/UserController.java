package com.kz.iitu.bagyt.controller;

import com.kz.iitu.bagyt.model.University;
import com.kz.iitu.bagyt.model.User;
import com.kz.iitu.bagyt.model.enums.Role;
import com.kz.iitu.bagyt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @GetMapping("/msisdn")
    public void verifyUser(@RequestParam(name = "msisdn") @Valid String msisdn) {

        userService.verifyMsisdn(msisdn);
    }

    @GetMapping("/verification")
    public ResponseEntity verifyingCode(
            @RequestParam(name = "msisdn") @Valid String msisdn,
            @RequestParam(name = "code") int verificationCode) {
        userService.verifyCode(msisdn, verificationCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{email}/{verificationCode}")
    public void verifyUniversityAdmin(
            HttpServletResponse httpServletResponse,
            @PathVariable("email") String email,
            @PathVariable(name = "verificationCode") int verificationCode) {
        userService.verifyUniversityAdmin(email, verificationCode);
        httpServletResponse.setHeader("Location", "http://localhost:3000");
        httpServletResponse.setStatus(302);
    }

    @PostMapping("/university-admin")
    public ResponseEntity createUniversityAdmin(@RequestBody User user) throws MessagingException {
        user.setRole(Role.UNIVERSITY_ADMIN);
        userService.verifyEmail(user);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/university-admin")
    public List<User> getUniversityAdmins() {
        return userService.getUniversityAdmins();
    }

    @GetMapping(value = "/university-admin/email",  produces = MediaType.APPLICATION_JSON_VALUE)
    public University getUniversityAdmins(@RequestParam("email") String email) {
        return userService.getUniversityByUser(email);
    }


    @PutMapping("/reset/{id}")
    public void resetPasswordOrEmail(@PathVariable("id") String id, @RequestBody User user) throws MessagingException {
        userService.resetPassword(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id) throws MessagingException {
        userService.deleteUser(id);
    }

}
