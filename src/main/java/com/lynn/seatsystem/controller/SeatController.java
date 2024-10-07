package com.lynn.seatsystem.controller;

import com.lynn.seatsystem.domain.dto.SeatDTO;
import com.lynn.seatsystem.domain.entity.Seat;
import com.lynn.seatsystem.domain.vo.ApiResponse;
import com.lynn.seatsystem.domain.vo.SeatVO;
import com.lynn.seatsystem.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午9:04
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/seat")
public class SeatController {
    private final SeatService seatService;

    @PostMapping
    public ApiResponse<String> createSeat(@RequestBody SeatDTO dto){
        Seat seat = new Seat();
        BeanUtils.copyProperties(dto, seat);
        seatService.createSeat(seat);
        return ApiResponse.success("创建座位成功");
    }

    @GetMapping("/{id}")
    public ApiResponse<SeatVO> getSeat(@PathVariable Long id){
        return ApiResponse.success(seatService.getSeat(id));
    }
}
