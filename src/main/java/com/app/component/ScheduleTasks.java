package com.app.component;

import com.app.service.FileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleTasks {

    private final FileLoader fileLoader;



@Autowired
    public ScheduleTasks(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }

    @Scheduled(fixedRate = 5_000)
    private void checkFiles() {

        fileLoader.selectFilesFromPath();

    }

}