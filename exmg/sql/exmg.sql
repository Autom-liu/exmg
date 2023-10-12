

use exmg;

create table if not exists `exmg_user` (
    `user_id` varchar(32) comment '登陆用户名，唯一主键',
    `user_name` varchar(64) not null comment '用户名',
    `password` varchar(32) not null comment '密码',
    `mobile` varchar(32) comment '手机号',
    `office_phone` varchar(32) comment '办公电话',
    `email` varchar(32) comment '邮箱',
    `remark` varchar(128) comment '备注',
    `wx_openid` varchar(128) comment '用户openid',
    `nick_name` varchar(128) comment '昵称',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常 1-初次创建 2-休假/暂停使用 3-离职',
    primary key (`user_id`)
    ) comment='登陆用户信息表' engine=Innodb DEFAULT CHARSET=utf8;


create table if not exists `exmg_department` (
    `dept_id` varchar(32) comment '机构编号',
    `dept_name` varchar(64) not null comment '机构名称',
    `parent_id` varchar(32) not null comment '父机构编号',
    `dept_level` int(11) not null comment '机构级别',
    `dept_type` tinyint(1) comment '机构类型(暂不使用)',
    `dept_manager` varchar(32) comment '机构管理员',
    `dept_category` varchar(32) comment '暂不使用',
    `dept_property` varchar(32) comment '暂不使用',
    `phone` varchar(32) comment '机构电话',
    `address` varchar(128) comment '机构地址',
    `effective_time` datetime comment '生效日期',
    `remark` varchar(128) comment '备注',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常',
    primary key (`dept_id`)
    ) comment='部门机构表' engine=Innodb DEFAULT CHARSET=utf8;


create table if not exists `exmg_role` (
    `role_id` varchar(32) comment '角色编号',
    `role_name` varchar(64) comment '角色名称',
    `remark` varchar(128) comment '备注',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常',
    primary key (`role_id`)
    ) comment='角色表' engine=Innodb DEFAULT CHARSET=utf8;

create table if not exists `exmg_dept_role_user_rel` (
    `user_id` varchar(32) not null  comment '',
    `dept_id` varchar(32) comment '',
    `role_id` varchar(32) comment '',
    `is_default` tinyint(1) comment '',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常',
    primary key (`user_id`, `dept_id`, role_id)
    ) comment='用户部门角色关联表' engine=Innodb DEFAULT CHARSET=utf8;

drop table if exists `exmg_menu`;
create table if not exists `exmg_menu` (
   `menu_id` varchar(32) comment '菜单编号',
   `menu_name` varchar(32) not null comment '菜单名称',
   `parent_id` varchar(32) not null comment '父菜单编号',
   `menu_type` int(11) comment '菜单类型',
   `url` varchar(64) comment '菜单链接',
   `order` int(11) comment '排序',
   `remark` varchar(128) comment '备注',
   `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
   `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
   `status` tinyint(1) comment '状态 0-正常',
   `is_leaf` tinyint(1) comment '是否叶子节点',
   primary key (`menu_id`)
) comment='菜单表' engine=Innodb DEFAULT CHARSET=utf8;

create table if not exists `exmg_permission` (
    `user_id` varchar(32) comment '用户编号',
    `dept_id` varchar(32) comment '机构编号',
    `role_id` varchar(32) comment '角色编号',
    `menu_id` varchar(32) comment '菜单编号'
    ) comment='用户权限表' engine=Innodb DEFAULT CHARSET=utf8;


create table if not exists `exmg_wx` (
    `id` int primary key auto_increment comment '微信用户id',
    `user_from` int comment '用户来源',
    `wx_openid` varchar(128) comment '用户openid',
    `nick_name` varchar(128) comment '昵称',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常',
    `version` int comment '版本号'
    )comment='微信用户表' engine=Innodb DEFAULT CHARSET=utf8;

create table if not exists `exmg_category` (
    id int primary key auto_increment comment  '主键',
    category_name varchar(64) comment '分类名称',
    parent_id int comment '父分类',
    level int comment '级别',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常',
    `version` int comment '版本号'
    ) comment='分类目录表' engine=Innodb DEFAULT CHARSET=utf8;

drop table if exists `exam_info`;
create table if not exists `exam_info` (
    `id` int primary key auto_increment comment '主键',
    `exam_name` varchar(32) comment '试题名称',
    `exam_remark` varchar(1024) comment '试题说明',
    `user_from` int comment '出题人来源',
    `user_id` varchar(128) comment '出题人用户id',
    `total_score` int comment '试题总分',
    `answer_mode` int comment '答题模式',
    `max_count` int comment '最大答题次数',
    `pic_url` varchar(1024) comment '缩略图地址',
    `banner` tinyint(1) comment '是否放首页',
    `auto_generate` tinyint(1) comment '是否随即生成的',
    `beginTime` DATETIME comment '答题开始时间',
    `endTime` DATETIME comment '答题结束时间',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常',
    `version` int comment '版本号'
) comment='考试信息表' engine=Innodb DEFAULT CHARSET=utf8;

drop table if exists `question_info`;
create table if not exists `question_info` (
    `id` int primary key auto_increment comment '主键',
    `user_id` varchar(128) comment '出题人用户id',
    `nick_name` varchar(128) comment '昵称',
    `question` varchar(1024) comment '题目',
    `question_type` int comment '题目类型',
    `difficulty` int comment '难度',
    `top_category` int comment '顶级分类id',
    `category_id` int comment '分类id',
    `interpretation` varchar(1024) comment '答案解析',
    `pics_url` varchar(2048) comment '图片地址',
    `common` tinyint(1) comment '是否贡献为公共题目',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint comment '状态 0-正常',
    `version` int comment '版本号'
) comment='题目信息表' engine=Innodb DEFAULT CHARSET=utf8;

drop table if exists `option_info`;
create table if not exists `option_info` (
    `id` int primary key auto_increment comment '主键',
    `option` varchar(1024) comment '选项',
    `question_id` int comment '关联题目id',
    `option_type` int comment '选项类型',
    `right` tinyint(1) comment '是否正确',
    `interpretation` varchar(1024) comment '选项解析',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常',
    `version` int comment '版本号'
    )  comment='选项信息表' engine=Innodb DEFAULT CHARSET=utf8;

create table if not exists `exam_question` (
    `id` int primary key auto_increment comment '主键',
    `exam_id` int comment '试题编号',
    `question_id` int comment '题目编号',
    `score` int comment '分值',
    `sorted` int comment '序号',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime on update CURRENT_TIMESTAMP comment '修改时间',
    `status` tinyint(1) comment '状态 0-正常',
    `version` int comment '版本号'
)  comment='试题题库关联表' engine=Innodb DEFAULT CHARSET=utf8;

drop table if exists `user_answer`;
create table if not exists `user_answer` (
    `id` int key primary key auto_increment comment '主键',
    `record_id` int comment '答题记录id',
    `user_id` varchar(128) comment '用户',
    `exam_id` int comment '试题',
    `question_id` int comment '题目',
    `option_id` int comment '选项',
    `score` int comment '实际得分',
    `sorted` int comment '排序号',
    `answer` varchar(128) comment '答案',
    `right` tinyint(1) comment '正确'
)  comment='答题信息表' engine=Innodb DEFAULT CHARSET=utf8;


drop table if exists `answer_record`;
create table if not exists `answer_record` (
    `id` int key primary key auto_increment comment '主键',
    `user_id` varchar(128) comment '用户',
    `exam_id` int comment '试题',
    `total_num` int comment '总题数',
    `right_num` int comment '正确题数',
    `start_time` datetime comment '开始答题时间',
    `end_time` datetime comment '结束答题时间'
)  comment='答题记录表' engine=Innodb DEFAULT CHARSET=utf8;
