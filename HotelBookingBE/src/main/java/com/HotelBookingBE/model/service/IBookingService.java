package com.HotelBookingBE.model.service;

import com.HotelBookingBE.model.BookingModel;

public interface IBookingService {
	Long save(BookingModel book);
	BookingModel SearchByUserId(Long user_id, int page);
}