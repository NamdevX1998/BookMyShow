package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.requestdtos.AddShowRequest;
import com.example.BookMyShow.dto.requestdtos.AddShowSeatsRequest;
import com.example.BookMyShow.dto.responsedtos.ShowResponse;
import com.example.BookMyShow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public ShowResponse addShow(@RequestBody AddShowRequest addShowRequest){
        ShowResponse result = showService.addShow(addShowRequest);
        return result;
    }

    @PostMapping("/createShowSeats")
    public String createShowSeats(@RequestBody AddShowSeatsRequest addShowSeatsRequest) {
        String result = showService.createShowSeats(addShowSeatsRequest);
        return result;
    }
}
