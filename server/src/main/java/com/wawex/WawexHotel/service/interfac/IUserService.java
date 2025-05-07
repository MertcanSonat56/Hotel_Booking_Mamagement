package com.wawex.WawexHotel.service.interfac;

import com.wawex.WawexHotel.dto.LoginRequest;
import com.wawex.WawexHotel.dto.Response;
import com.wawex.WawexHotel.model.User;

public interface IUserService {
    
    Response register(User user);
    Response login(LoginRequest loginRequest);
    Response getAllUsers();
    Response getUserBookingHistory(String userId);
    Response deleteUser(String userId);
    Response getUserById(String userId);
    Response getMyInfo(String email);
}
