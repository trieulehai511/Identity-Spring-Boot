package com.haynes.identifly.service.controller;
import com.haynes.identifly.service.dto.response.APIResponse;
import com.haynes.identifly.service.dto.request.UserCreateRequest;
import com.haynes.identifly.service.dto.request.UserUpdateRequest;
import com.haynes.identifly.service.dto.response.UserResponse;
import com.haynes.identifly.service.mapper.UserMapper;
import com.haynes.identifly.service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping()
    APIResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        return APIResponse.<UserResponse>builder().result(userService.createRequest(userCreateRequest)).build();
    }

    @GetMapping()
    APIResponse<List<UserResponse>> getUSers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return APIResponse.<List<UserResponse>>builder().result(userService.getUsers()).build();
    }

    @GetMapping("/{userID}")
    APIResponse<UserResponse> getUserById(@PathVariable("userID") String userID ){
        return APIResponse.<UserResponse>builder().result(userService.getUserById(userID)).build();
    }

    @GetMapping("/myinfo")
    APIResponse<UserResponse> getMyInfo(){
        return APIResponse.<UserResponse>builder().result(userService.getMyInfo()).build();
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
