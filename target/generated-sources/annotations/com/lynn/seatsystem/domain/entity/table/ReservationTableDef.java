package com.lynn.seatsystem.domain.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class ReservationTableDef extends TableDef {

    /**
     * 预约表 实体类。

 @author root
 @since 2024-10-06
     */
    public static final ReservationTableDef RESERVATION = new ReservationTableDef();

    /**
     * 座位ID
     */
    public final QueryColumn SEAT_ID = new QueryColumn(this, "seat_id");

    /**
     * 预约状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 用户ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 是否删除
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 结束时间
     */
    public final QueryColumn END_TIME = new QueryColumn(this, "end_time");

    /**
     * 开始时间
     */
    public final QueryColumn START_TIME = new QueryColumn(this, "start_time");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 预约ID
     */
    public final QueryColumn RESERVATION_ID = new QueryColumn(this, "reservation_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{SEAT_ID, STATUS, USER_ID, END_TIME, START_TIME, CREATE_TIME, UPDATE_TIME, RESERVATION_ID};

    public ReservationTableDef() {
        super("", "reservation");
    }

    private ReservationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ReservationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ReservationTableDef("", "reservation", alias));
    }

}
