package io.xc.nativex;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xiaochen
 * @since 2025/4/28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@TableName("t_user")
public class UserDTO {

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Version
    private Long version;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Boolean deleted;

    @TableId
    private Long id;

    private String name;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdTime;

    /**
     * 修改时间
     */
    @JsonIgnore
    protected LocalDateTime modifiedTime;

    /**
     * 创建者
     */
    @JsonIgnore
    protected String createdBy;

    /**
     * 修改人
     */
    @JsonIgnore
    protected String modifiedBy;
}
