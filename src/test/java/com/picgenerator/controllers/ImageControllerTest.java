package com.picgenerator.controllers;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class ImageControllerTest {

    private void endpointIsUp(String endpoint) throws IOException {
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

    @Test
    public void tagEndpointsIsUp() throws IOException {
        endpointIsUp("tag");
    }
}