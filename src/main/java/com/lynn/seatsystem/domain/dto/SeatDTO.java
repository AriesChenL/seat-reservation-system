package com.lynn.seatsystem.domain.dto;

import lombok.Data;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午9:15
 */
@Data
public class SeatDTO {
    private Long floorId;
    private String seatNumber;
    private String seatType;
}
