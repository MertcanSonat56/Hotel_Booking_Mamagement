package com.wawex.WawexHotel.service.interfac;

import com.wawex.WawexHotel.dto.Response;
import com.wawex.WawexHotel.model.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);
    Response getAllBookings();
    Response cancelBooking(Long bookingId);
}
