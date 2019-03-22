package com.mark.test.framework.core.dto.comm;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommRestDto {
    private String code;
    private String msg;
    private Object data;
}
