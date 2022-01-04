package com.picgenerator.utils;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GoogleCloudImageRecognition {

    public static List<AnnotateImageResponse> detectText(BufferedImage buffImg) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "jpeg", os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        ByteString imgBytes = ByteString.readFrom(is);

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            return response.getResponsesList();
        }
    }

    public static boolean TestIsInList(String text, List<AnnotateImageResponse> responses) {
        List<String> arr = new ArrayList<String>();

        for (AnnotateImageResponse res : responses) {
            for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                arr.add(annotation.getDescription());
            }
        }

        return arr.contains(text);
    }
}