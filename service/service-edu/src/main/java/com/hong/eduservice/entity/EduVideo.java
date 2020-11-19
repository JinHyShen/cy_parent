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
@ApiModel(value="EduVideo对象", description="")
public class EduVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "")
    private String courseId;

    @ApiModelProperty(value = "")
    private String chapterId;

    @ApiModelProperty(value = "")
    private String title;

    @ApiModelProperty(value = "")
    private String videoSourceId;

    @ApiModelProperty(value = "")
    private String videoOriginalName;

    @ApiModelProperty(value = "")
    private Integer sort;

    private Long playCount;

    private Boolean isFree;

    private Float duration;

    private String status;

    private Long size;

    @ApiModelProperty(value = "版本")
    private Long version;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
