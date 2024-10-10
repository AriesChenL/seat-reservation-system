create table blacklist
(
    blacklist_id bigint auto_increment comment '黑名单ID'
        primary key,
    user_id      int                                  not null comment '用户ID',
    reason       text                                 null comment '加入黑名单原因',
    start_date   date                                 not null comment '开始日期',
    end_date     date                                 null comment '结束日期',
    deleted      tinyint(1) default 0                 not null comment '是否删除',
    create_time  datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '黑名单表';

create table feedback
(
    feedback_id   bigint auto_increment comment '反馈ID'
        primary key,
    user_id       int                                                                  not null comment '用户ID',
    content       text                                                                 not null comment '反馈内容',
    feedback_type enum ('suggestion', 'complaint', 'other')  default 'other'           not null comment '反馈类型',
    status        enum ('pending', 'processing', 'resolved') default 'pending'         not null comment '处理状态',
    deleted       tinyint(1)                                 default 0                 not null comment '是否删除',
    create_time   datetime                                   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime                                   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '反馈表';

create table floor
(
    floor_id     bigint auto_increment comment '楼层ID'
        primary key,
    library_id   int                                  not null comment '所属图书馆ID',
    floor_number int                                  not null comment '楼层号',
    deleted      tinyint(1) default 0                 not null comment '是否删除',
    create_time  datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '楼层表';

create table reservation
(
    reservation_id bigint auto_increment comment '预约ID'
        primary key,
    user_id        int                                                                               not null comment '用户ID',
    seat_id        int                                                                               not null comment '座位ID',
    start_time     datetime                                                                          not null comment '开始时间',
    end_time       datetime                                                                          not null comment '结束时间',
    status         enum ('pending', 'confirmed', 'cancelled', 'completed') default 'pending'         not null comment '预约状态',
    deleted        tinyint(1)                                              default 0                 not null comment '是否删除',
    create_time    datetime                                                default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime                                                default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '预约表';

create table seat
(
    seat_id     bigint auto_increment comment '座位ID'
        primary key,
    floor_id    int                                                                  not null comment '所属楼层ID',
    seat_number varchar(20)                                                          not null comment '座位号',
    seat_type   enum ('single', 'double', 'group')         default 'single'          not null comment '座位类型',
    seat_status enum ('available', 'reserved', 'occupied') default 'available'       null comment '座位状态',
    available   tinyint(1)                                 default 1                 not null comment '是否可用',
    deleted     tinyint(1)                                 default 0                 not null comment '是否删除',
    create_time datetime                                   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime                                   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '座位表';

create table user
(
    user_id         bigint auto_increment comment '用户ID'
        primary key,
    username        varchar(50)                          not null comment '用户名',
    password        varchar(255)                         not null comment '密码(加密存储)',
    email           varchar(100)                         null comment '电子邮箱',
    phone           varchar(20)                          null comment '手机号码',
    gender          tinyint(1)                           null comment '性别(0:未知 1:男 2:女)',
    status          tinyint(1) default 1                 not null comment '状态(0:禁用 1:启用)',
    last_login_time datetime                             null comment '最后登录时间',
    last_login_ip   varchar(50)                          null comment '最后登录IP',
    deleted         tinyint(1) default 0                 not null comment '是否删除(0:未删除 1:已删除)',
    create_time     datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint email
        unique (email),
    constraint phone
        unique (phone),
    constraint username
        unique (username)
)
    comment '用户表';

