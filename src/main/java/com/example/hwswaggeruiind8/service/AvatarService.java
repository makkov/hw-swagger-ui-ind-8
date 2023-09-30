package com.example.hwswaggeruiind8.service;

import com.example.hwswaggeruiind8.entity.Avatar;
import com.example.hwswaggeruiind8.entity.Student;
import com.example.hwswaggeruiind8.repository.AvatarRepository;
import com.example.hwswaggeruiind8.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {

    private final String avatarsDir;
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    public AvatarService(
            @Value("${path.to.avatars.folder}") String avatarsDir,
            StudentRepository studentRepository,
            AvatarRepository avatarRepository
    ) {
        this.avatarsDir = avatarsDir;
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(Long facultyId, MultipartFile avatarFile) throws IOException {
        logger.info("Был вызван метод uploadAvatar");
        Student student = studentRepository.findById(facultyId).get();
        // строчка ниже работает для MacOs. заменить для Windows: Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Path filePath = Path.of(new File("").getAbsolutePath() + avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = new Avatar();
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(long id) {
        logger.info("Был вызван метод findAvatar");
        return avatarRepository.findById(id).get();
    }

    private String getExtensions(String fileName) {
        logger.info("Был вызван метод getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
