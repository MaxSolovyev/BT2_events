package com.app.events;

import org.springframework.context.ApplicationEvent;

public class FinishEncodingImgEvent extends ApplicationEvent {
    public FinishEncodingImgEvent(Object source) {
        super(source);
    }
}
