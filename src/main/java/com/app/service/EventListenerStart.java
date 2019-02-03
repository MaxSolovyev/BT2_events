package com.app.service;

import com.app.events.StartEncodingImgEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerStart implements ApplicationListener<StartEncodingImgEvent> {
    @Override
    public void onApplicationEvent(StartEncodingImgEvent event) {
        System.out.println("Start processing - ");
    }
}