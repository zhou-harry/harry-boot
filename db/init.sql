/*oauth权限认证表*/
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(48) NOT NULL,
    `resource_ids`            varchar(256)  DEFAULT NULL,
    `client_secret`           varchar(256)  DEFAULT NULL,
    `scope`                   varchar(256)  DEFAULT NULL,
    `authorized_grant_types`  varchar(256)  DEFAULT NULL,
    `web_server_redirect_uri` varchar(256)  DEFAULT NULL,
    `authorities`             varchar(256)  DEFAULT NULL,
    `access_token_validity`   int(11) DEFAULT NULL,
    `refresh_token_validity`  int(11) DEFAULT NULL,
    `additional_information`  varchar(4096) DEFAULT NULL,
    `autoapprove`             varchar(256)  DEFAULT NULL,
    PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*添加client记录（密码为：harry）,授权类型中如果没有加上：refresh_token则不会产生refresh_token的值*/
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('harry-client-id', '$2a$10$PFDpz98K3ROeSVImLkGhbe48OvF9oIvsheiPRzakIOzRs9nA3fjai', 'user_info', 'authorization_code,refresh_token', 'http://localhost/login', null, null, null, null, 'user_info');
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('swagger-client-id', '$2a$10$PFDpz98K3ROeSVImLkGhbe48OvF9oIvsheiPRzakIOzRs9nA3fjai', 'user_info', 'authorization_code,refresh_token', 'http://localhost/webjars/springfox-swagger-ui/oauth2-redirect.html', null, null, null, null, 'user_info');

/*第三方社交授权表*/
create table t_UserConnection
(
    userId         varchar(255) not null,
    providerId     varchar(255) not null,
    providerUserId varchar(255),
    `rank`         int          not null,
    displayName    varchar(255),
    profileUrl     varchar(512),
    imageUrl       varchar(512),
    accessToken    varchar(512) not null,
    secret         varchar(512),
    refreshToken   varchar(512),
    expireTime     bigint,
    primary key (userId, providerId, providerUserId)
);
create
unique index UserConnectionRank on t_UserConnection(userId, providerId, `rank`);

/*登录记住我功能表*/
CREATE TABLE persistent_logins
(
    username  VARCHAR(64) NOT NULL,
    series    VARCHAR(64) NOT NULL,
    token     VARCHAR(64) NOT NULL,
    last_used TIMESTAMP,
    PRIMARY KEY (series)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*系统菜单表*/
CREATE TABLE sys_menu
(
    menu_id     bigint(-1) NOT NULL COMMENT '菜单ID',
    parent_id   bigint(-1) COMMENT '上级菜单ID',
    menu_type   INT(-1) COMMENT '菜单类型',
    title       VARCHAR(255) COMMENT '菜单标题',
    component   VARCHAR(255) COMMENT '组件',
    menu_index  INT(-1) COMMENT '排序',
    icon        VARCHAR(255) COMMENT '图标',
    path        VARCHAR(255) COMMENT '链接地址',
    hidden      INT(-1) DEFAULT 0 COMMENT '隐藏(0=不隐藏，1=隐藏)',
    permission  VARCHAR(255) COMMENT '权限',
    create_by   bigint(-1) NOT NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   bigint(-1) NOT NULL COMMENT '更新人',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

/*后台服务地址*/
CREATE TABLE sys_rest
(
    rest_id     bigint(-1) NOT NULL COMMENT 'ID',
    parent_url  VARCHAR(100) NOT NULL COMMENT '请求地址前缀',
    request_url VARCHAR(100) NOT NULL COMMENT '请求地址',
    create_by   bigint(-1) NOT NULL COMMENT '创建人',
    update_by   bigint(-1) NOT NULL COMMENT '更新人',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (rest_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台服务地址';

/*系统角色*/
CREATE TABLE sys_role
(
    role_id     bigint(-1) NOT NULL COMMENT '角色ID',
    name        VARCHAR(255) COMMENT '名称',
    level       INT(-1) COMMENT '角色级别',
    description VARCHAR(255) COMMENT '描述',
    create_by   bigint(-1) NOT NULL COMMENT '创建人',
    update_by   bigint(-1) NOT NULL COMMENT '更新人',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*系统用户*/
CREATE TABLE sys_user
(
    user_id     bigint(-1) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    username    VARCHAR(255)             NOT NULL COMMENT '登录账号',
    nick_name   VARCHAR(255) COMMENT '昵称',
    gender      TINYINT(1) NOT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
    phone       VARCHAR(11) COMMENT '电话',
    email       VARCHAR(100) COMMENT '电子邮件',
    avatar      VARCHAR(255)             NOT NULL COMMENT '头像',
    password    VARCHAR(255) DEFAULT '1' NOT NULL COMMENT '密码',
    salt        VARCHAR(45)              NOT NULL COMMENT 'md5密码盐',
    birthday    DATETIME COMMENT '生日',
    status      TINYINT(1) NOT NULL COMMENT '状态：1启用、0禁用',
    third_id    VARCHAR(100) COMMENT '第三方登录的唯一标识',
    third_type  VARCHAR(100) COMMENT '第三方登陆类型',
    create_by   bigint(-1) NOT NULL COMMENT '创建人',
    create_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   bigint(-1) NOT NULL COMMENT '更新人',
    update_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

CREATE TABLE
    sys_roles_menus
(
    menu_id     bigint(-1) NOT NULL COMMENT '菜单ID',
    role_id     bigint(-1) NOT NULL COMMENT '角色ID',
    create_by   bigint(-1) NOT NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   bigint(-1) NOT NULL COMMENT '更新人',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (menu_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联';

CREATE TABLE
    sys_roles_rest
(
    rest_id     bigint(-1) NOT NULL COMMENT '后台服务ID',
    role_id     bigint(-1) NOT NULL COMMENT '角色ID',
    create_by   bigint(-1) NOT NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   bigint(-1) NOT NULL COMMENT '更新人',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (rest_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色后台服务关联';

CREATE TABLE
    sys_users_roles
(
    user_id     bigint(-1) NOT NULL COMMENT '用户ID',
    role_id     bigint(-1) NOT NULL COMMENT '角色ID',
    create_by   bigint(-1) NOT NULL COMMENT '创建者',
    update_by   bigint(-1) NOT NULL COMMENT '更新者',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联';