CREATE TABLE miaosha_user(
  id bigint(20) NOT NULL COMMENT '用户id,手机号码',
  nickname varchar(255) NOT NULL,
  password varchar(32) DEFAULT NULL COMMENT 'md5(md5(pass明文+固定salt)+salt)',
  salt VARCHAR (10) DEFAULT NULL ,
  head VARCHAR (128) DEFAULT  NULL COMMENT '头像，云存储的ID',
  register_date DATETIME DEFAULT NULL COMMENT '注册时间',
  last_login_date DATETIME DEFAULT NULL COMMENT '上次登录时间',
  login_count INT(11) DEFAULT '0' COMMENT '登陆次数',
  PRIMARY KEY (id)
)ENGINE=innoDB DEFAULT CHARSET=utf8;