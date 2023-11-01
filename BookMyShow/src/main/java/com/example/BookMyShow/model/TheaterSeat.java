package com.example.BookMyShow.model;

import com.example.BookMyShow.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theater_seat")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private Theater theater;
}
