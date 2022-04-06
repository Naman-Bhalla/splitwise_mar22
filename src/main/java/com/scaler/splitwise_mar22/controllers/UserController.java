package com.scaler.splitwise_mar22.controllers;

import com.scaler.splitwise_mar22.dtos.*;
import com.scaler.splitwise_mar22.models.User;
import com.scaler.splitwise_mar22.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public  ResponseDTO<RegisterUserResponseDTO> registerUser(
            RegisterUserRequestDTO request
    ) {
        User user = userService.registerUser(
                request.getName(),
                request.getPassword(),
                request.getPhoneNumber()
        );

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());

        ResponseDTO<RegisterUserResponseDTO> response = new ResponseDTO<>();
        response.setStatus(ResponseStatus.SUCCESS);
        response.setData(new RegisterUserResponseDTO());
        response.getData().setUser(userDTO);

        return response;
    }

    // /users/settleUp
    public SettleUpResponseDTO settleUp(SettleUpUserRequestDTO request) {
        return null;
    }
}
