package com.lynn.seatsystem.domain.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 反馈表 实体类。
 *
 * @author root
 * @since 2024-10-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("feedback")
public class Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 反馈ID
     */
    @Id(keyType = KeyType.Auto)
    private Long feedbackId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 反馈类型
     */
    private String feedbackType;

    /**
     * 处理状态
     */
    private String status;

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
