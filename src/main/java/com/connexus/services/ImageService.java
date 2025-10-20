package com.connexus.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadContactImage(MultipartFile contactImage, String fileName);

    String getUrlFromPublicId(String publicId);
}
