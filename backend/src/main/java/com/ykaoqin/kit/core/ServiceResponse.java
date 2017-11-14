package com.ykaoqin.kit.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "响应对象")
public class ServiceResponse<T> {
    @ApiModelProperty(value = "响应元数据", required=true)
    private Meta meta;
    @ApiModelProperty(value = "具体内容对象")
    private T data;

    public ServiceResponse(Integer code, String message) {
        this.meta = new Meta(code, message);
        this.data = (T)new Object();
    }

    public ServiceResponse(Integer code, String message, T data) {
        this.meta = new Meta(code, message);
        this.data = data;
    }
}
