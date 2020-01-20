package com.springboot.schedule.schedule;

import java.util.concurrent.ScheduledFuture;

/**
 * @Author: chengang.wu
 * @Date: 2020-01-20 14:58
 */
public final class ScheduledTask {
    volatile ScheduledFuture<?> future;

    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
