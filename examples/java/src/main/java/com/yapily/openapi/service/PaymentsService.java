package com.yapily.openapi.service;

import java.math.BigDecimal;
import java.util.Set;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.AuthorisationsApi;
import org.openapitools.client.api.PaymentsApi;
import org.openapitools.client.model.AccountIdentification;
import org.openapitools.client.model.AccountIdentificationType;
import org.openapitools.client.model.Amount;
import org.openapitools.client.model.ApiResponseOfPaymentResponse;
import org.openapitools.client.model.ApiResponseOfPaymentResponses;
import org.openapitools.client.model.Payee;
import org.openapitools.client.model.PaymentRequest;
import org.openapitools.client.model.PaymentResponses;
import org.openapitools.client.model.PaymentType;

public class PaymentsService {

    private final PaymentsApi api;

    public PaymentsService(final PaymentsApi apiClient) {
        this.api = apiClient;
    }

    public ApiResponseOfPaymentResponse createPaymentRequestCall(String consent, PaymentRequest paymentRequest, String psuId, String psuCorporateId, String psuIpAddress, Boolean raw)
            throws ApiException {
        return api.createPayment(consent, paymentRequest, psuId, psuIpAddress, psuCorporateId, raw);
    }

    public PaymentResponses fetchPayment(String id, String consent, String psuId, String psuCorporateId, String psuIpAddress, Boolean raw) throws ApiException {
        ApiResponseOfPaymentResponses paymentsResponses = api.getPayments(id, consent, psuId, psuCorporateId, psuIpAddress, raw);
        return paymentsResponses.getData();
    }

    public PaymentRequest createPaymentRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        Amount amount = new Amount();
        amount.setAmount(BigDecimal.TEN);
        amount.setCurrency("GBP");
        Payee payee = new Payee();
        payee.setName("Java user");
        AccountIdentification accId = new AccountIdentification();
        accId.setType(AccountIdentificationType.PAN);
        accId.setIdentification("23819264");
        Set<AccountIdentification> accountIdentifications = Set.of(accId);
        payee.setAccountIdentifications(accountIdentifications);
        paymentRequest.setAmount(amount);
        paymentRequest.setPaymentIdempotencyId("Test");
        paymentRequest.setReference("Test reference");
        paymentRequest.setType(PaymentType.DOMESTIC_PAYMENT);
        paymentRequest.setPayee(payee);
        return paymentRequest;
    }
}
