package com.picgenerator.controllers;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class ImageControllerTest {

    RequestUtility requestUtility;

    @Test
    public void tagEndpointsIsUp() throws IOException {
        requestUtility.jsonEndpoint("tag");
    }
}