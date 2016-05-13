package se.johan.communitysitev2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Johan on 13-May-16.
 */
@Controller
@RequestMapping(value = "/media")
public class MediaController {

    // Handles file upload.
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadImage(MultipartHttpServletRequest request) {

        System.out.println(" UPLOAD FILE !");
        Iterator<String> itrator = request.getFileNames();
        MultipartFile multiFile = request.getFile(itrator.next());
        try {
            // just to show that we have actually received the file
            System.out.println("File Length:" + multiFile.getBytes().length);
            System.out.println("File Type:" + multiFile.getContentType());
            String fileName = multiFile.getOriginalFilename();
            System.out.println("File Name:" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}