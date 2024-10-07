package com.lynn.seatsystem.domain.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class BlacklistTableDef extends TableDef {

    /**
     * 黑名单表 实体类。

 @author root
 @since 2024-10-06
     */
    public static final BlacklistTableDef BLACKLIST = new BlacklistTableDef();

    /**
     * 加入黑名单原因
     */
    public final QueryColumn REASON = new QueryColumn(this, "reason");

    /**
     * 用户ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 是否删除
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 结束日期
     */
    public final QueryColumn END_DATE = new QueryColumn(this, "end_date");

    /**
     * 开始日期
     */
    public final QueryColumn START_DATE = new QueryColumn(this, "start_date");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 黑名单ID
     */
    public final QueryColumn BLACKLIST_ID = new QueryColumn(this, "blacklist_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{REASON, USER_ID, END_DATE, START_DATE, CREATE_TIME, UPDATE_TIME, BLACKLIST_ID};

    public BlacklistTableDef() {
        super("", "blacklist");
    }

    private BlacklistTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public BlacklistTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new BlacklistTableDef("", "blacklist", alias));
    }

}
