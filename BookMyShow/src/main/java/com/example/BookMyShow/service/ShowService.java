package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.requestdtos.AddShowRequest;
import com.example.BookMyShow.dto.requestdtos.AddShowSeatsRequest;
import com.example.BookMyShow.dto.responsedtos.ShowResponse;
import com.example.BookMyShow.enums.SeatType;
import com.example.BookMyShow.model.*;
import com.example.BookMyShow.repository.MovieRepository;
import com.example.BookMyShow.repository.ShowRepository;
import com.example.BookMyShow.repository.ShowSeatRespository;
import com.example.BookMyShow.repository.TheaterRepository;
import com.example.BookMyShow.trasnformer.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRespository showSeatRespository;
    public ShowResponse addShow(AddShowRequest addShowRequest) {
        Show show= ShowTransformer.showRequestToShow(addShowRequest);
        Movie movie = movieRepository.findMovieByMovieName(addShowRequest.getMovieName());

        Optional<Theater> optionalTheater = theaterRepository.findById(addShowRequest.getTheaterId());
        Theater theater = optionalTheater.get();

        //setting foreign key values
        show.setMovie(movie);
        show.setTheater(theater);

        //setting the bidirectional mapping
        movie.getShowList().add(show);
        theater.getShowList().add(show);

        //save child not parent
        Show savedShow=showRepository.save(show);  //save both parent entity.

        //model to response dto
        return ShowTransformer.showToShowResponse(savedShow);
    }

    public String createShowSeats(AddShowSeatsRequest showSeatsRequest) {
        //To create Show seats;theater seats are required.Because we get info of seat no and seat type from
        //theater seat.

        //To get theater seat first we have to get theater.And to find theater first find show.
        Show show=showRepository.findById(showSeatsRequest.getShowId()).get();

        Theater theater=show.getTheater();
        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();

        List<ShowSeat>showSeatList=new ArrayList<>();

        for(TheaterSeat theaterSeat:theaterSeatList){
            ShowSeat showSeat=ShowSeat.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .show(show)
                    .isAvailable(true)
                    .build();

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setCost(showSeatsRequest.getPriceOfClassicSeats());
            else
                showSeat.setCost(showSeatsRequest.getPriceOfPremiumSeats());

            showSeatList.add(showSeat);
        }

        show.setShowSeatList(showSeatList);    //bidirectional mapping


        //Either save parent or save child  (i.e. showRepository.save(show);  <-- saving parent)
        //but if you try to save child then you have to save all seats
        //child is alot of seats (you need to save that list)
        //showSeatRespository.saveAll(showSeatList);  <-- saving child

        showRepository.save(show);
        return "The show seats have been added";
    }
}
