package com.yapily.openapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.api.AuthorisationsApi;
import org.openapitools.client.api.FinancialDataApi;
import org.openapitools.client.api.PaymentsApi;
import org.openapitools.client.api.UsersApi;
import org.openapitools.client.auth.HttpBasicAuth;
import org.openapitools.client.model.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.fail;

public class SuiteTest {

    private static final String INSTITUTION = "mock-sandbox";
    private static final String psuId = "";
    private static final String psuCorpId = "";
    private static final String psuIpAdd = "";
    private static final String CONSENT = "consentToken";


    private final ApiClient defaultClient = Configuration.getDefaultApiClient();
    private final HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");

    private UserService userService;
    private AuthorisationService authService;
    private PaymentsService paymentsService;
    private ConsentService consentService;
    private AccountService accountService;

    @BeforeEach
    void setup() {
        basicAuth.setUsername(System.getenv("APP_UUID"));
        basicAuth.setPassword(System.getenv("APP_SECRET"));
        System.out.println("APP ID is -> ["+System.getenv("APP_UUID")+"]");
        final UsersApi usersApi = new UsersApi(defaultClient);
        final AuthorisationsApi authApi = new AuthorisationsApi(defaultClient);
        final PaymentsApi paymentsApi = new PaymentsApi(defaultClient);
        final FinancialDataApi finApi = new FinancialDataApi(defaultClient);

        userService = new UserService(usersApi);
        authService = new AuthorisationService(authApi);
        consentService = new ConsentService();
        accountService = new AccountService(finApi);
        paymentsService = new PaymentsService(paymentsApi);
    }

    @Test
    void testAccountFlow() {
        try {
            // user setup
            NewApplicationUser newUser = new NewApplicationUser();
            // create the user
            ApplicationUser confirmedUser = userService.createUser(newUser);
            Assertions.assertNotNull(confirmedUser);
            // account request
            ApiResponseOfAccountAuthorisationResponse accountReqResp = authService.createAccountAuthRequest(confirmedUser.getUuid(), INSTITUTION, psuId, psuCorpId, psuIpAdd, false);

            AccountAuthorisationResponse accountReqRespData = accountReqResp.getData();
            Assertions.assertNotNull(accountReqRespData);
            // validate the state and then
            // fetch the token used for other calls
            String consentToken = consentService.fetchConsentFromAuth(accountReqRespData.getAuthorisationUrl(), defaultClient.getBasePath());
            // then fetch the accounts
            List<Account> accounts = accountService.getAccounts(consentToken, psuId, psuCorpId, psuIpAdd, false);
            Assertions.assertFalse(accounts.isEmpty());
            for (Account acc : accounts) {
                List<Transaction> transactions = accountService.getTransactions(acc.getId(), consentToken, psuId, psuCorpId, psuIpAdd, null, null, null, null, null, null, null, false);
                Assertions.assertFalse(transactions.isEmpty());
            }
            // user removal
            // DeleteUser fails with timeout connection error
            ApiResponseOfUserDeleteResponse deletedUser = userService.deleteUser(confirmedUser.getUuid());
            Assertions.assertNotNull(deletedUser);
        } catch (ApiException | IOException e) {
            handleException(e);
        }
    }

    @Test
    void testPaymentsFlow() {
        try {
            // user setup
            NewApplicationUser newUser = new NewApplicationUser();
            newUser.setReferenceId("java user");
            newUser.setApplicationUserId(UUID.randomUUID().toString());
            // create the user
            ApplicationUser confirmedUser = userService.createUser(newUser);
            Assertions.assertNotNull(confirmedUser);
            // payment request and payment auth request
            PaymentRequest paymentRequest = paymentsService.createPaymentRequest();
            ApiResponseOfPaymentAuthorisationRequestResponse paymentsReqResp =
                    authService.createPaymentAuthRequest(confirmedUser.getApplicationUserId(), psuId, psuCorpId, psuIpAdd, false,
                                                         paymentRequest, INSTITUTION);
            PaymentAuthorisationRequestResponse paymentsReqRespData = paymentsReqResp.getData();
            Assertions.assertNotNull(paymentsReqRespData);
            // validate the state and then
            // fetch the token used for other calls
            String consentToken = consentService.fetchConsentFromAuth(paymentsReqRespData.getAuthorisationUrl(), defaultClient.getBasePath());
            Assertions.assertNotNull(consentToken);
            // then fetch the accounts
            ApiResponseOfPaymentResponse paymentResponse = paymentsService.createPaymentRequestCall(consentToken, paymentRequest, psuId, psuCorpId, psuIpAdd, false);
            PaymentResponse paymentsRespData = paymentResponse.getData();
            Assertions.assertNotNull(paymentsRespData);
            PaymentResponses payments = paymentsService.fetchPayment(paymentsRespData.getId(), consentToken, psuId, psuCorpId, psuIpAdd, false);
            Assertions.assertNotNull(payments);
            // user removal
            // DeleteUser fails with timeout connection error
            ApiResponseOfUserDeleteResponse deletedUser = userService.deleteUser(confirmedUser.getUuid());
            Assertions.assertNotNull(deletedUser);
        } catch (ApiException | IOException e) {
            handleException(e);
        }
    }

    private static void handleException(Exception e) {
        if (e instanceof ApiException) {
            fail("Should not have thrown APIException: " + ((ApiException) e).getResponseBody());
        } else {
            fail("Should not have thrown exception: " + e.getStackTrace());
        }
    }
}
