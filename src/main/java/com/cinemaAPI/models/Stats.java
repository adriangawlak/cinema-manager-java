package com.cinemaAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Stats {

    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

}
