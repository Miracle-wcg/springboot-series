package com.springboot.schedule.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.schedule.entity.SysJob;
import com.springboot.schedule.mapper.SysJobMapper;
import com.springboot.schedule.service.SysJobService;
import org.springframework.stereotype.Service;

/**
 * @Author: chengang.wu
 * @Date: 2020-01-20 14:48
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {
}
