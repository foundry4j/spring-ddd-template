package io.github.springdddtemplate.interfaces.client;

import io.github.springdddtemplate.application.dto.CreateUserRequest;
import io.github.springdddtemplate.application.dto.UpdateUserRequest;
import io.github.springdddtemplate.application.dto.UserResponse;
import io.github.springdddtemplate.interfaces.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

/// Declarative HTTP client & API specification for User Service V1.
///
/// Dual-purpose interface:
/// 1. Server-side contract: implemented by UserControllerV1 to satisfy API endpoints and inherit Swagger documentation.
/// 2. Client-side proxy: dynamically created by HttpServiceProxyFactory in consumer microservices.
@HttpExchange(value = "/api/users", headers = "API-Version=1")
@Tag(name = "User Management V1", description = "V1 APIs for managing users")
public interface UserClientV1 {

    @PostExchange
    @Operation(summary = "Create a new user (v1)", description = "Creates a user and returns the V1 response")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "User created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Username or email already exists")
    })
    ApiResponse<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request);

    @GetExchange("/{id}")
    @Operation(summary = "Get user by ID (v1)", description = "Returns user details for the given ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<UserResponse> getUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id
    );

    @PutExchange("/{id}")
    @Operation(summary = "Update a user (v1)", description = "Updates user details and returns V1 response")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<UserResponse> updateUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id,
            @Valid @RequestBody UpdateUserRequest request
    );

    @DeleteExchange("/{id}")
    @Operation(summary = "Delete a user (v1)", description = "Deletes the user with the given ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<Void> deleteUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id
    );

    @PutExchange("/{id}/activate")
    @Operation(summary = "Activate a user (v1)", description = "Activates the user account with the given ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User activated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<UserResponse> activateUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id
    );

    @PutExchange("/{id}/deactivate")
    @Operation(summary = "Deactivate a user (v1)", description = "Deactivates the user account with the given ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User deactivated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<UserResponse> deactivateUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id
    );
}
