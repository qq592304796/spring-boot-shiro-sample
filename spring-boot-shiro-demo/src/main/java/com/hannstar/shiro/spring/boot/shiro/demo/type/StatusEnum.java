package com.hannstar.shiro.spring.boot.shiro.demo.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {
    
    STATUS_VALID((byte) 1), // 有效

    STATUS_INVALID((byte) 0), // 无效

    STATUS_DISABLE((byte) 2);// 禁用
    
    private Byte val;

    public static StatusEnum getEnum(Byte val) {
        StatusEnum[] values = StatusEnum.values();
        for (StatusEnum object : values) {
            if (object.getVal().equals(val)) {
                return object;
            }
        }
        return null;
    }
}
