package com.yapily.openapi.service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConsentService {

    private final OkHttpClient client;

    public ConsentService() {
        this.client = new OkHttpClient().newBuilder()
                                        .build();
    }

    /**
     * This function is for example purposes only.
     * The expected flow involves the user retrieving his consentToken using the URL returned as part of the Authorisation process.
     * We can help you on https://docs.yapily.com/support/".
     */
    public String fetchConsentFromAuth(String authUrl, String basePath) throws IOException {
        String state = authUrl.split("state=")[1].split("&")[0];
        //         This request creates a call to validate the returned consent
        JsonObject json = new JsonObject();
        json.addProperty("code", "FAKE_CODE");
        json.addProperty("id_token", "FAKE_ID_TOKE");
        json.addProperty("state", state);
        MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json.toString(), jsonType);
        Request authRequest = new Request.Builder()
                .url(basePath + "/exchange-code/")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(authRequest).execute();
        String consentCallResponseBody = response.body().string();
        // asserted as not null in the test
        String consent = null;
        if (consentCallResponseBody.contains("consentToken")) {
            JsonObject consentsResponse = JsonParser.parseString(consentCallResponseBody).getAsJsonObject();
            consent = consentsResponse.get("consentToken").getAsString();
        }
        return consent;
    }
}