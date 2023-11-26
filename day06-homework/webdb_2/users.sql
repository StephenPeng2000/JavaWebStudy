create table webdb_2.users
(
    id       int unsigned not null comment 'ID'
        primary key,
    name     varchar(10)  not null comment '姓名',
    age      int          not null comment '年龄',
    birthday date         not null comment '生日',
    salary   float(10, 2) null comment '工资'
);

