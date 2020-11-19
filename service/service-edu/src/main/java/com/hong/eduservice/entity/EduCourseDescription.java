package com.hong.eduservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author testjava
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduCourseDescription对象", description="")
public class EduCourseDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    //input表示手动输入，mp不会自动生成
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "")
    private String description;

    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
