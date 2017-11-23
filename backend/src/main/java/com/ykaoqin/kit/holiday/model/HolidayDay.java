package com.ykaoqin.kit.holiday.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "假日对象")
public class HolidayDay {
    @ApiModelProperty(value = "假日日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date day;
    @ApiModelProperty(value = "假日名称")
    private String name = "";
    @ApiModelProperty(value = "假日类型")
    private HolidayDateProperty prop;
    @ApiModelProperty(value = "假日说明")
    private String desc = "";
}
