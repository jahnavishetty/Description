package com.example.TicketingBooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    private String from;
    private String to;
    private User user;
    private double pricePaid;
    private Seat seat;
}