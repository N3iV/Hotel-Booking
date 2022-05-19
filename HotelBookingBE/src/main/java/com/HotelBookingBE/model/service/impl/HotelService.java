package com.HotelBookingBE.model.service.impl;

import java.sql.Timestamp;

import com.HotelBookingBE.mapper.HotelMapper;
import com.HotelBookingBE.model.HotelModel;
import com.HotelBookingBE.model.UserModel;
import com.HotelBookingBE.model.dao.IHotelDao;
import com.HotelBookingBE.model.dao.IUSerDao;
import com.HotelBookingBE.model.dao.impl.HotelDao;
import com.HotelBookingBE.model.dao.impl.UserDao;
import com.HotelBookingBE.model.service.IHotelService;


public class HotelService implements IHotelService {
	private IHotelDao hotelDao;
	private IUSerDao userDao;
	public HotelService()
	{
		hotelDao = new HotelDao();
		userDao = new UserDao();
	}
	@Override
	public Long save(HotelModel hotel) {
		hotel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		hotel.setRoomQuantity(0L);
		Long id = hotelDao.save(hotel);
		UserModel u = userDao.findOne(hotel.getUser_id());
		u.setRoleId(2L);
		userDao.UpdateRoleId(u);
		return id;
	}
	@Override
	public HotelModel Search(Timestamp checkinDate, Timestamp checkoutDate, Long provinceId, Long typeroomId,
			Long bedQuantity, int page) {
		Integer startPage = 0;
		HotelModel hotel = new HotelModel();
		hotel.setMaxPageItem(10);
		hotel.setPage(page);
		Integer maxItem = hotelDao.countMaxItem(checkinDate, checkoutDate, provinceId, typeroomId, bedQuantity);

		hotel.setTotalPage((int)Math.ceil((double)maxItem/(double)hotel.getMaxPageItem()));
		
		if(page==1)
		{
			startPage = hotel.getPage()-1;
		}else
		{
			startPage = (hotel.getPage() -1) * hotel.getMaxPageItem() ;
		}
		
		hotel.setResults(hotelDao.Search(checkinDate, checkoutDate, provinceId, typeroomId, bedQuantity, startPage,hotel.getMaxPageItem()));
		HotelMapper map = new HotelMapper();
		hotel.setShortModels(map.ModeltoModelView(hotel.getResults()));
		return hotel;
	}
	@Override
	public HotelModel findOne(Long id) {
		return hotelDao.findOne(id);
	}
	@Override
	public void saveChange(HotelModel hotel) {
		hotel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		hotelDao.updateHotel(hotel);
	}

	@Override
	public HotelModel findOnebyUserId(Long user_id) {
		return hotelDao.findOne(user_id);
	}
	
	
}