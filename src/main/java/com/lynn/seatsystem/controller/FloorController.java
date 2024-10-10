package com.lynn.seatsystem.controller;

import com.lynn.seatsystem.domain.dto.FloorDTO;
import com.lynn.seatsystem.domain.entity.Floor;
import com.lynn.seatsystem.domain.vo.ApiResponse;
import com.lynn.seatsystem.domain.vo.FloorVO;
import com.lynn.seatsystem.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午9:06
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/floor")
public class FloorController {
    private final FloorService floorService;

    @PostMapping
    public ApiResponse<String> createFloor(@RequestBody FloorDTO dto) {
        Floor floor = new Floor();
        BeanUtils.copyProperties(dto, floor);
        floorService.saveOrUpdate(floor);
        return ApiResponse.success("创建楼层成功");
    }

    @GetMapping("/{id}")
    public ApiResponse<Floor> getFloor(@PathVariable Integer id) {
        return ApiResponse.success(floorService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<String> updateFloor(@PathVariable Long id, @RequestBody FloorDTO dto) {
        Floor floor = new Floor();
        BeanUtils.copyProperties(dto, floor);
        floor.setFloorId(id);
        floorService.updateById(floor);
        return ApiResponse.success("更新楼层成功");
    }

    @GetMapping("/available/{libraryId}")
    public ApiResponse<List<FloorVO>> getAvailableFloor(@PathVariable Long libraryId) {
        return ApiResponse.success(floorService.getAvailableFloor(libraryId));
    }
}
