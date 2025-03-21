package com.yapily.openapi.service;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.AuthorisationsApi;
import org.openapitools.client.model.AccountAuthorisationRequest;
import org.openapitools.client.model.ApiResponseOfAccountAuthorisationResponse;
import org.openapitools.client.model.ApiResponseOfPaymentAuthorisationRequestResponse;
import org.openapitools.client.model.PaymentAuthorisationRequest;
import org.openapitools.client.model.PaymentRequest;

import java.util.UUID;

public class AuthorisationService {

    private final AuthorisationsApi api;

    public AuthorisationService(final AuthorisationsApi apiClient) {
        this.api = apiClient;
    }

    public ApiResponseOfAccountAuthorisationResponse createAccountAuthRequest(final UUID userUuid, final String institutionId, String psuId, String psuCorpId,
                                                                              String psuIpAdd, UUID subAppId) throws ApiException {
        AccountAuthorisationRequest accountRequest = new AccountAuthorisationRequest();
        accountRequest.setUserUuid(userUuid);
        accountRequest.setInstitutionId(institutionId);
        return api.initiateAccountRequest(accountRequest, psuId, psuCorpId, psuIpAdd, subAppId);
    }

    public ApiResponseOfPaymentAuthorisationRequestResponse createPaymentAuthRequest(final String applicationUserUuid, String psuId, String psuCorpId, String psuIpAdd, UUID subAppId, PaymentRequest paymentRequest, String institutionId) throws ApiException {
        PaymentAuthorisationRequest paymentAuthorisationRequest = new PaymentAuthorisationRequest();
        paymentAuthorisationRequest.setPaymentRequest(paymentRequest);
        paymentAuthorisationRequest.setApplicationUserId(applicationUserUuid);
        paymentAuthorisationRequest.setInstitutionId(institutionId);
        return api.createPaymentAuthorisation(paymentAuthorisationRequest, psuId, psuCorpId, psuIpAdd, subAppId);
    }


}