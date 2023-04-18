package com.cinemaAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Seat {
    private int row;
    private int column;
    private Integer price;
    @JsonIgnore
    private boolean isBooked;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        setPrice();
    }

    public void setPrice() {
        int seatPrice = 8;
        if (this.row <= 4)
            seatPrice = 10;
        this.price = seatPrice;
    }

    public boolean isBooked() {
        return isBooked;
    }
    @JsonIgnore
    public void setBooked(boolean booked) {
        isBooked = booked;
    }

}