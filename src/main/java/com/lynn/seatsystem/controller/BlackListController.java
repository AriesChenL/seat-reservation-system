package com.lynn.seatsystem.controller;

import com.lynn.seatsystem.domain.vo.ApiResponse;
import com.lynn.seatsystem.domain.vo.PageResult;
import com.lynn.seatsystem.domain.vo.UserVO;
import com.lynn.seatsystem.exception.BusinessException;
import com.lynn.seatsystem.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Lynn
 * Date: 2024/10/10 下午2:58
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/blacklist")
public class BlackListController {
    private final BlacklistService blacklistService;

    @PostMapping
    public ApiResponse<Long> createBlackObject(@RequestParam Long userId) {
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }

        blacklistService.createBlackObject(userId);
        return ApiResponse.success(userId);
    }

    @GetMapping("/{userId}")
    public ApiResponse<Boolean> checkBlackObject(@PathVariable Long userId) {
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }

        return ApiResponse.success(blacklistService.checkBlackObject(userId));
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteBlackObject(@PathVariable Long userId) {
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }

        blacklistService.deleteBlackObject(userId);
        return ApiResponse.success("移除黑名单成功");
    }

    @GetMapping
    public ApiResponse<PageResult<UserVO>> checkUserBlack(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ApiResponse.success(blacklistService.listBlackList(pageNum, pageSize));
    }
}
