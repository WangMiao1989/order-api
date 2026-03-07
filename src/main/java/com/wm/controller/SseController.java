package com.wm.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class SseController {

    // 线程安全的列表，保存所有客户端连接
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /**
     * SSE 连接端点
     */
    @RequestMapping(path = "/sse/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamOrders() {
        SseEmitter emitter = new SseEmitter(0L); // 0表示永不超时
        // 注册回调：当 emitter 完成或超时或出错时，从列表中移除
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        // 加入列表
        emitters.add(emitter);
        return emitter;
    }

    // order 更新触发
    public void notifyOrderUpdated() {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("order-updated")
                        .data("update"));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }
    
    // table 更新触发
    public void notifyTableUpdated() {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("table-updated")
                        .data("update"));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }
}