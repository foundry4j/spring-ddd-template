package io.github.springdddtemplate.interfaces.controller;

import io.github.springdddtemplate.application.dto.CreateUserRequest;
import io.github.springdddtemplate.application.dto.UpdateUserRequest;
import io.github.springdddtemplate.application.dto.UserResponse;
import io.github.springdddtemplate.application.dto.UserResponseV2;
import io.github.springdddtemplate.application.service.UserApplicationService;
import io.github.springdddtemplate.interfaces.client.UserClientV2;
import io.github.springdddtemplate.interfaces.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/// User management REST controller for API Version 2 (and above).
///
/// Implements UserClientV2 to adhere to the shared contract and inherit OpenAPI documentation
/// and validation constraints.
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserControllerV2 implements UserClientV2 {

    private final UserApplicationService userApplicationService;

    @Override
    @PostMapping(version = "2+")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponseV2> createUser(@RequestBody CreateUserRequest request) {
        return ApiResponse.success(userApplicationService.createUserV2(request));
    }

    @Override
    @GetMapping(value = "/{id}", version = "2+")
    public ApiResponse<UserResponseV2> getUser(@PathVariable("id") Long id) {
        return ApiResponse.success(userApplicationService.getUserV2(id));
    }

    @Override
    @PutMapping(value = "/{id}", version = "2+")
    public ApiResponse<UserResponseV2> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequest request) {
        return ApiResponse.success(userApplicationService.updateUserV2(id, request));
    }

    @Override
    @DeleteMapping(value = "/{id}", version = "2+")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteUser(@PathVariable("id") Long id) {
        userApplicationService.deleteUser(id);
        return ApiResponse.empty();
    }

    @Override
    @PutMapping(value = "/{id}/activate", version = "2+")
    public ApiResponse<UserResponse> activateUser(@PathVariable("id") Long id) {
        return ApiResponse.success(userApplicationService.activateUser(id));
    }

    @Override
    @PutMapping(value = "/{id}/deactivate", version = "2+")
    public ApiResponse<UserResponse> deactivateUser(@PathVariable("id") Long id) {
        return ApiResponse.success(userApplicationService.deactivateUser(id));
    }
}
