package com.company.beatyclub.service;


import com.company.beatyclub.exception.IncorrectInputException;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



@Service
public class FileUploadService {
    private static final String FILE_UPLOAD_EXCEPTION = "Could not save image file: %s";
    private static final String DIRECTORY_CREATE_EXCEPTION = "Could not create directory with name: %s";
    private static final String IMAGE_SIZE_EXCEPTION = "Max image size should be %d bytes, your image size is %d bytes";
    private static final String IMAGE_RESOLUTION_EXCEPTION = "Image %s should be more than %d, your image %s is %d";
    private static final String UPLOAD_LOCATION = "/images";
    private static final String UPLOAD_PLUG = "/upload/test/test.png";
    private static final Long IMAGE_SIZE_MB = 5L;
    private static final Long IMAGE_SIZE_B = IMAGE_SIZE_MB * 1024 * 1024;
    private static final Long MIN_IMAGE_WIDTH = 200L;
    private static final Long MIN_IMAGE_HEIGHT = 200L;

    public String uploadImage(String uploadDir, String fileName, MultipartFile multipartFile) throws FileUploadException, IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (multipartFile.getSize() > IMAGE_SIZE_B) {
            throw new IncorrectInputException(
                    String.format(IMAGE_SIZE_EXCEPTION, IMAGE_SIZE_B, multipartFile.getSize()));
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width < MIN_IMAGE_WIDTH) {
                throw new IncorrectInputException(
                        String.format(IMAGE_RESOLUTION_EXCEPTION, "width", MIN_IMAGE_WIDTH, "width", width));
            }
            if (height < MIN_IMAGE_HEIGHT) {
                throw new IncorrectInputException(
                        String.format(IMAGE_RESOLUTION_EXCEPTION, "height", MIN_IMAGE_HEIGHT, "height", height));
            }
        } catch (IOException | IncorrectInputException e) {
            e.printStackTrace();
        }

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new FileUploadException(String.format(DIRECTORY_CREATE_EXCEPTION, fileName));
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new FileUploadException(String.format(FILE_UPLOAD_EXCEPTION, fileName));
        }

        String actualPath = String.format("%s/%s", uploadDir, fileName);
        System.out.println(actualPath);
        return actualPath.substring(actualPath.indexOf(UPLOAD_LOCATION));
    }

    public void deleteFile(String filePath) throws FileUploadException {
        if (filePath.contains(UPLOAD_PLUG)) {
            return;
        }
        if (!filePath.contains(UPLOAD_LOCATION)) {
            throw new IncorrectInputException("Wrong uploaded file path");
        }
        try {
            FileUtils.forceDelete(new File("target" + filePath));
        } catch (IOException e) {
            throw new FileUploadException(String.format("Can't delete file with path: %s", filePath));
        }
    }
}