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
and  sc.cst>95  ;

--
/*
有一个 player_scores 表，有 player (球员) 和 score (得分)
请找到所有被三名或以上球员得过的分数
如在下面这一张表中，50 这个分数被 Bryant, Carter, Durant 三个人拿到过
所以应该输出 50
*/
drop table if exists player_scores;
desc player_scores;
select *from player_scores;

create table player_scores(
  id int unsigned not null auto_increment,
  player varchar(30) not null comment '球员姓名',
  score int not null comment '分数',
  primary key(id)
)engine=innodb, default charset=utf8 comment '球员得分表';

insert into player_scores values(1,'Jordan',63);
insert into player_scores values(2,'Iverson',55);
insert into player_scores values(3,'Bryant',50);
insert into player_scores values(4,'Carter',50);
insert into player_scores values(5,'McGrady',	46);
insert into player_scores values(6,'James',51);
insert into player_scores values(7,'Durant',50);
insert into player_scores values(8,'Wade',46);
insert into player_scores values(9,'Anthony',42);
insert into player_scores values(10,'Ginobili',39);
-- 查询大于3次出现的分数
select score,count(score) as num from player_scores group by score
having num>=3;

/*
编写一个 SQL 语句，找出所有英雄热度为非 T3 (不热门)的并且 id 为奇数的英雄，结果请按 ban 率由大到小排列。
Tx (x = 0, 1 ,2, ...) 表示英雄的热度，其中，T0 表示热门英雄，T1 表示次热门英雄，T2 表示正常热度英雄，T3 表示不热门英雄，T4 及以后表示冷门英雄。
*/
drop table if exists heroes;

create table heroes(
    `id` int unsigned auto_increment not null comment 'id',
    `name` varchar(50) not null comment '英雄名字',
     popularity varchar(50) not null comment '热门等级', 
     ban float not null comment '英雄被禁概率', 
     PRIMARY KEY ( `id` )
)engine=innodb, default charset=utf8 comment '英雄表';

select *from heroes;

insert into heroes values (1,'Lv Bu','T0',0.90);
insert into heroes values (2,'Ju Youjing','T1',0.24);
insert into heroes values (3,'Ma Chao','T1',10.26);
insert into heroes values (4,'Guan Yu','T2',1.56);
insert into heroes values (5,'Meng Tian','T3',2.10);

-- 
select  id,
a.`name`, 
a.popularity,concat(a.ban,'%') as probability
  from heroes a 
  where 1=1
  and (a.id%2=1) 
  and a.popularity<>'T3'
  order by ban desc;
  
--
SELECT CONCAT(TRUNCATE(10.26,2),'%') as colname;
SELECT CONCAT(TRUNCATE(0.90,2),'%') as colname;

-- 查询序号
SET @row_number = 0;
select ( @row_number := @row_number + 1 ) AS id,a.`name`, 
 a.popularity,concat(a.ban,'%')  from heroes a where 1=1
and a.id=1;

/*给定一个 coins 表，其中的 side 属性表示硬币的正反面，
p 表示正面 (positive)，n 表示反面 (negative) 交换所有的 p 和 n 值（例如，将所有 p 值改为 n，反之亦然）
*/

drop table coins;
create table `coins`(
    `id` int unsigned auto_increment comment 'id',
    `side` varchar(32) not null comment '硬币的面', 
     primary key ( `id` )
) engine=innodb, default charset=utf8;

select *from coins;

truncate coins;

insert into coins values(1,'p');
insert into coins values(2,'n');
insert into coins values(3,'p');
insert into coins values(4,'n');
--
show variables like 'SQL_SAFE_UPDATES';
SET SQL_SAFE_UPDATES = 0;

-- 设置相反的值
 update coins set side = (
 case side 
	when 'p' then 'n'
	when 'n' then 'p'
 end);
 
/*
online_class_situations 表展示了一些同学上网课的行为活动。
每行数据记录了一名同学在退出网课之前，当天使用同一台设备登录课程后听过的课程数目（可能是0个）。
写一条 SQL 语句，查询每位同学第一次登录平台听课的日期
*/
drop table if exists online_class_situations;

create table `online_class_situations`(
    student_id int not null comment '学生 id',
    device_id int not null comment '设备 id', 
    date date not null comment '课程的上课日期', 
    course_number int not null comment '课程数量', 
    primary key (student_id, date )
)engine=innodb, default charset=utf8 comment '网课上课情况表';

select *from online_class_situations;
insert into online_class_situations values(1,2,'2020-03-01',5);
insert into online_class_situations values(1,2,'2020-04-02',6);
insert into online_class_situations values(2,3,'2020-05-25',1);
insert into online_class_situations values(3,1,'2020-03-02',0);
insert into online_class_situations values(3,4,'2020-12-03',5);
--
select student_id, date as earliest_course_date from online_class_situations; 
-- 
select datediff('2020-03-01','2020-04-02') as neardate;
-- 
select student_id,min(date) as earliest_course_date 
from online_class_situations
where course_number > 0
group by student_id;


/*
rooms 表记录了公租房的租客信息 (tenant_id) 和租金 (rent)
tenants 表记录了租客的姓名 (name)
请编写 SQL 语句，查询所有房间的id、租金和他的租房人姓名，如果还没有租客，则为 null
*/
drop table if exists rooms;
drop table if exists tenants;

create table `rooms`(
    `id` int unsigned auto_increment comment 'id主键',
    `tenant_id` int comment '租客 id', 
    `rent` int not null comment '租金', 
     primary key ( `id` )
) engine=InnoDB,default charset=utf8;

create table `tenants`(
    `id` int unsigned auto_increment comment '主键',
    `name` varchar(50) not null comment '租客姓名', 
    primary key ( `id` ) 
) engine=InnoDB,default charset=utf8;
 
 -- insert rooms 
insert into rooms values(1,	2,	300);
insert into rooms values(2	,3	,400);
insert into rooms values(3,	null,	300);
insert into rooms values(4	,1	,500);

-- insert tenants;
insert into tenants values(1,	'zhangsan');
insert into tenants values(2,	'lisi');
insert into tenants values(3,	'wanger');

select a.id,a.rent,b.`name` from rooms a 
 left join tenants b on a.tenant_id=b.id;

-- 字符串拼接
select concat('汪','宝宝')  as `name`;

-- 字符串拼接
-- select concat(concat('汪','宝宝'),',早上好呀~')  as `say hello`;

--
drop table if exists patients;

create table `patients`(
    `id` int unsigned auto_increment comment 'id',
    `name` varchar(50) not null comment '患者姓名', 
    `infected_by_id` int comment '感染者 id', 
    primary key ( `id` )
) engine=InnoDB, default charset=utf8 comment '患者表'; 

insert into patients values(1,'Amy',null);
insert into patients values(2,'Bob',null);
insert into patients values(3,'Catalina',2);
insert into patients values(4,'Deng',null);
insert into patients values(5,'Eason',1);
insert into patients values(6,'Frank',2);
-- 
select *from patients a where infected_by_id!=2 or  infected_by_id is null;

select `name` from patients where infected_by_id!=2 or  infected_by_id is null;

/*
exams 表中存放着同学们的考试记录
请用 SQL 语句，找到挂科数最多的同学所对应的 student_id
*/
drop table if exists exams;

create table `exams`(
    `id` int unsigned auto_increment,
    `student_id` int not null,
    `date` date not null, 
    `is_pass` int not null, 
    primary key ( `id` )
)engine=InnoDB, default charset=utf8 comment '考试表';

insert into exams values(1,	1,	'2020-11-15',	0);
insert into exams values(2,	2,	'2020-11-17',	1);
insert into exams values(3,	3	,'2020-11-24',	0);
insert into exams values(4	,3,	'2020-11-28',	0);

select *from exams;

select student_id as '学生ID' ,count(date) as notpass
 from exams 
 where is_pass=0
 group by student_id order by notpass desc  limit 1 ;
 
-- 查询最大挂科次数的学生ID和挂科次数
 select student_id from exams 
 where is_pass=0
 group by student_id order by count(date) desc  limit 1 ;
 
 
 -- 结果
select student_id  from  (
select student_id,count(date) as notpass
	from exams 
    where is_pass=0 group by student_id ) a 
group by a.student_id 
order by max(notpass)  desc limit 1;

-- 删除students表
drop table if exists students;
/*
张三考上了某知名学府，记者前来张三的学校进行采访。
students 表中记录了学生的姓名以及班级 (class_id)，请编写 SQL 语句，
从学生表 (students) 中找出所有姓 "zhang" 的学生的姓名。
中国的姓名习惯是先姓后名，本题要求查找 "zhang" 开头的字符串
*/
create table `students`(
    `id` int unsigned auto_increment,
    `name` varchar(50) not null comment '学生姓名',
    `class_id` int not null comment '学生班级',
    primary key ( `id` )
)engine=InnoDB, default charset=utf8 comment '学生表';

insert into students values(1,'zhangsan',2);
insert into students values(6,	'zhangfei',	3);
insert into students values(8,	'liubei',	3);
--
select a.name from students a where 1=1 and  a.name like 'zhang%';


drop table if exists users;

create table `users`(
    `username` varchar(50) not null,
    `password` varchar(16) not null,
    `email` varchar(50) not null,
    primary key ( `username` )
)engine=InnoDB, default charset=utf8 comment '用户表';
truncate users;
insert into users values ('zhangsan','zs789852',	'zhangsan@gmail.com');
insert into users values ('lisi','ls654852',	'lisi@126.com');

select username,email from users a where 
 trim(a.username)='zhangsan' and trim(a.password)='zs789852';
 
 
/**
李华的作业是判断三条线段是否可以构成直角三角形
假设表 line_segments 保存了所有由三条长度为 a, b, c 的线段构成的组，请你帮李华写一个 SQL 语句，
来判断每组线段是否可以组成一个直角三角形
**/
drop table if exists line_segments;

create table `line_segments`(
    `id` int unsigned auto_increment,
    `a` int not null,
    `b` int not null,
    `c` int not null,
    primary key ( `id` )
);
insert into line_segments values(1,	3,	4,	5);
insert into line_segments values(2,	10,	20,	15);
--  right_triangle

select id,a,b,c ,(pow(a,2)+pow(b,2)=pow(c,2)) as right_triangle from line_segments;


select id,a,b,c ,(case (pow(a,2)+pow(b,2)=pow(c,2)) when 1 then 'Yes' when 0  then 'No'  end )
  as right_triangle from line_segments;

/*
shared_bicycles 表中存储着共享单车的使用信息，包括单车 id (bike_id) 和使用者 id (user_id)
编写一条 SQL 语句，找到被同一个人至少使用过三次的共享单车 id 和使用者 id
*/
drop table if exists shared_bicycles;

create table `shared_bicycles`(
    `id` int unsigned auto_increment,
    `bike_id` int not null comment '单车id',
    `user_id` int not null comment '使用者id',
    primary key ( `id` )
) comment '共享单车表';
insert into shared_bicycles values(1,	1	,1);
insert into shared_bicycles values(2,	1,	1);
insert into shared_bicycles values(3,	1	,1);
insert into shared_bicycles values(4	,1	,2);
insert into shared_bicycles values(5,	1,	2);
insert into shared_bicycles values(6,	2	,1);
insert into shared_bicycles values(7,	2	,1);
-- query 


select bike_id,	user_id from shared_bicycles;

select bike_id,	user_id
 from shared_bicycles  group by bike_id,user_id 
 having count(id)>=3;
 
/*
rankings 表记录了某俱乐部年度比赛的排名及得分信息，包括项目 id (category_id)，年份 (year)，
排名 (rank) 以及分数 (score);
categories 表记录了项目的名称 (name)，因为某些原因，categories 表中可能存在数据丢失情况，
请编写 SQL 语句，查询 rankings 表和 categories 表中所有项目对应的项目名称 (name)、
该项目的比赛年份 (year) 和分数 (score)
*/
drop table if exists rankings;
drop table if exists categories;

create table `rankings`(
    `id` int unsigned auto_increment,
    `category_id` int not null comment '项目id',
    `year` int not null,
    `rank` int not null comment '排名',
    `score` int not null comment '得分',
    primary key ( `id` )
) comment '排名表';

create table `categories`(
    `id` int unsigned auto_increment comment '主键',
    `name` varchar(50) not null comment '项目名称',
    primary key ( `id` )
) comment '项目类别表';
insert into rankings values(1,1,2008,15,90);
insert into rankings values(2,1,2012,11,98);
insert into rankings values(3,2,2016,10,99);

select *from rankings;

insert into categories values(1,'volleyball');
insert into categories values(2	,'basketball');
insert into categories values(3,'soccer');
select *from categories;
-- 
select b.name,	a.year,	a.score from rankings a 
 inner join categories b 
 on a.category_id =b.id;
 
/*
请编写 SQL 语句，查询课程表 courses 中学生人数 student_count 超过 800 的所有课程，
并返回满足查询条件的全部课程信息
*/
drop table if exists `courses`;
create table `courses`  (
	`id` int unsigned not null auto_increment comment 'id',
	`name` varchar(64)  not null comment '课程名称' ,
	`student_count` int unsigned not null comment '学生总数',
	`created_at` date not null comment '开课时间' ,
	`teacher_id` int unsigned not null,
	primary key (`id`) 
) comment '课程信息';

select *from courses;
insert into courses values (1,'Advanced Algorithms',880	,'2020-6-1',4);
insert into courses values (2,'System Design',1350,'2020-7-18',3);
insert into courses values (3,'Django',780,'2020-2-29',3);
insert into courses values (4,'Web',340,'2020-4-22',4);
insert into coutses values (5,'Big Data',700,'2020-9-11',1);
-- 
insert into coutses values (5,'Big Data',700,'2020-9-11',1);
insert into courses values (10,'Object Oriented Design',300,'2020-8-8',4);
insert into courses values (12,'Dynamic Programming',2000,'2018-8-18',1);

/*
请编写 SQL 语句，查询课程表 courses 中课程名称以大写字母 'D' 到 'O' （包含单个字符 'D'、'O'）开头的课程的课程名称
*/
-- 方法1
select name from courses where 1=1 and substr(name,1,1) between 'D' and 'O';
 
-- 方法2 正则表达式
select name from courses where name regexp '^[d-o]';

/*
 请编写 SQL 语句，查询 courses 表中，课程名首两个字母在 'Db' 和 'Dy' 之间所有课程的名称
*/

-- Object Oriented Design
SELECT SUBSTRING_INDEX('Object Oriented Design',' ',1)as str;
SELECT SUBSTRING_INDEX(SUBSTRING_INDEX('Object Oriented Design',' ',2),' ',-1)as str;
-- 方法1
select name from courses where 1=1
and  left(name,2)  between 'Db' and 'Dy';
-- 方法2
select name  from courses
where 1=1 and  name regexp '^d[b-y]';

-- and SUBSTRING_INDEX(SUBSTRING_INDEX(name,' ',2),' ',-1) regexp '^[d-y]';


-- 学生人数超过 800 但是不包括 800
select id,a.`name`,student_count as student_count ,created_at as	created_at,
 teacher_id from courses a where student_count>800;
 
 -- 请编写 SQL 语句，使用 BETWEEN AND 查询课程表 courses 中开课日期为 2020 年 6 月到 2020 年 8 月的所有课程信息
 select id,	name,student_count,created_at,teacher_id 
 from courses where created_at between '2020-06-01'  and '2020-08-31' ;
 
-- 教师信息表
drop table if exists `teachers`;
create table `teachers`  (
  `id` int unsigned not null auto_increment,
  `name` varchar(64) not null,
  `email` varchar(64) ,
  `age` int unsigned not null,
  `country` varchar(32) not null,
  primary key (`id`)
) comment '教师信息表';

 /*
 请编写 SQL 语句，查询教师表 teachers 中除了年龄 age 在 20 岁以上 (不包括 20 岁) 的中国 (CN)
 教师以外所有教师信息
 */
 select id,name,email,age,country
FROM  teachers
where 1=1
and email is null or email='';


 -- 方法1
select  id,b.`name`,email,age,country from teachers b where id not in(
select id from teachers a where 1=1 and a.country='cn' and age>20);
 -- 方法2
select id,b.`name`,email,age,country from teachers where not (age > 20 and country = 'cn');

--
/*
请编写 SQL 语句，查询课程表 courses 中课程创建时间 created_at 在 '2020-01-01' (包括) 到 '2020-05-01' (不包括) 
之间的所有课程名称和课程创建时间
*/
 select a.`name` ,created_at from courses a
  where  (a.created_at>='2020-01-01' and  a.created_at<'2020-05-01');
 
 /*
 请编写 SQL 语句，查询课程表 courses 中开设在 2020 年内的所有课程信息
 */
  select a.`name` ,created_at from courses a
  where  year(a.created_at)='2020' ;
 
 /*
 请编写 SQL 语句，查询课程表 courses 中教师 id 为 4，且上课人数在 500 以上（不包括 500 人）的所有课程信息
 */
 select id,name,student_count,created_at,teacher_id
 from courses where 1=1
  and teacher_id=4 and student_count>500;
  
-- 请编写 SQL 语句，使用 IN 查询教师表 teachers 中国籍为中国 (CN) 或英国 (UK) 的所有教师信息
SELECT id,a.`name`,email,age,country from teachers a 
 where 1=1 and a.country in ('CN','UK'); 
 
-- 
 SELECT id,a.`name`,email,age,country from teachers a 
 where 1=1 and not a.country  in ('CN','UK'); 
 
 /*
 请编写 SQL 语句，查询教师表 teachers 中教师年龄在 20 到 25 岁之间，包括 20 岁和 25 岁，
 且国籍不为中国和英国的教师，最后返回所查询教师的全部信息
 */
select id,name ,email,age,country
  from teachers
  where 1=1
  and (age>=20 and age<=25)
  and  country not in('uk','cn');
  
/*
请编写 SQL 语句，查询课程表 courses 中学生数量在 50 到 55 之间的所有课程信息
*/
select id,name,student_count,created_at,teacher_id
from courses 
 where 1=1
 and student_count between 50 and 55;
 
 /*
 请编写 SQL 语句，查询教师表 teachers 中年龄不在 20 到 30 岁之间的教师信息
 */
 select id,name ,email,age,country from 
  teachers where 1=1
  and age not between 20 and  30;
  
-- 请编写 SQL 语句，查询教师（ID）不在 5 到 10 之间，且国籍为中国（CN）的所有教师信息
 select id,name ,email,age,country from 
  teachers where 1=1
  and country='cn'
   and id not between 5 and 10  ;
```