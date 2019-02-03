package com.app.service;

import com.app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileLoader {
    private final ImgEncoder imgEncoder;
    private final ImageRepository imageRepository;
    private final Writer writer;

    @Autowired
    public FileLoader(ImgEncoder imgEncoder, ImageRepository imageRepository, Writer writer) {
        this.imgEncoder = imgEncoder;
        this.imageRepository = imageRepository;
        this.writer = writer;
    }

    private final String directory = "c:\\images";

    public void selectFilesFromPath() {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                imgEncoder.imgEncode(file);

//                saveImageToDB(file);
//                System.out.println(file.getName());
            }
        }
    }

//    private void saveImageToDB(File file) {
//        byte[] image = null;
//        try {
//            image = Files.readAllBytes(file.toPath());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        imgEncoder.imgEncode();
//
//        BufferedImage imageBuffer = imgEncoder.imgEncode(file);
//
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
//            ImageIO.write(imageBuffer, "jpg", out);
//            image = out.toByteArray();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        ImageModel imageModel = new ImageModel(file.getName(), image);
//        writer.saveImage(imageModel);
//    }
}