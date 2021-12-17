package com.picgenerator.controllers;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RequestUtility {

    public void imageEndpoint(String endpoint) throws IOException {
        String imageMineType = "image";

        HttpUriRequest request = new HttpGet("http://localhost/" + endpoint);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Assert that the endpoint is online
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

        // Assert mime type is a image
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertThat(mimeType, containsString(imageMineType));
    }


    public void jsonEndpoint(String endpoint) throws IOException {
        String imageMineType = "application/json";

        HttpUriRequest request = new HttpGet("http://localhost/" + endpoint);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Assert that the endpoint is online
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

        // Assert mime type is a image
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertThat(mimeType, containsString(imageMineType));
    }
}
