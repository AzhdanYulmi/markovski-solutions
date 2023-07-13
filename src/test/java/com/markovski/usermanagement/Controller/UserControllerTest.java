package com.markovski.usermanagement.Controller;

import com.markovski.usermanagement.DTO.AppUserRequest;
import com.markovski.usermanagement.DTO.AppUserResponse;
import com.markovski.usermanagement.Exception.UserNotFoundException;
import com.markovski.usermanagement.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    private int userId;
    private List<AppUserResponse> userResponses;

    private AppUserRequest userRequest;

    private AppUserResponse userResponse;
    private final int validUserId = 1;
    private final int invalidUserId = -1;

    @Before
    public void setUp() {
        userId = 1;
        userResponses = Arrays.asList(
                new AppUserResponse(1, "John", "Doe", LocalDate.of(1996, 6, 4), "+359878655733", "jdoe@example.com"),
                new AppUserResponse(2, "Jane", "Smith", LocalDate.of(1998, 9, 12), "+359888123456", "jsmith@example.com")
        );
        userRequest = new AppUserRequest("John", "Doe", LocalDate.of(1996, 6, 4), "+359878655733", "jdoe@example.com");
        userResponse = new AppUserResponse(1, "John", "Doe", LocalDate.of(1996, 6, 4), "+359878655733", "jdoe@example.com");
        when(userService.getUserById(validUserId))
                .thenReturn(new AppUserResponse(validUserId, "John", "Doe", LocalDate.of(1996, 6, 4), "+359878655733", "jdoe@example.com"));

        when(userService.getUserById(invalidUserId))
                .thenThrow(new IllegalArgumentException("Invalid ID Input: " + invalidUserId));
    }

    @Test
    public void getAllUsers_ReturnsUserResponses() {
        when(userService.getAllUsers()).thenReturn(userResponses);

        ResponseEntity<List<AppUserResponse>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<AppUserResponse> actualUserResponses = response.getBody();
        assertEquals(userResponses, actualUserResponses);
    }

    @Test
    public void getUserById_ValidId_ReturnsUserResponse() {
        ResponseEntity<AppUserResponse> response = userController.getUserById(validUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        AppUserResponse actualUserResponse = response.getBody();
        assertNotNull(actualUserResponse);
        assertEquals(validUserId, actualUserResponse.getId());
    }

    @Test
    public void getUserById_InvalidId_ReturnsBadRequest() {
        ResponseEntity<AppUserResponse> response = userController.getUserById(invalidUserId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getUserById_UserNotFound_ReturnsNotFound() {
        when(userService.getUserById(anyInt()))
                .thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<AppUserResponse> response = userController.getUserById(validUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void addUser_ValidRequest_ReturnsCreated() {
        when(userService.addUser(userRequest)).thenReturn(userResponse);

        ResponseEntity<AppUserResponse> response = userController.addUser(userRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
        assertEquals(URI.create("/users/" + userResponse.getId()), response.getHeaders().getLocation());

        verify(userService, times(1)).addUser(userRequest);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUser_UserNotFound_ReturnsNotFound() {
        when(userService.updateUser(userId, userRequest)).thenThrow(new UserNotFoundException("User hasn't been found" + userId));

        ResponseEntity<AppUserResponse> response = userController.updateUser(userId, userRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userService, times(1)).updateUser(userId, userRequest);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void deleteUser_ExistingId_ReturnsDeletedUser() {
        when(userService.deleteUser(userId)).thenReturn(userResponse);

        ResponseEntity<AppUserResponse> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse, response.getBody());

        verify(userService, times(1)).deleteUser(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void deleteUser_UserNotFound_ReturnsNotFound() {
        when(userService.deleteUser(userId)).thenThrow(new UserNotFoundException("User hasn't been found" + userId));

        ResponseEntity<AppUserResponse> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userService, times(1)).deleteUser(userId);
        verifyNoMoreInteractions(userService);
    }
}