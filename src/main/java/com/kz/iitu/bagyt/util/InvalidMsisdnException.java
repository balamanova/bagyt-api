package com.kz.iitu.bagyt.util;


public class InvalidMsisdnException extends Exception {

    private static final String DEFAULT_MESSAGE = "Illegal msisdn value";

    public InvalidMsisdnException(String message) {
        super(message);
    }

    public InvalidMsisdnException(String message, Throwable cause) {
        super(message, cause);
    }

    public static InvalidMsisdnException forInputString(String msisdn, Exception e) {
        return new InvalidMsisdnException(
                String.format("Invalid msisdn for input :'%s'. Cause %s:%s ", msisdn, e.getClass().getCanonicalName(), e.getLocalizedMessage()), e);
    }

    public static InvalidMsisdnException invalidRangeForInputString(String msisdn) {
        return new InvalidMsisdnException(
                String.format("Invalid range of value msisdn for input :'%s'. Value must between 7_000_000_0000 and 7_999_999_9999", msisdn));
    }

    @Override
    public String getMessage() {
        return DEFAULT_MESSAGE;
    }
}
