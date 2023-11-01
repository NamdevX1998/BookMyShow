package com.example.BookMyShow.dto.requestdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddShowSeatsRequest {
    private Integer showId;

    private Integer priceOfClassicSeats;

    private Integer priceOfPremiumSeats;
}
