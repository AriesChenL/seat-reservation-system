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
 * 座位表 实体类。
 *
 * @author root
 * @since 2024-10-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("seat")
public class Seat implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 座位ID
     */
    @Id(keyType = KeyType.Auto)
    private Long seatId;

    /**
     * 所属楼层ID
     */
    private Long floorId;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 座位类型
     */
    private String seatType;

    /**
     * 座位状态
     */
    private String seatStatus;

    /**
     * 是否可用
     */
    private Boolean available;

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
