package com.lynn.seatsystem.domain.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 黑名单表 实体类。
 *
 * @author root
 * @since 2024-10-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("blacklist")
public class Blacklist implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 黑名单ID
     */
    @Id(keyType = KeyType.Auto)
    private Long blacklistId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 加入黑名单原因
     */
    private String reason;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 是否删除
     */
    @Column(isLogicDelete = true)
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
