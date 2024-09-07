package com.example.TicketingBooking.service;

import com.example.TicketingBooking.model.Receipt;
import com.example.TicketingBooking.model.Seat;
import com.example.TicketingBooking.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    private final Map<String, Receipt> receipts = new HashMap<>();
    private final List<Seat> seatsA = new ArrayList<>();
    private final List<Seat> seatsB = new ArrayList<>();

    public TicketService() {
        // Initialize train with some seats
        for (int i = 1; i <= 10; i++) {
            seatsA.add(new Seat("A", "A" + i));
            seatsB.add(new Seat("B", "B" + i));
        }
    }

    public Receipt purchaseTicket(User user) {
        String seatNumber = allocateSeat(user);
        Receipt receipt = new Receipt("London", "France", user, 20.0, new Seat(seatNumber.substring(0, 1), seatNumber));
        receipts.put(user.getEmail(), receipt);
        return receipt;
    }

    public String allocateSeat(User user) {
        Seat seat = seatsA.isEmpty() ? seatsB.remove(0) : seatsA.remove(0);
        return seat.getSection() + seat.getSeatNumber();
    }

    public Receipt getReceipt(String email) {
        return receipts.get(email);
    }

    public List<Receipt> getUsersBySection(String section) {
        List<Receipt> result = new ArrayList<>();
        for (Receipt receipt : receipts.values()) {
            if (receipt.getSeat().getSection().equals(section)) {
                result.add(receipt);
            }
        }
        return result;
    }

    public boolean removeUser(String email) {
        return receipts.remove(email) != null;
    }

    public boolean modifySeat(String email, String newSeatNumber) {
        Receipt receipt = receipts.get(email);
        if (receipt != null) {
            receipt.setSeat(new Seat(newSeatNumber.substring(0, 1), newSeatNumber));
            return true;
        }
        return false;
    }
}
