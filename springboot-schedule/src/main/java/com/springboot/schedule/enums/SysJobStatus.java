package com.springboot.schedule.enums;

/**
 * 业务异常枚举
 */
public enum SysJobStatus {


    PAUSE(1, "暂停"),
    NORMAL(0, "正常");

    private final int id;
    private final String desc;

    SysJobStatus(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }


    public int id() {
        return this.id;
    }

    public String desc() {
        return this.desc;
    }

    public static SysJobStatus getDesc(int id) {
        SysJobStatus[] values = SysJobStatus.values();
        for (SysJobStatus value : values) {
            if (value.id() == id) {
                return value;
            }
        }
        return PAUSE;
    }
}
