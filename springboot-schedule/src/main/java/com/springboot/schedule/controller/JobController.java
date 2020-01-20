package com.springboot.schedule.controller;

import com.springboot.schedule.config.ApiResponse;
import com.springboot.schedule.entity.SysJob;
import com.springboot.schedule.enums.SysJobStatus;
import com.springboot.schedule.schedule.CronTaskRegister;
import com.springboot.schedule.schedule.SchedulingRunnable;
import com.springboot.schedule.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chengang.wu
 * @Date: 2020-01-20 15:43
 */
@RestController
@RequestMapping("schedule")
public class JobController {
    @Autowired
    private SysJobService sysJobService;
    @Autowired
    private CronTaskRegister cronTaskRegister;

    @PostMapping("create")
    public ApiResponse createTask(@RequestBody SysJob sysJob) {
        boolean success = sysJobService.save(sysJob);

        if (!success) {
            return ApiResponse.fail("新增失败");
        } else {
            if (sysJob.getJobStatus().equals(SysJobStatus.NORMAL.id())) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegister.addCronTask(task, sysJob.getCronExpression());
            }
        }
        return ApiResponse.ok();
    }

    @PostMapping("update")
    public ApiResponse updateTask(@RequestBody SysJob sysJob) {
        SysJob existedJob = sysJobService.getById(sysJob.getId());
        boolean success = sysJobService.updateById(sysJob);
        if (!success) {
            return ApiResponse.fail("编辑失败");
        } else {
            if (existedJob.getJobStatus().equals(SysJobStatus.NORMAL.id())) {
                SchedulingRunnable task = new SchedulingRunnable(existedJob.getBeanName(), existedJob.getMethodName(), existedJob.getMethodParams());
                cronTaskRegister.removeCronTask(task);
            }
            if (sysJob.getJobStatus().equals(SysJobStatus.NORMAL.id())) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegister.addCronTask(task, sysJob.getCronExpression());
            }
        }
        return ApiResponse.ok();
    }

    @PutMapping("remove/{id}")
    public ApiResponse removeTask(@PathVariable("id") Integer id) {

        boolean success = sysJobService.removeById(id);
        if (!success) {
            return ApiResponse.fail("编辑失败");
        } else {
            SysJob existedJob = sysJobService.getById(id);
            if (existedJob.getJobStatus().equals(SysJobStatus.NORMAL.id())) {
                SchedulingRunnable task = new SchedulingRunnable(existedJob.getBeanName(), existedJob.getMethodName(), existedJob.getMethodParams());
                cronTaskRegister.removeCronTask(task);
            }
        }
        return ApiResponse.ok();
    }
}
