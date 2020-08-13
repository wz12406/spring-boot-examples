package com.neo.eventbus;

import java.util.concurrent.Executor;

/**
 * @author Administrator
 * @date 2020/8/8 23:56
 * @desc
 */

public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
