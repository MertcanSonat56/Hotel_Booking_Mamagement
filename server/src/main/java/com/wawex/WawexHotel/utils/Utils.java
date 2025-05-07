package com.wawex.WawexHotel.utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import com.wawex.WawexHotel.dto.BookingDto;
import com.wawex.WawexHotel.dto.RoomDto;
import com.wawex.WawexHotel.dto.UserDto;
import com.wawex.WawexHotel.model.Booking;
import com.wawex.WawexHotel.model.Room;
import com.wawex.WawexHotel.model.User;

public class Utils {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomConfirmationCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static UserDto mapUserEntityToUserDto(User user) {
       UserDto userDto = new UserDto();   
     
       userDto.setId(user.getId());
       userDto.setName(user.getName());
       userDto.setEmail(user.getEmail());
       userDto.setPhoneNumber(user.getPhoneNumber());
       userDto.setRole(user.getRole());

       return userDto;
    }

    public static RoomDto mapRoomEntityToRoomDTO(Room room) {
        RoomDto roomDto = new RoomDto();

        roomDto.setId(room.getId());
        roomDto.setRoomType(room.getRoomType());
        roomDto.setRoomPrice(room.getRoomPrice());
        roomDto.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDto.setRoomDescription(room.getRoomDescription());

        return roomDto;
    }

     public static BookingDto mapBookingEntityToBookingDTO(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        
        bookingDto.setId(booking.getId());
        bookingDto.setCheckInDate(booking.getCheckInDate());
        bookingDto.setCheckOutDate(booking.getCheckOutDate());
        bookingDto.setNumOfAdults(booking.getNumOfAdults());
        bookingDto.setNumOfChildren(booking.getNumOfChildren());
        bookingDto.setTotalNumOfGuest(booking.getTotalNumOfGuest());
        bookingDto.setBookingConfirmationCode(booking.getBookingConfirmationCode());

        return bookingDto;
    }

    public static RoomDto mapRoomEntityToRoomDTOPlusBookings(Room room) {
        RoomDto roomDto = new RoomDto();

        roomDto.setId(room.getId());
        roomDto.setRoomType(room.getRoomType());
        roomDto.setRoomPrice(room.getRoomPrice());
        roomDto.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDto.setRoomDescription(room.getRoomDescription());

        if (room.getBookings() != null) {
            roomDto.setBookings(room.getBookings().stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList()));
        }
        return roomDto;
    }

    public static BookingDto mapBookingEntityToBookingDTOPlusBookedRooms(Booking booking, boolean mapUser) {

        BookingDto bookingDto = new BookingDto();
        
        bookingDto.setId(booking.getId());
        bookingDto.setCheckInDate(booking.getCheckInDate());
        bookingDto.setCheckOutDate(booking.getCheckOutDate());
        bookingDto.setNumOfAdults(booking.getNumOfAdults());
        bookingDto.setNumOfChildren(booking.getNumOfChildren());
        bookingDto.setTotalNumOfGuest(booking.getTotalNumOfGuest());
        bookingDto.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        
        if (mapUser) {
            bookingDto.setUser(Utils.mapUserEntityToUserDto(booking.getUser()));
        }
        if (booking.getRoom() != null) {
            RoomDto roomDto = new RoomDto();

            roomDto.setId(booking.getRoom().getId());
            roomDto.setRoomType(booking.getRoom().getRoomType());
            roomDto.setRoomPrice(booking.getRoom().getRoomPrice());
            roomDto.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
            roomDto.setRoomDescription(booking.getRoom().getRoomDescription());
            bookingDto.setRoom(roomDto);
        }
        return bookingDto;
    }

    public static UserDto mapUserEntityToUserDTOPlusUserBookingsAndRoom(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());

        if (!user.getBookings().isEmpty()) {
            userDto.setBookings(user.getBookings().stream().map(booking -> mapBookingEntityToBookingDTOPlusBookedRooms(booking, false)).collect(Collectors.toList()));
        }
        return userDto;
    }

    public static List<UserDto> mapUserListEntityToUserListDTO(List<User> userList) {
        return userList.stream().map(Utils::mapUserEntityToUserDto).collect(Collectors.toList());
    }

    public static List<RoomDto> mapRoomListEntityToRoomListDTO(List<Room> roomList) {
        return roomList.stream().map(Utils::mapRoomEntityToRoomDTO).collect(Collectors.toList());
    }

    public static List<BookingDto> mapBookingListEntityToBookingListDTO(List<Booking> bookingList) {
        return bookingList.stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList());
    }
}
