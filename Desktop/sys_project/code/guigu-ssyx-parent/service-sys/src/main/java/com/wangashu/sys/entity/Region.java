package com.wangashu.sys.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 地区表
 * </p>
 *
 * @author wangashu
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
      private Long id;

    /**
     * 上级id
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer isDeleted;


}
