package com.cinemaAPI.exceptions;

public class SeatErrorResponse {
    private String error;

    public SeatErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}