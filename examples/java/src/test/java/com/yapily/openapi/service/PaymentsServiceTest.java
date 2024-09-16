package com.yapily.openapi.service;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.AuthorisationsApi;
import org.openapitools.client.api.PaymentsApi;
import org.openapitools.client.model.AccountAuthorisationRequest;
import org.openapitools.client.model.ApiResponseOfAccountAuthorisationResponse;
import org.openapitools.client.model.ApiResponseOfPaymentResponse;
import org.openapitools.client.model.PaymentRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentsServiceTest {

    @Mock
    private PaymentsApi apiClient;
    @Mock
    private PaymentRequest paymentRequest;

    private PaymentsService service;

    private static final String subAppId = "bbcc4621-d88f-4a94-ae2f-b38072bf5087";

    @BeforeEach
    void setUp() {
        service = new PaymentsService(apiClient);
    }

    @Test
    void createPaymentRequestCall() throws ApiException {
        ApiResponseOfPaymentResponse paymentResponse = mock(ApiResponseOfPaymentResponse.class);
        when(apiClient.createPayment(anyString(), any(PaymentRequest.class), anyString(), anyString(), anyString(), any(UUID.class), anyBoolean())).thenReturn(paymentResponse);
        assertNotNull(service.createPaymentRequestCall("i-am-a-consent", paymentRequest, "", "", "", UUID.fromString(subAppId), false));
    }

    @Test
    void createPaymentRequest() {
        assertNotNull(service.createPaymentRequest());
    }
}
