package com.springboot.schedule.springbootschedule;

import com.springboot.schedule.entity.SysJob;
import com.springboot.schedule.mapper.SysJobMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootScheduleApplicationTests {
    @Autowired
    private SysJobMapper sysJobMapper;
    @Test
    void testSelect() {
        List<SysJob> sysJobs = sysJobMapper.selectList(null);
        System.out.println(111);
    }

}
