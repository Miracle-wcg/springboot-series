package com.springboot.schedule.schedule;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.springboot.schedule.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: chengang.wu
 * @Date: 2020-01-20 14:59
 */
@Slf4j
public class SchedulingRunnable implements Runnable {
    private String beanName;
    private String methodName;
    private String params;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public void run() {
        log.info("定时任务开始执行 - bean: {}, 方法: {}, 参数: {}", beanName, methodName, params);
        long startTime = System.currentTimeMillis();
        Object target = SpringContextUtils.getBean(beanName);
        Method method = null;
        try {
            if (StringUtils.isNotEmpty(params)) {
                method = target.getClass().getDeclaredMethod(methodName, String.class);
                ReflectionUtils.makeAccessible(method);
                method.invoke(target, params);
            } else {
                method = target.getClass().getDeclaredMethod(methodName);
                ReflectionUtils.makeAccessible(method);
                method.invoke(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(String.format("定时任务开始执行 - bean: %s, 方法: %s, 参数: %s", beanName, methodName, params));
        }
        long times = System.currentTimeMillis() - startTime;
        log.info("定时任务执行结束 - bean: {}, 方法: {}, 参数: {}, 耗时: {} 毫秒", beanName, methodName, params, times);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SchedulingRunnable that = (SchedulingRunnable) obj;
        if (params == null) {
            return beanName.equals(that.beanName) && methodName.equals(that.methodName) && that.params == null;
        }

        return beanName.equals(that.beanName) && methodName.equals(that.methodName) && params.equals(that.params);
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }
        return Objects.hash(beanName, methodName, params);
    }
}
