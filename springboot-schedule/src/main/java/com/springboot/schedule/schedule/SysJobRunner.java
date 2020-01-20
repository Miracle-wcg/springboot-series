package com.springboot.schedule.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.springboot.schedule.entity.SysJob;
import com.springboot.schedule.enums.SysJobStatus;
import com.springboot.schedule.service.SysJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chengang.wu
 * @Date: 2020-01-20 17:30
 */
@Slf4j
@Service
public class SysJobRunner implements CommandLineRunner {
    @Autowired
    private SysJobService sysJobService;
    @Autowired
    private CronTaskRegister cronTaskRegister;

    @Override
    public void run(String... args) throws Exception {
        List<SysJob> list = sysJobService.list(new LambdaQueryWrapper<SysJob>().eq(SysJob::getJobStatus, SysJobStatus.NORMAL.id()));
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(sysJob -> {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegister.addCronTask(task, sysJob.getCronExpression());
            });
            log.info("定时任务加载完毕");
        }
    }
}
