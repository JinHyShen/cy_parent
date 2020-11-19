package com.hong.eduservice.entity;

import java.math.BigDecimal;

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
 * @author testjava
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduCourse对象", description="")
public class EduCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "")
    private String teacherId;

    @ApiModelProperty(value = "")
    private String subjectId;

    @ApiModelProperty(value = "")
    private String subjectParentId;

    @ApiModelProperty(value = "")
    private String title;

    @ApiModelProperty(value = "")
    private BigDecimal price;

    @ApiModelProperty(value = "")
    private Integer lessonNum;

    @ApiModelProperty(value = "")
    private String cover;

    @ApiModelProperty(value = "")
    private Long buyCount;

    @ApiModelProperty(value = "")
    private Long viewCount;

    @ApiModelProperty(value = "")
    private Long version;

    @ApiModelProperty(value = "")
    private String status;

    @ApiModelProperty(value = "")
    private Integer isDeleted;

    @ApiModelProperty(value = "")
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
