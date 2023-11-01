package com.example.BookMyShow.service;
import com.example.BookMyShow.dto.requestdtos.AddTheaterRequest;
import com.example.BookMyShow.enums.SeatType;
import com.example.BookMyShow.model.Show;
import com.example.BookMyShow.model.Theater;
import com.example.BookMyShow.model.TheaterSeat;
import com.example.BookMyShow.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    public String addTheater(AddTheaterRequest addTheaterRequest){

        //1. Create the Theater Entity
        Theater theater = Theater.builder().name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .city(addTheaterRequest.getCity())
                .build();

        //Create the theater Seats Entity
        createTheaterSeats(theater,addTheaterRequest);

        return "Theater and its seats have been saved to DB";
    }
    private void createTheaterSeats(Theater theater,AddTheaterRequest addTheaterRequest){
        int noOfClassicSeats=addTheaterRequest.getNoOfClassicSeats();
        int noOfPremiumSeats= addTheaterRequest.getNoOfPremiumSeats();
        int noOfSeatsPerRow= addTheaterRequest.getNoOfSeatsPerRow();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        int row=1;
        char ch='A';
        int count=1;
        boolean flag=false;
        while(count<=noOfClassicSeats){
            String seatNo=row+""+ch;
            if(count%noOfSeatsPerRow==0){
                row++;
                ch='A';
                flag=true;
            }
            count++;
            if(!flag)
            ch++;
            flag=false;

            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)   //Setting the FK for each and every seat.
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        count--;
        if(!(count%noOfSeatsPerRow==0)){
            row++;
        }
        ch='A';
        count=1;


        while(count<=noOfPremiumSeats){
            String seatNo=row+""+ch;
            if(count%noOfSeatsPerRow==0){
                row++;
                ch='A';
                flag=true;
            }
            count++;
            if(!flag)
                ch++;
            flag=false;

            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)   //Setting the FK for each and every seat.
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        theater.setTheaterSeatList(theaterSeatList);   //bidirectional mapping
        theaterRepository.save(theater);   //save both entities
    }
}
