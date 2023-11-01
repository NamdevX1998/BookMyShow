package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.requestdtos.BookTicketRequest;
import com.example.BookMyShow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/bookTicket")
    public String bookTicket(@RequestBody BookTicketRequest bookTicketRequest) {
        String result  = ticketService.bookTicket(bookTicketRequest);
        return result;
    }

}
