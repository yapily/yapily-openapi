package com.yapily.openapi.service;

import java.util.List;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.FinancialDataApi;
import org.openapitools.client.model.Account;
import org.openapitools.client.model.AccountApiListResponse;
import org.openapitools.client.model.ApiListResponseOfTransaction;
import org.openapitools.client.model.SortEnum;
import org.openapitools.client.model.Transaction;

public class AccountService {

    private final FinancialDataApi api;

    public AccountService(final FinancialDataApi apiClient) {
        this.api = apiClient;
    }

    public List<Account> getAccounts(String consent, String psuId, String psuCorpId, String psuIpAdd,
                                     Boolean raw) throws ApiException {
        AccountApiListResponse accounts = api.getAccounts(consent, psuId, psuCorpId, psuIpAdd, raw);
        return accounts.getData();
    }

    public List<Transaction> getTransactions(String accountId, String consent, String psuId, String psuCorporateId, String psuIpAddress, List<String> with, String from, String before,
                                             Integer limit, SortEnum sort, Integer offset, String cursor, Boolean raw)
            throws ApiException {
        ApiListResponseOfTransaction transactions = api.getTransactions(accountId, consent, psuId, psuCorporateId, psuIpAddress, with, from, before, limit, sort, offset, cursor, raw);
        return transactions.getData();
    }

}