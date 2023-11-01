package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.requestdtos.BookTicketRequest;
import com.example.BookMyShow.model.*;
import com.example.BookMyShow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public String bookTicket(BookTicketRequest bookTicketRequest) {
        String movieName=bookTicketRequest.getMovieName();
        Movie movie=movieRepository.findMovieByMovieName(movieName);
        int theaterId=bookTicketRequest.getTheaterId();
        Theater theater=theaterRepository.findById(theaterId).get();
        LocalDate date=bookTicketRequest.getShowDate();
        LocalTime time=bookTicketRequest.getShowTime();
        List<String>reqSeats=bookTicketRequest.getRequestedSeatNos();
        User user=userRepository.findById(bookTicketRequest.getUserId()).get();

        Show correctShow=findCorrectShow(movie,theater,date,time);
        List<ShowSeat>getAllShowSeats=correctShow.getShowSeatList();

        int totalPrice=0;
        int countOfClassicSeat=0;
        int countOfPremiumSeat=0;
        for(ShowSeat showSeat:getAllShowSeats) {

            if(bookTicketRequest.getRequestedSeatNos().contains(showSeat.getSeatNo())) {
                //Whatever are the requested seats : mark them as not available in show seats
                showSeat.setAvailable(false);
                if(showSeat.getSeatType().toString().equalsIgnoreCase("PREMIUM"))
                    countOfPremiumSeat++;
                else
                    countOfClassicSeat++;

                totalPrice = totalPrice + showSeat.getCost();
            }
        }

        Ticket ticket=Ticket.builder()
                .movieName(movieName)
                .totalPrice(totalPrice)
                .showDate(date)
                .showTime(time)
                .theaterAddress(theater.getAddress())
                .show(correctShow)
                .user(user)
                .bookedSeats(bookTicketRequest.getRequestedSeatNos().toString())
                .build();

       correctShow.getTicketList().add(ticket);
       user.getTicketList().add(ticket);

       ticketRepository.save(ticket);   //save child

        //send mail:-
        String text="Hello from Book My Show!We will use this email to send your ticket confirmation."+"\n"+
                "You booked "+countOfPremiumSeat+" premium seats.and "+countOfClassicSeat+" classic seats";

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("springtestingjob1234");
        simpleMailMessage.setTo(user.getEmailId());
        simpleMailMessage.setSubject("Information Regarding Booking from BookMyShow");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);

        return "Ticket has been booked";
    }

    private Show findCorrectShow(Movie movie,Theater theater,LocalDate date,LocalTime time){
        Show show =showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(date,time,movie,theater);
        return show;
    }
}
