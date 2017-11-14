package com.ykaoqin.kit.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "响应元数据")
public class Meta {
    @ApiModelProperty(value = "状态码", required=true)
    private Integer code;
    @ApiModelProperty(value = "返回信息", required=true)
    private String msg;
}
