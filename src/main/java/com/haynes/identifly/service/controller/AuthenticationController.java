package com.haynes.identifly.service.controller;

import com.haynes.identifly.service.dto.request.APIResponse;
import com.haynes.identifly.service.dto.request.AuthenticationRequest;
import com.haynes.identifly.service.dto.request.IntrospectRequest;
import com.haynes.identifly.service.dto.response.AuthenticationResponse;
import com.haynes.identifly.service.dto.response.IntrospectResponse;
import com.haynes.identifly.service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/token")
    APIResponse<AuthenticationResponse> athenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return APIResponse.<AuthenticationResponse>builder().result(result).build();
    }
    @PostMapping("/introspect")
    APIResponse<IntrospectResponse> athenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspectResponse(request);
        return APIResponse.<IntrospectResponse>builder().result(result).build();
    }
}
