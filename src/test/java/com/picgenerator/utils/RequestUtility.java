package com.picgenerator.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RequestUtility {

    String baseUrl = "http://localhost:8080/";

    public <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz)
            throws IOException {

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }

    public HttpResponse getImageEndpoint(String endpoint) throws IOException {
        String imageMineType = "image";

        HttpUriRequest request = new HttpGet(baseUrl + endpoint);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Assert that the endpoint is online
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

        // Assert mime type is a image
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertThat(mimeType, containsString(imageMineType));

        return response;
    }


    public HttpResponse getJsonEndpoint(String endpoint) throws IOException {
        String imageMineType = "application/json";

        HttpUriRequest request = new HttpGet(baseUrl + endpoint);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Assert that the endpoint is online
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

        // Assert mime type is a JSON
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertThat(mimeType, containsString(imageMineType));

        return response;
    }

    public void getEndpointNonAuthenticated(String endpoint) throws IOException {
        HttpUriRequest request = new HttpGet(baseUrl + endpoint);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Assert that the endpoint is online
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_UNAUTHORIZED));
    }
}
