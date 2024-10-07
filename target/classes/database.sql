CREATE TABLE user (
                      user_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
                      username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
                      password VARCHAR(255) NOT NULL COMMENT '密码(加密存储)',
                      email VARCHAR(100) UNIQUE COMMENT '电子邮箱',
                      phone VARCHAR(20) UNIQUE COMMENT '手机号码',
                      gender TINYINT(1) COMMENT '性别(0:未知 1:男 2:女)',
                      status TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态(0:禁用 1:启用)',
                      last_login_time DATETIME COMMENT '最后登录时间',
                      last_login_ip VARCHAR(50) COMMENT '最后登录IP',
                      deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除 1:已删除)',
                      create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                      update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '用户表';

CREATE TABLE floor (
                        floor_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '楼层ID',
                        library_id INT NOT NULL COMMENT '所属图书馆ID',
                        floor_number INT NOT NULL COMMENT '楼层号',
                        deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
                        create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '楼层表';

CREATE TABLE seat (
                       seat_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '座位ID',
                       floor_id INT NOT NULL COMMENT '所属楼层ID',
                       seat_number VARCHAR(20) NOT NULL COMMENT '座位号',
                       seat_type ENUM('single', 'double', 'group') NOT NULL DEFAULT 'single' COMMENT '座位类型',
                       is_available BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否可用',
                       deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
                       create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '座位表';

CREATE TABLE reservation (
                              reservation_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '预约ID',
                              user_id INT NOT NULL COMMENT '用户ID',
                              seat_id INT NOT NULL COMMENT '座位ID',
                              start_time DATETIME NOT NULL COMMENT '开始时间',
                              end_time DATETIME NOT NULL COMMENT '结束时间',
                              status ENUM('pending', 'confirmed', 'cancelled', 'completed') NOT NULL DEFAULT 'pending' COMMENT '预约状态',
                              deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
                              create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '预约表';

CREATE TABLE blacklist (
                           blacklist_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '黑名单ID',
                           user_id INT NOT NULL COMMENT '用户ID',
                           reason TEXT COMMENT '加入黑名单原因',
                           start_date DATE NOT NULL COMMENT '开始日期',
                           end_date DATE COMMENT '结束日期',
                           deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
                           create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '黑名单表';

CREATE TABLE feedback (
                          feedback_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '反馈ID',
                          user_id INT NOT NULL COMMENT '用户ID',
                          content TEXT NOT NULL COMMENT '反馈内容',
                          feedback_type ENUM('suggestion', 'complaint', 'other') NOT NULL DEFAULT 'other' COMMENT '反馈类型',
                          status ENUM('pending', 'processing', 'resolved') NOT NULL DEFAULT 'pending' COMMENT '处理状态',
                          deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
                          create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '反馈表';