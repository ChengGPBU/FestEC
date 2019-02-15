package com.diabin.latte.app.delegate.web.event;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.HashMap;

/**
 * package: com.diabin.latte.app.delegate.web.event
 * author: chengguang
 * created on: 2019/2/12 下午4:33
 * description:
 */
public class EventManager {
    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NotNull String name, Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NotNull String action) {
        Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
