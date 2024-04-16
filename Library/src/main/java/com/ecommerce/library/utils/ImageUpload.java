package com.ecommerce.library.utils;


import jakarta.mail.Multipart;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUpload {
    private final String UPLOAD_FOLDER = "D:\\Ecommerce-Springboot\\Admin\\src\\main\\resources\\static\\img\\image-product";
    public boolean uploadFile(MultipartFile file){
        try{
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_FOLDER + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkExist(MultipartFile multipartFile){
        boolean isExist = true;
        try{
            File file = new File(UPLOAD_FOLDER + "\\" + multipartFile.getOriginalFilename());
            isExist = file.exists();
        }catch (Exception e){
            e.printStackTrace();
        }
        return isExist;
    }
}
