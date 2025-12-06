package com.example.foine.cloudinary;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {
    

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) {
        try {
            
            long maxFileSize = 10 * 1024 * 1024;

            if (file.getSize() > maxFileSize) {
                throw new RuntimeException("Cannot upload images greater than 10mb.");
            }
            
            if (!file.getContentType().startsWith("image/")) {
                throw new RuntimeException("Upload must be an image file.");
            }

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Image upload failed.");
        }
    }
}
