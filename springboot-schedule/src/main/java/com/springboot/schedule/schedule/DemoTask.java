package com.springboot.schedule.schedule;

import org.springframework.stereotype.Component;

/**
 * @Author: chengang.wu
 * @Date: 2020-01-20 15:31
 */
@Component("demoTask")
public class DemoTask {
    public void taskWithParam(String params) {
        System.out.println("执行有参数的任务: " + params);
    }

    public void taskNonhParam() {
        System.out.println("执行无参数的任务");
    }
}
