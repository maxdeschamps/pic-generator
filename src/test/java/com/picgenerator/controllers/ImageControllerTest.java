package com.picgenerator.controllers;

import com.picgenerator.RequestUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageControllerTest {

    @Autowired
    RequestUtility requestUtility = new RequestUtility();

    @Test
    void getImage() throws IOException {
        requestUtility.getImageEndpoint("image?id=1");
    }

    @Test
    void getAllImage() throws IOException {
        requestUtility.getJsonEndpoint("images/all");
    }
}