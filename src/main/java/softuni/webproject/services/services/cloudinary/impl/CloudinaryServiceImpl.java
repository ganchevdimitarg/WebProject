package softuni.webproject.services.services.cloudinary.impl;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softuni.webproject.services.services.cloudinary.CloudinaryService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp-file", multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        return cloudinary.uploader().upload(file, new HashMap()).get("url").toString();
    }
}
