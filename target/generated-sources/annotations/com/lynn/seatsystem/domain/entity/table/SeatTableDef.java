package com.lynn.seatsystem.domain.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class SeatTableDef extends TableDef {

    /**
     * 座位表 实体类。

 @author root
 @since 2024-10-06
     */
    public static final SeatTableDef SEAT = new SeatTableDef();

    /**
     * 座位ID
     */
    public final QueryColumn SEAT_ID = new QueryColumn(this, "seat_id");

    /**
     * 是否删除
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 所属楼层ID
     */
    public final QueryColumn FLOOR_ID = new QueryColumn(this, "floor_id");

    /**
     * 座位类型
     */
    public final QueryColumn SEAT_TYPE = new QueryColumn(this, "seat_type");

    /**
     * 是否可用
     */
    public final QueryColumn AVAILABLE = new QueryColumn(this, "available");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 座位号
     */
    public final QueryColumn SEAT_NUMBER = new QueryColumn(this, "seat_number");

    /**
     * 座位状态
     */
    public final QueryColumn SEAT_STATUS = new QueryColumn(this, "seat_status");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{SEAT_ID, FLOOR_ID, SEAT_TYPE, AVAILABLE, CREATE_TIME, SEAT_NUMBER, SEAT_STATUS, UPDATE_TIME};

    public SeatTableDef() {
        super("", "seat");
    }

    private SeatTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SeatTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SeatTableDef("", "seat", alias));
    }

}
