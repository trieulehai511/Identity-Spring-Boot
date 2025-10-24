package com.haynes.identifly.service.controller;

import com.haynes.identifly.service.dto.response.APIResponse;
import com.haynes.identifly.service.dto.request.PermissionRequest;
import com.haynes.identifly.service.dto.response.PermissionResponse;
import com.haynes.identifly.service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    APIResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return APIResponse.<PermissionResponse>builder().result(permissionService.create(request)).build();
    }

    @GetMapping
    APIResponse<List<PermissionResponse>> getAll(){
        return APIResponse.<List<PermissionResponse>>builder().result(permissionService.getAll()).build();
    }

    @DeleteMapping("/{name}")
    APIResponse<String> delete(@PathVariable String name ){
        permissionService.delete(name);
        return APIResponse.<String>builder().result("Thao tac thanh cong").build();
    }
}
