package com.picgenerator.controllers;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.picgenerator.utils.GoogleCloudImageRecognition;
import com.picgenerator.utils.ImageUtility;
import com.picgenerator.utils.RequestUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class ImageControllerTest {

    @Autowired
    RequestUtility requestUtility = new RequestUtility();
    @Autowired
    ImageUtility imageUtility = new ImageUtility();

    @Test
    @DisplayName("Get image by ID without auth")
    void getImageById() throws IOException {
        requestUtility.getEndpointNonAuthenticated("image?id=1");
    }

    @Test
    @DisplayName("Get image by Tag")
    void getImageByTagId() throws IOException {
        requestUtility.getImageEndpoint("generate?t=1");
    }

    @Nested
    @DisplayName("Generate image")
    class Generate {

        @Test
        @DisplayName("No option")
        void generateImage() throws  IOException {
            requestUtility.getImageEndpoint("generate");
        }

        @Test
        @DisplayName("Width Height")
        void generateImageWidthHeight() throws  IOException {
            Integer width = 500;
            Integer height = 500;
            String endpoint = "generate?w=" + width + "&h=" + height;
            requestUtility.getImageEndpoint(endpoint);
            BufferedImage img = imageUtility.getImageFromResponse(endpoint);
            assertThat(width, equalTo(img.getWidth()));
            assertThat(height, equalTo(img.getHeight()));
        }

        @Test
        @DisplayName("Width Height Resize")
        void generateImageWidthHeightResize() throws  IOException {
            Integer width = 500;
            Integer height = 500;
            String endpoint = "generate?w=" + width + "&h=" + height + "&opt=2";
            requestUtility.getImageEndpoint(endpoint);
            BufferedImage img = imageUtility.getImageFromResponse(endpoint);

            if (img.getWidth() == width || img.getHeight() == height) {
                assertThat(true, equalTo(true));
            } else {
                assertThat(false, equalTo(true));
            }
        }

        @Test
        @DisplayName("Text (pas fiable à 100%)")
        void generateText() throws IOException {
            String text = "generator";
            String endpoint = "generate?t=1&w=500&h=600&text=" + text + "&t-size=40";
            requestUtility.getImageEndpoint(endpoint);
            BufferedImage img = imageUtility.getImageFromResponse(endpoint);
            List<AnnotateImageResponse> responses = GoogleCloudImageRecognition.detectText(img);
            assertThat(true, equalTo(GoogleCloudImageRecognition.TestIsInList(text, responses)));
        }

    }


    @Test
    @DisplayName("Get all images without auth")
    void getAllImage() throws IOException {
        requestUtility.getEndpointNonAuthenticated("images/all");
    }
}