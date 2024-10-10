package com.lynn.seatsystem.controller;

import com.lynn.seatsystem.domain.entity.User;
import com.lynn.seatsystem.domain.vo.ApiResponse;
import com.lynn.seatsystem.service.ReservationService;
import com.lynn.seatsystem.util.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Lynn
 * Date: 2024/10/07 上午10:17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ApiResponse<String> createReservation(@RequestParam Long seatId) {
        Long userId = -1L;
        User user = UserContext.getUser();
        if (user != null) {
            userId = user.getUserId();
        }

        if (seatId == null) {
            return ApiResponse.error("参数错误，缺少座位ID。");
        }
        reservationService.createReservation(seatId, userId);
        return ApiResponse.success("预约成功");
    }

    @PostMapping("/cancel")
    public ApiResponse<String> cancelReservation(@RequestParam Long reservationId) {
        long userId = -1L;
        User user = UserContext.getUser();
        if (user != null) {
            userId = user.getUserId();
        }

        if (reservationId == null) {
            return ApiResponse.error("参数错误，缺少预约ID。");
        }
        reservationService.cancelReservation(reservationId, userId);
        return ApiResponse.success("取消成功");
    }

    @PostMapping("/confirm")
    public ApiResponse<String> confirmReservation(@RequestParam Long reservationId) {
        long userId = -1L;
        User user = UserContext.getUser();
        if (user != null) {
            userId = user.getUserId();
        }

        if (reservationId == null || reservationId < 1) {
            return ApiResponse.error("参数错误，缺少预约ID。");
        }
        reservationService.confirmReservation(reservationId, userId);
        return ApiResponse.success("确认成功");
    }

    @PostMapping("/leave")
    public ApiResponse<String> leaveSeat(@RequestParam Long reservationId) {
        long userId = -1L;
        User user = UserContext.getUser();
        if (user != null) {
            userId = user.getUserId();
        }

        if (reservationId == null || reservationId < 1) {
            return ApiResponse.error("参数错误，缺少预约ID。");
        }
        reservationService.leaveSeat(reservationId, userId);
        return ApiResponse.success("离开成功");
    }
}
