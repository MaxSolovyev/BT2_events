package com.app.service;

import com.app.events.FinishEncodingImgEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerFinish implements ApplicationListener<FinishEncodingImgEvent> {
    @Override
    public void onApplicationEvent(FinishEncodingImgEvent event) {
        System.out.println("Finish processing - ");
    }
}