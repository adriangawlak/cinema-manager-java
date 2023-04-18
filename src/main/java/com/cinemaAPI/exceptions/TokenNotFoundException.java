package com.cinemaAPI.exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super("Wrong token!");
    }
}
