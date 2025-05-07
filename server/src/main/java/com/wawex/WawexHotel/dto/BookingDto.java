package com.wawex.WawexHotel.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {

    private Long id;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private int numOfAdults;
    private int numOfChildren;
    private int totalNumOfGuest;

    private String bookingConfirmationCode;
    
    private UserDto user;
    private RoomDto room;
}
