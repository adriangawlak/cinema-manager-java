package com.cinemaAPI.models;

import com.cinemaAPI.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnedTicket {
    private Seat returned_ticket;

}
