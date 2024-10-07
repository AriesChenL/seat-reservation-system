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
 * 预约表 实体类。
 *
 * @author root
 * @since 2024-10-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("reservation")
public class Reservation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 预约ID
     */
    @Id(keyType = KeyType.Auto)
    private Long reservationId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 座位ID
     */
    private Long seatId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 预约状态
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
