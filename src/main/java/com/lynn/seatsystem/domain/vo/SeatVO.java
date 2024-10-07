package com.lynn.seatsystem.domain.vo;

import lombok.Data;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午9:39
 */
@Data
public class SeatVO {
    private Long seatId;
    private Integer libraryId;
    private Integer floorNumber;
    private String seatNumber;
    private String seatType;
    private Boolean available;
}
