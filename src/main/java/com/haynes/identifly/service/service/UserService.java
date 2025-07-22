package com.haynes.identifly.service.service;

import com.haynes.identifly.service.dto.request.UserCreateRequest;
import com.haynes.identifly.service.dto.request.UserUpdateRequest;
import com.haynes.identifly.service.dto.response.UserResponse;
import com.haynes.identifly.service.entity.User;
import com.haynes.identifly.service.enums.Role;
import com.haynes.identifly.service.exception.AppException;
import com.haynes.identifly.service.exception.ErrorCode;
import com.haynes.identifly.service.mapper.UserMapper;
import com.haynes.identifly.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    public User createRequest(UserCreateRequest request){

        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return userRepository.save(user);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()-> new RuntimeException("Không có User này")));
    }
    public String deleteUser(String id){
        userRepository.deleteById(id);
        return "Thao tác thành công";
    }

    public User updateUser(UserUpdateRequest request, String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User không tồn tại"));
        userMapper.updateUser(user, request);
        return userRepository.save(user);
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse( userRepository.findById(id).orElseThrow(()->new RuntimeException("User không tồn tại")));
    }


}
