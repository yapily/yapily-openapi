package com.yapily.openapi.service;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsersApi;
import org.openapitools.client.model.ApiResponseOfUserDeleteResponse;
import org.openapitools.client.model.ApplicationUser;
import org.openapitools.client.model.NewApplicationUser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UsersApi apiClient;

    private UserService userService;

    @BeforeEach
    public void setup (){
         userService = new UserService(apiClient);
    }

    @Test
    void createUser() throws ApiException {
        NewApplicationUser newAppUser = mock(NewApplicationUser.class);
        ApplicationUser appUser = mock(ApplicationUser.class);
        when(apiClient.addUser(any(NewApplicationUser.class))).thenReturn(appUser);
        assertNotNull(userService.createUser(newAppUser));
    }

    @Test
    void deleteUser() throws ApiException {
        ApiResponseOfUserDeleteResponse appDeletedUser = mock(ApiResponseOfUserDeleteResponse.class);
        when(apiClient.deleteUser(any(UUID.class))).thenReturn(appDeletedUser);
        assertNotNull(userService.deleteUser(UUID.randomUUID()));
    }
}