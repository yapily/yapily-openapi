package com.yapily.openapi.service;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.AuthorisationsApi;
import org.openapitools.client.model.AccountAuthorisationRequest;
import org.openapitools.client.model.ApiResponseOfAccountAuthorisationResponse;
import org.openapitools.client.model.ApiResponseOfPaymentAuthorisationRequestResponse;
import org.openapitools.client.model.PaymentAuthorisationRequest;
import org.openapitools.client.model.PaymentRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorisationServiceTest {

    @Mock
    private AuthorisationsApi apiClient;

    @Mock
    private PaymentRequest paymentReq;

    private AuthorisationService service;

    @BeforeEach
    void setUp() {
        service = new AuthorisationService(apiClient);
    }

    @Test
    void createAccountAuthRequest() throws ApiException {
        ApiResponseOfAccountAuthorisationResponse accResponse = mock(ApiResponseOfAccountAuthorisationResponse.class);
        when(apiClient.initiateAccountRequest(any(AccountAuthorisationRequest.class), anyString(), anyString(), anyString(), anyBoolean())).thenReturn(accResponse);
        assertNotNull(service.createAccountAuthRequest(UUID.randomUUID(), "institution", "", "", "", false));
    }

    @Test
    void createPaymentAuthRequest() throws ApiException {
        ApiResponseOfPaymentAuthorisationRequestResponse paymentResp = mock(ApiResponseOfPaymentAuthorisationRequestResponse.class);
        when(apiClient.createPaymentAuthorisation(any(PaymentAuthorisationRequest.class), anyString(), anyString(), anyString(), anyBoolean())).thenReturn(paymentResp);
        assertNotNull(service.createPaymentAuthRequest(UUID.randomUUID().toString(), "", "", "", false, paymentReq, ""));
    }
}