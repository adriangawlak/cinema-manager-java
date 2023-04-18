package com.cinemaAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CinemaRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<SeatErrorResponse> handleException(SeatNotAvailableException e) {
        SeatErrorResponse response = new SeatErrorResponse(e.getMessage());
//        response.setError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SeatErrorResponse> handleException(SeatOutOfBoundsException e) {
        SeatErrorResponse response = new SeatErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SeatErrorResponse> handleException(TokenNotFoundException e) {
        SeatErrorResponse response = new SeatErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SeatErrorResponse> handleException(WrongPasswordException e) {
        SeatErrorResponse response = new SeatErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}