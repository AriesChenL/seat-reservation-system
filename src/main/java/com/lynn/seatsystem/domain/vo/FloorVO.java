package com.lynn.seatsystem.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Lynn
 * Date: 2024/10/10 下午2:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FloorVO {
    private Long floorId;
    private Long libraryId;
    private String floorNumber;
}
