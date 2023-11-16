package com.yapily.openapi.service;

import java.util.UUID;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.UsersApi;
import org.openapitools.client.model.ApiResponseOfUserDeleteResponse;
import org.openapitools.client.model.ApplicationUser;
import org.openapitools.client.model.NewApplicationUser;

public class UserService {

    private final UsersApi api;

    public UserService(final UsersApi apiClient) {
        this.api = apiClient;
    }

    public ApplicationUser createUser(NewApplicationUser applicationUser) throws ApiException {
        return api.addUser(applicationUser);
    }

    public ApiResponseOfUserDeleteResponse deleteUser(UUID userUuid) throws ApiException {
        return api.deleteUser(userUuid);
    }
}