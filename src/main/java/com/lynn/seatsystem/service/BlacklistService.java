package com.lynn.seatsystem.service;

import com.lynn.seatsystem.domain.vo.PageResult;
import com.lynn.seatsystem.domain.vo.UserVO;
import com.mybatisflex.core.service.IService;
import com.lynn.seatsystem.domain.entity.Blacklist;

/**
 * 黑名单表 服务层。
 *
 * @author root
 * @since 2024-10-06
 */
public interface BlacklistService extends IService<Blacklist> {

    /**
     * 创建黑名单对象
     * @param userId 用户id
     */
    void createBlackObject(long userId);

    /**
     * 检查是否在黑名单中
     * @param userId 用户id
     * @return true 在黑名单中，false 不在黑名单中
     */
    Boolean checkBlackObject(long userId);

    /**
     * 删除黑名单对象
     * @param userId 用户id
     */
    void deleteBlackObject(long userId);

    /**
     * 获取黑名单列表
     * @param pageNum 分页页数
     * @param pageSize 分页大小
     * @return 黑名单列表
     */
    PageResult<UserVO> listBlackList(Integer pageNum, Integer pageSize);
}
