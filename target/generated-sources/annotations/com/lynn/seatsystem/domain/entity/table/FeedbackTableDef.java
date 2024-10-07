package com.lynn.seatsystem.domain.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class FeedbackTableDef extends TableDef {

    /**
     * 反馈表 实体类。

 @author root
 @since 2024-10-06
     */
    public static final FeedbackTableDef FEEDBACK = new FeedbackTableDef();

    /**
     * 处理状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 用户ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 反馈内容
     */
    public final QueryColumn CONTENT = new QueryColumn(this, "content");

    /**
     * 是否删除
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 反馈ID
     */
    public final QueryColumn FEEDBACK_ID = new QueryColumn(this, "feedback_id");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 反馈类型
     */
    public final QueryColumn FEEDBACK_TYPE = new QueryColumn(this, "feedback_type");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{STATUS, USER_ID, CONTENT, CREATE_TIME, FEEDBACK_ID, UPDATE_TIME, FEEDBACK_TYPE};

    public FeedbackTableDef() {
        super("", "feedback");
    }

    private FeedbackTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public FeedbackTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new FeedbackTableDef("", "feedback", alias));
    }

}
