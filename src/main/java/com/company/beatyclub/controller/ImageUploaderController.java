package com.company.beatyclub.controller;

import com.company.beatyclub.service.FileUploadService;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@RestController
@CrossOrigin(origins = "*")
public class ImageUploaderController {
    @Value("${application.upload.path}")
    private String uploadDirectory;
    private final FileUploadService fileUploadServiceImpl;

    @Autowired
    public ImageUploaderController(FileUploadService fileUploadServiceImpl) {
        this.fileUploadServiceImpl = fileUploadServiceImpl;
    }


    @PostMapping("/upload-image")
    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    public String uploadPhoto(@RequestParam("image") MultipartFile image,
                              @RequestParam("folder") String folder) throws FileUploadException, IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uploadDir = String.format("%s/%s", uploadDirectory, folder);

        return fileUploadServiceImpl.uploadImage(uploadDir, fileName, image);
    }


    @DeleteMapping("/delete-file")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteFile(@RequestParam("filePath") String filePath) throws FileUploadException {
        fileUploadServiceImpl.deleteFile(filePath);
        return filePath + " successfully deleted.";
    }

    @GetMapping(value = "/image/{folder}/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable String folder,
                                                      @PathVariable String name) throws IOException {
        System.out.println("./upload/" + folder + "/" + name + ".jpg");
        InputStream in = new FileInputStream("target/classes/" + folder + "/" + name + ".jpg");

        return IOUtils.toByteArray(in);
    }
}
