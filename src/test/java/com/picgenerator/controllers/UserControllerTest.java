package com.picgenerator.controllers;

import com.picgenerator.RequestUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Autowired
    RequestUtility requestUtility = new RequestUtility();

    @Test
    void getUser() throws IOException {
        requestUtility.getJsonEndpoint("user?id=1");
    }

    @Test
    void getUsers() throws IOException {
        requestUtility.getJsonEndpoint("users/all");
    }
}