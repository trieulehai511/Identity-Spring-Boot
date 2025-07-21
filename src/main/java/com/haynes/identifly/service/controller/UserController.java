package com.haynes.identifly.service.controller;
import com.haynes.identifly.service.dto.request.APIResponse;
import com.haynes.identifly.service.dto.request.UserCreateRequest;
import com.haynes.identifly.service.dto.request.UserUpdateRequest;
import com.haynes.identifly.service.dto.response.UserResponse;
import com.haynes.identifly.service.entity.User;
import com.haynes.identifly.service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    APIResponse<User> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        APIResponse<User> apiResponse = new APIResponse<>();
        apiResponse.setResult(userService.createRequest(userCreateRequest));
        return apiResponse;
    }

    @GetMapping()
    List<User> getUSer(){
        return userService.getUsers();
    }

    @GetMapping("{userID}")
    UserResponse getUserById(@PathVariable("userID") String userID ){
        return userService.getUserById(userID);
    }
    @DeleteMapping("{userID}")
    String deleteUser(@PathVariable("userID") String userID){
        userService.deleteUser(userID);
        return "Thao tác thành công";
    }
    @PutMapping("{userID}")
    String updateUser(@PathVariable("userID") String userID, @RequestBody UserUpdateRequest request){
        userService.updateUser(request,userID);
        return "Thao tác thành công";
    }
}
