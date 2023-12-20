package cn.datafort.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author 胡鑫虎
 * @version 1.0
 * @date 2023/12/20 11:34
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {
    success(1, "success"),
    fail(2, "fail");

    private final int code;
    private final String value;
}
