--用户配置表 配置界面中的控件的值
drop table if exists app_config;
create table app_config
(
    config_code  text not null
        constraint app_config_pk
            primary key,
    config_class text,
    config_value text
);
--数据源配置表
drop table if exists db_config;
create table db_config
(
    name              text not null--数据源配置名
        primary key,
    user_name         text not null,--数据库账户名
    password          text not null,--数据库密码
    driver_class_name text not null,--驱动类名
    url               text not null--数据库连接
);
drop table if exists app_dict;
create table app_dict
(
    dict_code  text not null primary key,--字典项值
    dict_name  text not null ,--字典项名
    group_code text not null,--字典分组编码
    group_name text,--字典分组名
    order_id integer not null default 0,--排序（按值升序排列）
    status integer not null default 1--状态（0失效 1生效）
);


