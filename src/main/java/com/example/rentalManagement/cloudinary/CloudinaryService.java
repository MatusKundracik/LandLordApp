package com.example.rentalManagement.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

  private final Cloudinary cloudinary;

  public String uploadImage(MultipartFile file) {
    try {
      Map result =
          cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "items"));
      return (String) result.get("secure_url");
    } catch (IOException e) {
      throw new RuntimeException("Image upload failed");
    }
  }

  public void deleteImage(String imageUrl) {
    try {
      String publicId = extractPublicId(imageUrl);
      cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    } catch (IOException e) {
      throw new RuntimeException("Image delete failed");
    }
  }

  private String extractPublicId(String imageUrl) {
    // z URL vytiahne public_id napr. "items/abc123"
    String[] parts = imageUrl.split("/");
    String fileName = parts[parts.length - 1];
    return "items/" + fileName.split("\\.")[0];
  }
}
