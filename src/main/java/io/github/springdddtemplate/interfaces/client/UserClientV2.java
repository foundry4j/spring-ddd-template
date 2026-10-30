package io.github.springdddtemplate.interfaces.client;

import io.github.springdddtemplate.application.dto.CreateUserRequest;
import io.github.springdddtemplate.application.dto.UpdateUserRequest;
import io.github.springdddtemplate.application.dto.UserResponse;
import io.github.springdddtemplate.application.dto.UserResponseV2;
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

/// Declarative HTTP client & API specification for User Service V2.
///
/// Dual-purpose interface:
/// 1. Server-side contract: implemented by UserControllerV2 to satisfy API endpoints and inherit Swagger documentation.
/// 2. Client-side proxy: dynamically created by HttpServiceProxyFactory in consumer microservices.
/// V2 endpoints return UserResponseV2 with enriched fields (displayName, createdAt).
@HttpExchange(value = "/api/users", headers = "API-Version=2")
@Tag(name = "User Management V2", description = "V2 APIs for managing users (enriched with displayName and createdAt)")
public interface UserClientV2 {

    @PostExchange
    @Operation(summary = "Create a new user (v2+)", description = "Creates a user and returns the V2 response with displayName and createdAt")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "User created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Username or email already exists")
    })
    ApiResponse<UserResponseV2> createUser(@Valid @RequestBody CreateUserRequest request);

    @GetExchange("/{id}")
    @Operation(summary = "Get user by ID (v2+)", description = "Returns enriched user details including displayName and createdAt")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<UserResponseV2> getUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id
    );

    @PutExchange("/{id}")
    @Operation(summary = "Update a user (v2+)", description = "Updates user details and returns V2 response with displayName and createdAt")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<UserResponseV2> updateUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id,
            @Valid @RequestBody UpdateUserRequest request
    );

    @DeleteExchange("/{id}")
    @Operation(summary = "Delete a user (v2+)", description = "Deletes the user with the given ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<Void> deleteUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id
    );

    @PutExchange("/{id}/activate")
    @Operation(summary = "Activate a user (v2+)", description = "Activates the user account with the given ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User activated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<UserResponse> activateUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id
    );

    @PutExchange("/{id}/deactivate")
    @Operation(summary = "Deactivate a user (v2+)", description = "Deactivates the user account with the given ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User deactivated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    ApiResponse<UserResponse> deactivateUser(
            @Parameter(description = "User ID", example = "1")
            @PathVariable("id") @Positive(message = "User ID must be a positive number") Long id
    );
}
