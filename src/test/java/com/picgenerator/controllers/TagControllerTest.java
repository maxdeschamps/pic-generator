package com.picgenerator.controllers;

import com.picgenerator.RequestUtility;
import com.picgenerator.entities.Tag;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TagControllerTest {

    @Autowired
    private RequestUtility requestUtility = new RequestUtility();

    @Test
    public void testGetTag() throws IOException {
        HttpResponse response = requestUtility.getJsonEndpoint("tag?id=1");
        Tag ressource = requestUtility.retrieveResourceFromResponse(response, Tag.class);
        assertThat(1, is(ressource.getId()));

    }

    @Test
    public void testGetTags() throws IOException {
        HttpResponse response =  requestUtility.getJsonEndpoint("tags/all");
    }
}