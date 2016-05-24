-- 创建数据库
CREATE DATABASE sue4j;

-- 使用数据库
use sue4j;

-- 创建测试用表
CREATE TABLE s_user(
  s_id INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
  s_name VARCHAR(50) NOT NULL COMMENT '用户姓名',
  s_tel VARCHAR(50) NOT NULL COMMENT '用户电话',
  PRIMARY KEY (s_id)
) ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '用户信息表';

-- 初始化数据
INSERT INTO
  s_user (s_name, s_tel)
VALUES
  ('sue', '13211112222'),
  ('ues', '13433334444'),
  ('sss', '13355556666');
