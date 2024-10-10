package com.lynn.seatsystem.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: Lynn
 * Date: 2024/10/10 下午2:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResult<T> {

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 数据列表
     */
    private List<T> list;
}
