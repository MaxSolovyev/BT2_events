package com.app.events;

import org.springframework.context.ApplicationEvent;

public class StartEncodingImgEvent extends ApplicationEvent {
    public StartEncodingImgEvent(Object source) {
        super(source);
    }
}
