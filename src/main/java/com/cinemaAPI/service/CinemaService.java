package com.cinemaAPI.service;

import com.cinemaAPI.entity.Seat;
import com.cinemaAPI.models.Stats;
import com.cinemaAPI.models.Ticket;
import com.cinemaAPI.entity.Cinema;
import com.cinemaAPI.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CinemaService {
    private final Cinema cinema;

    public Map<String, Seat> soldTickets;

    @Autowired
    public CinemaService(Cinema cinema) {
        this.cinema = cinema;
        this.soldTickets = new HashMap<>();
    }

    public Cinema getCinema() {
        return this.cinema;
    }
    public Ticket purchaseTicket(Seat seat) {
        // Throw exception if not available, else return seat with price
        Seat purchasedSeat = checkSeatsValidity(seat);

        cinema.bookSeat(purchasedSeat);
        Ticket ticket = new Ticket(purchasedSeat);
        soldTickets.put(ticket.getToken(), ticket.getTicket());
        return ticket;
    }

    public Seat returnTicket(String token) {
        if (!soldTickets.containsKey(token)) // change to ticket optional
            throw new TokenNotFoundException();
        Seat returnedSeat = soldTickets.get(token);

        //Add the seat back to available seats and remove the token from the map
        cinema.getAllSeats().stream()
                .filter(seat -> (seat.getRow() == returnedSeat.getRow()
                        && seat.getColumn() == returnedSeat.getColumn()))
                .forEach(seat -> seat.setBooked(false));
        cinema.updateAvailableSeats();

        soldTickets.remove(token);
        return returnedSeat;
    }

    public Stats calculateStats() {
        int current_income = getCurrentIncome();
        int numAvailableSeats = cinema.getAllSeats().size() - soldTickets.size();
        int numPurchasedSeats = soldTickets.size();
        return new Stats(current_income, numAvailableSeats, numPurchasedSeats);
    }

    public int getCurrentIncome() {
        int total = soldTickets.values().stream()
                .mapToInt(Seat::getPrice)
                .sum();
        return total;
    }

    private Seat checkSeatsValidity(Seat seat) {
        // Check if seat's number is within the cinema room
        if ((seat.getColumn() <= 0) || (seat.getColumn() > cinema.getTotal_columns())
                || (seat.getRow() <= 0) || (seat.getRow() > cinema.getTotal_rows())) {
            throw new SeatOutOfBoundsException();
        }
        // Check if seat wasn't purchased
        int seatNumber = cinema.getSeatNumber(seat);
        Seat purchasedSeat = cinema.getSeat(seatNumber);
        if (purchasedSeat.isBooked())
            throw new SeatNotAvailableException();

        return purchasedSeat;
    }

}