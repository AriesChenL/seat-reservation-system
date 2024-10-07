package com.lynn.seatsystem.domain.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class FloorTableDef extends TableDef {

    /**
     * 楼层表 实体类。

 @author root
 @since 2024-10-06
     */
    public static final FloorTableDef FLOOR = new FloorTableDef();

    /**
     * 是否删除
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 楼层ID
     */
    public final QueryColumn FLOOR_ID = new QueryColumn(this, "floor_id");

    /**
     * 所属图书馆ID
     */
    public final QueryColumn LIBRARY_ID = new QueryColumn(this, "library_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 楼层号
     */
    public final QueryColumn FLOOR_NUMBER = new QueryColumn(this, "floor_number");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{FLOOR_ID, LIBRARY_ID, CREATE_TIME, UPDATE_TIME, FLOOR_NUMBER};

    public FloorTableDef() {
        super("", "floor");
    }

    private FloorTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public FloorTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new FloorTableDef("", "floor", alias));
    }

}
