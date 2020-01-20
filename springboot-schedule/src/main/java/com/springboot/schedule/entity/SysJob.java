package com.springboot.schedule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: chengang.wu
 * @Date: 2020-01-20 14:45
 */
@Data
public class SysJob {
    @TableId(type = IdType.AUTO)
    private String id;
    private String beanName;
    private String methodName;
    private String methodParams;
    private String cronExpression;
    private String remark;
    private String jobStatus;
    private String createAt;
    private String updateAt;
}
