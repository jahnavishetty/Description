package com.example.TicketingBooking.controller;



import com.example.TicketingBooking.model.Receipt;
import com.example.TicketingBooking.model.User;
import com.example.TicketingBooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // API to purchase a ticket
    @PostMapping("/purchase")
    public Receipt purchaseTicket(@RequestBody User user) {
        return ticketService.purchaseTicket(user);
    }

    // API to get the receipt by user email
    @GetMapping("/receipt/{email}")
    public Receipt getReceipt(@PathVariable String email) {
        return ticketService.getReceipt(email);
    }

    // API to view users and seats by section
    @GetMapping("/users/section/{section}")
    public List<Receipt> getUsersBySection(@PathVariable String section) {
        return ticketService.getUsersBySection(section);
    }

    // API to remove a user from the train
    @DeleteMapping("/remove/{email}")
    public boolean removeUser(@PathVariable String email) {
        return ticketService.removeUser(email);
    }

    // API to modify a user's seat
    @PutMapping("/modify/{email}/{newSeat}")
    public boolean modifySeat(@PathVariable String email, @PathVariable String newSeat) {
        return ticketService.modifySeat(email, newSeat);
    }
}
