package com.lynn.seatsystem.domain.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class UserTableDef extends TableDef {

    /**
     * 用户表 实体类。

 @author root
 @since 2024-10-06
     */
    public static final UserTableDef USER = new UserTableDef();

    /**
     * 电子邮箱
     */
    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    /**
     * 手机号码
     */
    public final QueryColumn PHONE = new QueryColumn(this, "phone");

    /**
     * 性别(0:未知 1:男 2:女)
     */
    public final QueryColumn GENDER = new QueryColumn(this, "gender");

    /**
     * 状态(0:禁用 1:启用)
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 用户ID
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 是否删除(0:未删除 1:已删除)
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 密码(加密存储)
     */
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    /**
     * 用户名
     */
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 最后登录IP
     */
    public final QueryColumn LAST_LOGIN_IP = new QueryColumn(this, "last_login_ip");

    /**
     * 最后登录时间
     */
    public final QueryColumn LAST_LOGIN_TIME = new QueryColumn(this, "last_login_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{EMAIL, PHONE, GENDER, STATUS, USER_ID, PASSWORD, USERNAME, CREATE_TIME, UPDATE_TIME, LAST_LOGIN_IP, LAST_LOGIN_TIME};

    public UserTableDef() {
        super("", "user");
    }

    private UserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public UserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new UserTableDef("", "user", alias));
    }

}
