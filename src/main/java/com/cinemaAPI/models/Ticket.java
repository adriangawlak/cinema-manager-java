package com.cinemaAPI.models;

import com.cinemaAPI.entity.Seat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class Ticket {
    String token;
    Seat ticket;

    public Ticket(Seat ticket) {
        this.token = UUID.randomUUID().toString();
        this.ticket = ticket;
    }

}
