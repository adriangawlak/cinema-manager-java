package com.cinemaAPI.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
public class Cinema {

    private int total_rows;
    private int total_columns;

    @JsonIgnore
    private ArrayList<Seat> allSeats;

    private ArrayList<Seat> available_seats;

    // By default, the Cinema has 9 rows with 9 seats each
    public Cinema() {
        this(9,9);
    }

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.allSeats = new ArrayList<>();
        this.available_seats = new ArrayList<>();
        initSeats();
    }

    private void initSeats() {
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                Seat seat = new Seat(i, j);
                allSeats.add(seat);
                available_seats.add(seat);
            }
        }
    }

    public Seat getSeat(int seatNumber) {
        return allSeats.get(seatNumber);
    }

    public int getSeatNumber(Seat seat) {
        int seatNumber = (seat.getRow() - 1) * this.getTotal_rows() + seat.getColumn() - 1;
        return seatNumber;
    }

    public void bookSeat(Seat seat) {
        int seatNumber = this.getSeatNumber(seat);
        Seat seatToUpdate = this.allSeats.get(seatNumber);
        seatToUpdate.setBooked(true);
        allSeats.set(seatNumber, seatToUpdate);
        updateAvailableSeats();
    }

    public void updateAvailableSeats() {
        ArrayList<Seat> updatedAvailableSeats = new ArrayList<>();
        for (Seat seat : allSeats) {
            if (!seat.isBooked()) {
                updatedAvailableSeats.add(seat);
            }
        }
        this.available_seats = updatedAvailableSeats;
    }

}
