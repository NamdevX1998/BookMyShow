package com.example.BookMyShow.trasnformer;

import com.example.BookMyShow.dto.requestdtos.AddShowRequest;
import com.example.BookMyShow.dto.responsedtos.ShowResponse;
import com.example.BookMyShow.model.Show;

public class ShowTransformer {
    public static Show showRequestToShow(AddShowRequest showRequest){
        return Show.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .build();
    }

    public static ShowResponse showToShowResponse(Show show){
        return ShowResponse.builder()
                .show_date(show.getShowDate())
                .show_time(show.getShowTime())
                .movieName(show.getMovie().getMovieName())
                .theaterName(show.getTheater().getName())
                .build();
    }
}
