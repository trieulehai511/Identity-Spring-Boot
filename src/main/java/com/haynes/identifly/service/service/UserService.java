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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createRequest(UserCreateRequest request){

        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);
        try{
            user = userRepository.save(user);

        }catch(DataIntegrityViolationException exception){
            throw  new AppException(ErrorCode.USER_EXISTED);
        }
        return userMapper.toUserResponse(user);
    }
    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow( ()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers(){
        log.info("In method get User");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }
    @PostAuthorize("hasRole('ADMIN')")
    public UserResponse getUserById(String id){
        log.info("In method get User ID");
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()-> new RuntimeException("Không có User này")));
    }
    public String deleteUser(String id){
        userRepository.deleteById(id);
        return "Thao tác thành công";
    }

    public UserResponse updateUser(UserUpdateRequest request, String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User không tồn tại"));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse( userRepository.findById(id).orElseThrow(()->new RuntimeException("User không tồn tại")));
    }


}
