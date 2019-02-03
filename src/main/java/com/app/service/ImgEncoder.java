package com.app.service;

import com.app.events.FinishEncodingImgEvent;
import com.app.events.StartEncodingImgEvent;
import com.app.model.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ImgEncoder {

    private Writer writer;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ImgEncoder(Writer writer, ApplicationEventPublisher applicationEventPublisher) {
        this.writer = writer;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async(value = "pullThread")
    public void imgEncode(File file) {

        applicationEventPublisher.publishEvent(new StartEncodingImgEvent(this));
        BufferedImage img = null;

        byte[] imageArray = null;

        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println(e);
        }

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        //convert to grayscale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                //calculate average
                int avg = (r + g + b) / 3;

                //replace RGB value with avg
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                img.setRGB(x, y, p);
            }
        }

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(img, "jpg", out);
            imageArray = out.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        writer.saveImage(new ImageModel(file.getName(), imageArray));
        applicationEventPublisher.publishEvent(new FinishEncodingImgEvent(this));
        file.delete();

    }

    @Bean(name="pullThread")
    AsyncTaskExecutor taskExecutor () {
        SimpleAsyncTaskExecutor t = new SimpleAsyncTaskExecutor();
        t.setConcurrencyLimit(5);
        return t;
    }

}