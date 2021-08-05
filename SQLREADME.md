# java-learning
>mysql学习笔记

```roomsql
-- create database lintcode default charset=utf8
use lintcode;
-- 学生表
create table students(
  id int unsigned AUTO_INCREMENT not null comment '学生ID',
  student_name varchar(30) not null comment '学生姓名',
  phone varchar(50) comment '学生电话',
  primary key (id)
)engine=innodb default charset=utf8 comment='学生表' ;

desc students;
desc enrollments;
-- drop table students;
-- 学籍表
create table enrollments(
  id int unsigned not null comment '学籍主键',
  student_id int unsigned  comment '学生id',
  hometown varchar(50) comment '家乡',
  address varchar(50) comment '地址',
  primary key (id)
)engine=innodb default charset=utf8 comment '学籍表';

-- insert table
insert into students values('1','Li Lei','13888888888');
insert into students values('2','Han Meimei','13999999999');
insert into students values('3','Amy','13788889999');
insert into students values('4','Jason','13788789999');

select *from students;

-- 插入学籍信息
insert into enrollments values (1,1,'Shi Jiazhuang','Hang Zhou');
insert into enrollments values (2,2,'Heng Shui','Tang Shan');
insert into enrollments values (3,3,'Cang Zhou','Shi Jiazhuang');
insert into enrollments values (4,1,'Cang Zhou','Shi Hezi');
select *from enrollments;

-- student_name	phone	hometown	address
select s.student_name,s.phone, e.hometown, e.address from students s left join 
enrollments e on s.id= e.student_id;
-- 编写一个 SQL 语句，查找 students 表中所有重名同学的名字。
select s.student_name,count(student_name) as 'number' from students s
group by s.student_name having number>1;
--
select s.student_name from students s
group by s.student_name having count(student_name)>1;

-- 
drop table if exists group_members;
desc group_members;
select *from group_members;
--
create table group_members(
  id int unsigned not null AUTO_INCREMENT comment 'id',
  name varchar(50) comment '组员姓名',
  score int unsigned comment '组员分数',
  group_leader_id int unsigned default null comment '组长id',
  primary key(id)
) engine=innodb, default charset=utf8 comment '组员表';

insert into group_members values(1,'Bryant',81,4);
insert into group_members values(2,'Iverson',60,1);
insert into group_members values(3,'Carter',51,null);
insert into group_members values(4,'McGrady',62,null);

-- 删除表
truncate group_members;

insert into group_members values(1,'Bryant',81,null);
insert into group_members values(2,'Iverson',60,3);
insert into group_members values(3,'Carter',51,1);
-- insert into group_members values(4,'McGrady',62,null);


-- query
select a.id,a.name,a.score as score,b.name as leadername,b.score as leadercore from group_members a 
 left join group_members b
  on b.id=a.group_leader_id where 1=1 
  and a.score>b.score;

--
select a.name from group_members a 
  left join group_members b
  on b.id=a.group_leader_id where 1=1 
  and a.score>b.score and a.group_leader_id is not null;
  
-- 

-- DROP TABLE IF EXISTS new_cases;
-- CREATE TABLE `new_cases`(
--     `id` INT UNSIGNED AUTO_INCREMENT,
--     `date` DATE NOT NULL,
--     `increased_count` INT NOT NULL,
--     PRIMARY KEY ( `id` )
-- );
--  删除表 
drop table if exists new_cases;
create table new_cases(
   id int unsigned not null auto_increment comment 'id',
  `date` date not null comment '日期',
  increased_count int not null comment '新增感染人数',
  primary key(id) comment '主键'
) engine=innodb default charset=utf8 comment '新增疫情';

--
select *from new_cases order by `date` asc;
-- insert values
insert into new_cases values(1,'2020-12-25',100994);
insert into new_cases values(2,'2020-12-26',216858);
insert into new_cases values(3,'2020-12-27',152102);
insert into new_cases values(4,'2020-12-28',189044);

select a.id,a.increased_count,a.`date` ,b.id, b.date `next_date`,
b.increased_count,(b.increased_count-a.increased_count>0) as bigger 
 from new_cases a 
 left join new_cases b 
 on a.id =b.id-1 and (b.increased_count-a.increased_count>0);
 
/*
编写一个 SQL 语句，来查找与前一天的日期相比美国的新增病例数更高的所有日期的 id
说明：
2020-12-26 美国的新增病例数比前一天高（100994 -> 216858）
2020-12-28 美国的新增病例数比前一天高（152102 -> 189044)
*/
select b.id, b.date `next_date`,
b.increased_count
 from new_cases a 
 left join new_cases b 
 on a.id =b.id-1 and (b.increased_count-a.increased_count>0);
 -- 方法1
select b.id from new_cases a 
 inner join new_cases b 
 on a.id =b.id-1 and (b.increased_count-a.increased_count>0);
 -- 方法2
 select b.id from new_cases a 
 join new_cases b 
 where 1=1 
 and datediff(b.date,a.date)=1
 and (b.increased_count-a.increased_count>0);
 
 /*
 从不充值的玩家
 某游戏数据库包含两个表，用户 (users) 表和充值 (recharges) 表，编写一个 SQL 语句，找出所有从未充值的玩家
 */
 drop table if exists users;
 drop table if exists recharges;
 -- user table 
 create table users(
  id int unsigned not null auto_increment comment 'id',
  `name` varchar(50) not null comment '姓名',
  primary key(id)
 ) engine=innodb default charset=utf8 comment '用户表';
 
 -- recharges (充值表)
  create table recharges(
  id int unsigned not null auto_increment comment '主键',
  user_id int not null comment '用户id',
  primary key(id)
 ) engine=innodb default charset=utf8 comment '充值表';
 
desc recharges;

insert into users values(1,'XiaoXuesheng');
insert into users values(2,'Mike');
insert into users values(3,'John');
insert into users values(4,'Maria');

--
insert into recharges values(1,3);
insert into recharges values(2,1);

-- 显示充值的
select a.name as player from users a inner join recharges b
on a.id=b.user_id;

-- 显示没有充值的人的姓名
select u.name as player from users u where u.name
  not in(select a.name as player from users a inner join recharges b on a.id=b.user_id);
  
/*
*/
drop table if exists student_scores;
desc student_scores;
CREATE TABLE student_scores(
    id int unsigned not null auto_increment comment 'id',
    `name` VARCHAR(50) NOT NULL comment '学生姓名',
    chinese int not null comment '学生语文分数', 
    math int not null comment '学生数学分数', 
    english int not null comment '学生英语分数', 
    cst int not null comment '学生理综分数', 
    PRIMARY KEY (id)
)engine=InnoDB ,default charset=utf8 comment '学生成绩表';
-- insert table;
insert into student_scores values(1,'KangKang',95,	91,	89,	97);
insert into student_scores values(2,'Jane',90,	93,	98,	98);
insert into student_scores values(3,'Micheal',85,	76	,93	,92);
insert into student_scores values(4,'Maria',88	,89,	95,	94);
insert into student_scores values(5,'LiHua',30	,13	,19	,23);

select *from student_scores;

select sc.name ,sc.math, sc.cst from student_scores sc where 1=1
and (sc.math>=90 or sc.cst>=95);
--  方法1
select distinct a.* from (select sc.name ,sc.math, sc.cst from student_scores sc where 1=1
and sc.math>90 
union all
select  sc.name ,sc.math, sc.cst from student_scores sc where 1=1
and  sc.cst>95 ) as a ;

-- 方法2 注意 union和union all 的区别
select sc.name ,sc.math, sc.cst from student_scores sc where 1=1
and sc.math>90 
union 
select  sc.name ,sc.math, sc.cst from student_scores sc where 1=1
and  sc.cst>95  

--

```