package com.lynn.seatsystem.service.impl;

import com.lynn.seatsystem.constant.BlackListConstant;
import com.lynn.seatsystem.domain.entity.User;
import com.lynn.seatsystem.domain.vo.PageResult;
import com.lynn.seatsystem.domain.vo.UserVO;
import com.lynn.seatsystem.mapper.UserMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lynn.seatsystem.domain.entity.Blacklist;
import com.lynn.seatsystem.mapper.BlacklistMapper;
import com.lynn.seatsystem.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.lynn.seatsystem.domain.entity.table.BlacklistTableDef.BLACKLIST;
import static com.lynn.seatsystem.domain.entity.table.UserTableDef.USER;

/**
 * 黑名单表 服务层实现。
 *
 * @author root
 * @since 2024-10-06
 */
@Service
@RequiredArgsConstructor
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements BlacklistService {
    private final BlacklistMapper blacklistMapper;
    private final UserMapper userMapper;

    @Override
    public void createBlackObject(long userId) {
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, BlackListConstant.BLACKLIST_DAY_LIMIT);
        Date endDate = calendar.getTime();

        Blacklist blacklist = Blacklist.builder()
                .userId(userId)
                .startDate(now)
                .endDate(endDate)
                .build();

        blacklistMapper.insert(blacklist, true);
    }

    @Override
    public Boolean checkBlackObject(long userId) {
        List<Blacklist> list = QueryChain.of(blacklistMapper)
                .select()
                .from(BLACKLIST)
                .where(BLACKLIST.USER_ID.eq(userId))
                .and(BLACKLIST.END_DATE.gt(new Date()))
                .list();

        return !list.isEmpty();
    }

    @Override
    public void deleteBlackObject(long userId) {
        List<Blacklist> list = QueryChain.of(blacklistMapper)
                .select()
                .from(BLACKLIST)
                .where(BLACKLIST.USER_ID.eq(userId))
                .and(BLACKLIST.END_DATE.gt(new Date()))
                .list();

        list.forEach(blacklist -> {
            blacklist.setEndDate(new Date());
            blacklistMapper.update(blacklist, true);
        });
    }

    @Override
    public PageResult<UserVO> listBlackList(Integer pageNum, Integer pageSize) {
        QueryWrapper wrapper = QueryChain.of(blacklistMapper)
                .select(USER.USER_ID, USER.USERNAME, USER.EMAIL, USER.PHONE, USER.GENDER, USER.STATUS, USER.LAST_LOGIN_IP, USER.LAST_LOGIN_TIME, USER.CREATE_TIME, USER.UPDATE_TIME)
                .from(BLACKLIST)
                .leftJoin(USER)
                .on(BLACKLIST.USER_ID.eq(USER.USER_ID))
                .where(BLACKLIST.END_DATE.gt(new Date()));

        Page<UserVO> page = new Page<>(pageNum, pageSize);
        Page<UserVO> userPage = userMapper.paginateAs(page, wrapper, UserVO.class);

        return PageResult.<UserVO>builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .total(userPage.getTotalRow())
                .list(userPage.getRecords())
                .build();

    }
}
