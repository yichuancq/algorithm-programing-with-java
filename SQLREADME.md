# java-learning
>mysql学习笔记

```roomsql
-- create database lintcode default charset=utf8
use lintcode;

desc rankings;
desc sapling_distances;
desc students;
desc enrollments;
desc companies;
desc recording;
--
desc categories;
select *from rankings;

drop table if exists teachers_bkp;

create table `teachers_bkp`  ( 
	  `id` int unsigned not null auto_increment, 
	  `name` varchar(64) not null, 
	  `email` varchar(64) , 
	  `age` int unsigned not null, 
	  `country` varchar(32) not null, 
	  primary key (`id`) 
);
        
drop table if exists student_heights;

create table `student_heights`(
    `height` int not null
);

-- 查询教师表 teachers 和课程表 courses，查询 'Western Venom' 老师所教的所有课程信息
insert into teachers_bkp (select *from teachers );

select c.id,c.name,c.student_count,c.created_at,c.teacher_id
 from teachers t inner join  courses c
 on t.id=c.teacher_id
 and t.name='Western Venom';

-- 
insert into student_heights values(178);
insert into student_heights values(178);
insert into student_heights values(173);
insert into student_heights values(173);
insert into student_heights values(171);
insert into student_heights values(174);
insert into student_heights values(175);
insert into student_heights values(176);
-- 请编写一个 SQL 语句，在只出现过一次的身高中，找出最高的身高
select height ,count(height)  as number 
 from student_heights group by height order by number desc;
-- 
select max(height) as height from (
select height ,count(height)  as number 
 from student_heights group by height having number=1) a;
 
-- 
drop table if exists sapling_distances;

create table `sapling_distances`(
    `id` int unsigned auto_increment,
    `distance` int not null,
    primary key ( `id` )
);
-- 树苗距离表 
insert into `lintcode`.`sapling_distances` (`id`, `distance`) values ('1', '8');
insert into `lintcode`.`sapling_distances` (`id`, `distance`) values ('2', '1');
insert into `lintcode`.`sapling_distances` (`id`, `distance`) values ('3', '4');

-- companies 表存储了所有公司的信息，包括公司 id 和公司名称 name
create table `companies`(
    `id` int unsigned auto_increment,
    `name` varchar(50) not null,
    `address` varchar(50) not null,
    primary key ( `id` )
);
-- recording 表存储了所有的简历投递数据，包括学生 id (student_id) 和 公司 id (company_id)
desc  recording;
create table `recording`(
    `id` int unsigned auto_increment,
    `delivery_date` date not null,
    `company_id` int not null,
    `student_id` int not null,
    primary key ( `id` )
);
-- 
drop table if exists classes;
-- 创建班级表 
create table `classes`(
    `id` int unsigned auto_increment,
    `name` varchar(50) not null,
    `teacher_id` int not null,
    primary key ( `id` )
);
-- 插入班级表
insert into classes values(1,'Class 1',2);
insert into classes values(2,'Class 2',1);
insert into classes values(3,'Class 3',3);
insert into classes values(4,'Class 4',2);

-- 插入公司表
insert into companies values(1,	'Alibaba','Hang Zhou');
insert into companies values(2,'NetEase'	,'Guang Zhou');
insert into companies values(3,'Baidu'	,'Bei Jing');
insert into companies values(4,'Tencent','Shen Zhen');

-- 学生表
create table students(
  id int unsigned AUTO_INCREMENT not null comment '学生ID',
  student_name varchar(30) not null comment '学生姓名',
  phone varchar(50) comment '学生电话',
  primary key (id)
)engine=innodb default charset=utf8 comment='学生表' ;

-- 请编写 SQL 语句，查询所有没有向阿里巴巴 (Alibaba) 公司投递过简历的学生姓名
-- 方法1
select t.name,t.id from students t where t.name not in(select s.name from students s ,companies c ,recording r
where 1=1
and s.id=r.student_id and c.id =r.company_id
and c.name='Alibaba') ;
-- 方法2
select s.name from students s where 1=1 and s.id not in(
select r.student_id from recording r inner join companies c 
on r.company_id=c.id and c.name='Alibaba');

-- 请编写 SQL 语句，查询接收简历最多的公司名称和地址
select name,address from companies where id in(
select company_id from recording group by company_id 
 having count(company_id)>1 order by count(company_id)  desc);
 --
 select name,address from companies where id in(
select company_id,count(id) as max_count from recording group by company_id 
order by count(company_id)  desc limit 1);


select c.name, c.address from recording r  inner join companies c 
on r.company_id = c.id 
group by c.name, c.address
having count(*) = (
select max(max_count) from (
select c.name,c.address ,count(r.id) as max_count from companies c inner join recording r 
  on c.id=r.company_id group by c.id ,c.name desc) t);
 
-- 请编写一个 SQL 语句，找到最近的两棵树苗的距离 (shortest_distance)
--   
select  min(abs(a.distance - b.distance)) as shortest_distance
  from sapling_distances a inner join  sapling_distances b
  on a.id != b.id having shortest_distance != 'none';

desc recording;

-- drop table students;
-- 请编写 SQL 语句，找出 "zhangsan" 同学所在班级的班级名称
select c.name from classes c inner join students s on c.id=s.class_id where 1=1
 and s.name='zhangsan';
 
 -- 请编写 SQL 语句，找出 "xujia" 老师所带的班级所有同学的姓名
 select s.name from students s inner join classes c on s.class_id=c.id
 inner join teachers t on t.id=c.teacher_id
  where 1=1
  and t.name='xujia';
  
 
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
 
desc online_class_situations;
/*
在创建数据表时，因为数据库工作人员忘记添加主键约束，现在我们需要对课程表 courses 添加主键约束，
将 id 列设置为主键，请编写相应的 SQL 语句
*/
-- alter table 表名 modify 列名 数据类型 primary key
alter table courses modify id INT(10) unsigned primary key;

-- 删除主键约束
-- alter table 表名 drop primary key


-- SELECT c.name as course_name ,t.name as teacher_name
-- from courses c cross join teachers t 
-- on c.teacher_id =t.id ;


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
 
-- 查询 courses 表，计算从 2018 年 01 月 13 日到创建课程时间（created at）相差天数，结果列名请以 date_diff 显示
select datediff(created_at,'2018-01-13 ') as date_diff 
  from courses;
  
-- 从课程表 courses 中查询所有课程的课程名称（ name ）和课程创建时间（ created_at ）的小时数，将提取小时数的列名起别名为 created_hour
select name, hour(created_at) as created_hour from courses;
-- 从课程表 courses 中查询课程的名字和创建年份，并为 created_at 起别名为 created_year
SELECT name ,year(created_at) as created_year FROM courses;
  
/*
将教师表 teachers 和课程表 courses 进行左连接，查询来自中国（讲师国籍 country ='CN' ）
的教师名称以及所教课程名称，结果列名请分别以课程名称 course_name ，
教师名称 teacher_name 显示
*/
select courses.name as course_name ,teachers.name as teacher_name  from teachers left join
 courses on teachers.id=courses.teacher_id WHERE 1=1 and  country ='CN';

/*
将课程表 courses 和教师表 teachers 进行右连接，查询教师姓名，邮箱以及所教课程名称，
 结果列名请分别以课程名称 course_name ，
教师姓名 teacher_name ，教师邮箱 teacher_email 显示
*/
select c.name as  course_name ,t.name as teacher_name,t.email as teacher_email 
 from  courses c right join teachers t
  on c.teacher_id=t.id ;
  
  select c.name as  course_name ,t.name as teacher_name,t.email as teacher_email ,
  t.country
	from courses c right join teachers t 
	on c.teacher_id=t.id 
  WHERE 1=1 and  country ='CN';
/*
将课程表 courses和教师表 teachers 进行全连接，查询课程名称以及对应授课教师的年龄，
结果列名请分别以课程名称 course_name 和教师年龄 teacher_age 显示
*/
select c.name as course_name,
 t.age as teacher_age 
 from courses c right join teachers t on c.teacher_id=t.id ;
      

-- Write your SQL Query here --
-- example: SELECT * FROM XX_TABLE WHERE XXX --
select c.`name` as course_name, t.age as teacher_age from teachers t
left join courses c on t.id = c.teacher_id
union
select c.`name` as course_name, t.age as teacher_age from teachers t
right join courses c on t.id = c.teacher_id;
-- 将课程表 courses 和教师表 teachers 进行外连接，查询所有课程名称以及与其相互对应的教师名称和教师国籍，结果列名请分别以课程名称 course_name 、
-- 教师名称 teacher_name 、教师国籍 teacher_country 显示
select c.name as course_name,t.name as teacher_name,
 t.country as teacher_country 
 from courses c right join teachers t on c.teacher_id=t.id 
 union
 select c.name as course_name,t.name as teacher_name,
 t.country as teacher_country 
 from courses c left join teachers t on c.teacher_id=t.id ;
 -- 
 select c.`name` as course_name, t.email as teacher_email from courses c
left join teachers t on t.id = c.teacher_id;
/*
查询教师表 teachers ，统计不同国家教师的人数，并将结果按照不同国籍教师人数升序排列，
如果相同教师数量则按照国籍名称升序排列，返回列名显示为 teacher_count
*/
select t.country,count(t.country) as teacher_count
  from teachers t
 group by t.country order by teacher_count asc , t.country asc;
 
/*
查询教师表 teachers，统计不同年龄教师的人数，并将结果按照年龄从大到小排列，返回列名显示为 age_count
*/
select age,count(age)	as age_count from teachers
where 1=1
group by age  order by age desc;


/*
连接 courses 与 teachers 表，统计不同教师所开课程的学生总数，对于没有任课的老师，学生总数计为 0 
，最后查询学生总数少于 3000 的教师姓名及学生总数 （别名为 student_count ），
结果按照学生总数升序排列，如果学生总数相同，则按照教师姓名升序排列
*/
select t.name as name , sum(ifnull(student_count,0) )as student_count from
 teachers t left join courses c 
  on c.teacher_id=t.id  where 1=1
  and c.student_count<3000
  group by t.name
  order by student_count asc, t.name asc;
  
select t.name as name , sum(ifnull(student_count,0) )as student_count from
 teachers t left join courses c 
  on c.teacher_id=t.id  where 1=1
  -- and c.student_count<3000
  group by t.name
  having student_count<3000
  order by student_count asc, t.name asc;
-- 向记录表 records 中插入当前的日期
INSERT into records VALUES(date(now()));
/*
从 teachers 表中查询教师名字为 Eastern Heretic 的信息，
并根据教师 id 将教师 Eastern Heretic 创建的课程名称全部改为 PHP，并将学生总数设为 300 人
*/
select t.id from teachers t where 1=1 and t.name ='Eastern Heretic';

update courses set student_count=300,name ='name' where courses.teacher_id=
(select t.id from teachers t where 1=1 and t.name ='Eastern Heretic');
-- 
select c.name as course_name,
 t.age as teacher_age 
 from courses c right join teachers t on c.teacher_id=t.id ;

-- 查询课程表中课程的创建时间，以 ‘时:分:秒’ 格式输出，最后返回的列名为 created_at
select DATE_FORMAT(created_at,  '%h:%i:%s')  as created_at from courses;
  
-- 查询 courses 表，查询课程创建时间，按照 ’年-月-日 时：分：秒’ 的格式返回结果，返回列名显示为 DATE_FORMAT
  
select  date_format(created_at, '%Y-%m-%d %H:%i:%s') as DATE_FORMAT from courses;
  
-- select DATE_FORMAT(now(),  '%H:%i:%s')  as created_at from courses;
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
每行数据记录了一名同学在退出网课之前，当天使用同一台设备登录课程后听过的课程数目（可能是0个）。
写一条 SQL 语句，查询每位同学第一次登录平台听课的设备ID (device_id)
*/
select student_id,device_id 
 from (select student_id,min(device_id) AS device_id,min(date) as earliest_course_date 
 from online_class_situations
 where course_number > 0
 group by student_id) a;
 
--
desc exams;

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

insert into exams values(1,1,'2020-11-10',0);
insert into exams values(2,2,'2020-11-17',1);
insert into exams values(3,3,'2020-11-24',2);
insert into exams values(4,3,'2020-11-28',0);
insert into exams values(5,1,'2020-11-29',0);
truncate exams;
select *from exams;

select student_id as '学生ID' ,count(date) as notpass
 from exams 
 where is_pass=0
 group by student_id order by notpass desc  limit 1 ;
 
-- 查询最大挂科次数的学生ID和挂科次数
 select student_id from exams 
 where is_pass=0
 group by student_id order by count(date) desc  limit 1 ;
 
/*
 请用 SQL 语句，找到挂科数最多的同学所对应的 student_id
 期望答案
| student_id |
| :--------- |
| 1          |
挂科数量最多的同学有多位，所以我们需要拿到最大的挂科次数，然后和每个同学的挂科次数进行比较，得到相同的即为挂科数最多的同学
*/ 
select student_id from exams
where is_pass = 0 group by student_id
having count(*) = (select count(*) from exams where is_pass = 0 group by student_id order by count(*) desc limit 1);

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
insert into courses values(13,'Python',400,'2021-05-23',3);

/*
将课程表 courses 和教师表 teachers 进行交叉连接，并结合 WHERE 子句，查询课程创建时间和教师国籍，
结果列名请分别以课程创建时间 course_date 和教师国籍 teacher_country 显示
*/
select c.created_at as course_date ,t.country as teacher_country 
 from courses c left join teachers t 
 on c.teacher_id=t.id  where 1=1  and !isnull(t.country )
 union
 select c.created_at as course_date ,t.country as teacher_country 
 from courses c right join teachers t 
 on c.teacher_id=t.id where 1=1 and  !isnull( c.created_at);
 
 --
 select c.created_at as course_date ,t.country as teacher_country 
 from courses c cross join teachers t 
 on c.teacher_id=t.id;

-- 将教师表 teachers 和课程表 courses进行左连接，查询教师名称以及所教课程名称，
-- 结果列名请分别以课程名称 course_name ，教师名称 teacher_name 显示
select c.name as course_name,t.name as teacher_name 
 from teachers t left join courses c on c.teacher_id=t.id;
 
 /*
 将课程表 courses 和教师表 teachers 进行内连接，查询 “Eastern Heretic” 老师所教的所有课程的课程名和课程编号 , 
 且结果列名分别以课程编号 id 、课程名称 course_name 和教师姓名 teacher_name 显示
 */
 
select c.id,c.name as course_name ,t.name as teacher_name 
 from courses c inner join teachers t on c.teacher_id=t.id
  where 1=1
  and t.name ='Eastern Heretic';
  
/*
联合教师表（teachers）和课程表（courses） 查询课程表中国籍不为 USA 和 UK 的老师所教所有课程的 id 
和 所对应的学生数量 student_count*/

select c.id,c.student_count as student_count
  from courses c inner join  teachers t on c.teacher_id=t.id
  and t.country not in('USA','UK');
  
/*
查询教师表 teachers 和课程表 courses,统计每个老师教授课程的数量，并将结果按课程数量从大到小排列，
如果相同课程数量则按照教师姓名排列，返回列名老师姓名列名显示为 teacher_name ，课程数量列名显示为 course_count
*/
select t.name as teacher_name,count( c.name) as  course_count
   from courses c right join  teachers t on c.teacher_id=t.id
   group by t.name
   order by course_count desc, teacher_name asc;
-- country

/*
连接 courses 与 teachers 表，统计不同国籍教师所开课程的学生总数，对于没有任课的老师，学生总人数计为 0 。
最后查询教师国籍是 'U' 开头且学生总数在 2000 到 5000 之间的教师国籍及学生总数 （别名为 student_count ），
结果按照学生总数降序排列，如果学生总数相同，则按照教师国籍升序排列*/

select t.country as country,sum(c.student_count ) as student_count
    from courses c inner join  teachers t on c.teacher_id=t.id
    where 1=1 and  country regexp '^U' and
    student_count between 2000 and 5000
    group by t.country 
    order by student_count desc, t.country asc;
 
-- 请编写 SQL 语句，为课程表 courses 中的 teacher_id 添加外键约束，使之能与教师表 teachers 中的 id 相关联

-- 修改外键参照
alter table courses add foreign key(teacher_id) references teachers(id);

-- 从课程表 courses 中查询第一季度（1月、2月、3月）创建的课程
select name,quarter(created_at) as created_at from courses where 1=1
and created_at in(1,2,3);

select name,created_at as created_at from courses where 1=1
and quarter(created_at) in(1,2,3);

/*
查询课程表 courses 所有课程中的课程名（name）和创建日期中的年份（别名：year）和月份（ 别名：month）
*/


-- 查询季度
select  quarter(curdate()) in(3,4,5) ;


select name , year(created_at) as 'year' ,month(created_at) as month
 from  courses;
 /*查询课程表 courses 中，教师 id teacher_id 不为 3，且学生人数 student_count 超过 800 的所有课程，
 最后返回满足条件的课程的所有信息
 */
select id,name,student_count,created_at,teacher_id
 from courses where 1=1
 and teacher_id<>3 and student_count>800;
 
/*
查询课程表 courses 中教师 id （teacher_id）为 1、2 或 3 的课程名称、教师 id 和课程创建时间，
并将结果集按教师 id 升序排列，如果教师 id 相同，则按照课程创建时间降序排列
*/

 select name,teacher_id, created_at from courses where 1=1
 and teacher_id in(1,2,3) order by id asc, created_at desc;
 
/*
修改课程表 courses 中课程的课程创建日期，将课程创建日期均往后推迟一年，
最后返回课程名称 name 及修改后的课程创建日期，修改后的课程创建日期命名为 new_created 
*/
select adddate("2017-06-15", interval 10 day);

select adddate("2017-06-15", interval 1 year);
-- 日期增加
select date_add("2017-06-15", interval 1 day) as new_date;

update courses set created_at= adddate(created_at, interval 1 year);
select name , adddate(created_at, interval 1 year) as new_created from courses;
-- 提前一月
select name , adddate(created_at, interval -1 month) as new_created from courses;
-- 方法2
select name , date_sub(created_at, interval 1 month) as new_created from courses;
select id,name , date_sub(created_at, interval 1 day) as new_created from courses;

select name , second(created_at) as created_second from courses;

/*
请编写 SQL 语句，查询课程表 courses 中课程名称以大写字母 'D' 到 'O' （包含单个字符 'D'、'O'）开头的课程的课程名称
*/
-- 方法1
select name from courses where 1=1 and substr(name,1,1) between 'D' and 'O';
 
-- 方法2 正则表达式
select name from courses where name regexp '^[d-o]';


-- 请编写 SQL 语句，查询课程名 name 以字母 'D' 开头的所有课程信息

select id,name,student_count,created_at,teacher_id
 from courses where 1=1
 and name regexp '^[D]';

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
 
 -- 请编写 SQL 语句，查询课程表 courses 中学生人数（student_count）最少的三门课程的信息，且结果按升序排序
 select id,a.`name`,student_count as student_count ,created_at as	created_at,
 teacher_id from courses a
  order by student_count asc limit 3;
 /*
查询 courses 表，计算课程表中创建课程时间（created_at）与 2021年4月1日的年数差，
结果课程名称列显示为 courses_name ,创建课程时间显示为 courses_created_at ，年数差列名显示为 year_diff
*/

select name as courses_name, 
   created_at as courses_created_at ,
   timestampdiff(year,date_format(created_at, '%y-%m-%d %h:%i:%s'),'2021-04-01 00:00:00') as  year_diff 
    from courses
   where 1=1;
   
select datediff('2021-04-01',created_at) from courses as year_diff;

select timestampdiff(year,'1993-03-23 00:00:00',date_format(now(), '%y-%m-%d %h:%i:%s'));

-- 计算课程创建时间与 '2020-04-22' 的月数差，返回列名显示为 MonthDiff
-- MonthDiff
select timestampdiff(month,date_format(created_at, '%y-%m-%d'),'2020-04-22')
 as MonthDiff from  courses;
 
 -- 从课程表courses中查询2020年8月前的课程名和创建日期，
 -- 并为创建日期的列名起别名为 created_date (日期指 created_at 中不包括具体时间的部分)
 select name, date(created_at)  as  created_date from courses
 where 1=1
 and datediff(created_at,'2020-08-08')<0;
 
 
/*
从课程表 courses 中查询课程的名字name 和课程创建时间 created_at，从课程创建时间created_at 
中提取出创建课程的日期与时间，用created_date 和created_time作为结果集列名
*/
select name,created_at,date(created_at) as created_date, time(created_at) as created_time
  from courses;

 /*
 从课程表 courses 中查询课程的名字 name 和课程创建时间 created_at，
 从课程创建时间 created_at 中提取出创建课程的日期，用 created_date 作为结果集列名
 */
select name, date(created_at) as created_date from courses;

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

insert into teachers values(1,'Eastern Heretic','eastern.heretic@gmail.com',20,'UK');
insert into teachers values(2,	'Northern Beggar',	'northern.beggar@qq.com',	21	,'CN');
insert into teachers values(3,	'Western Venom',	'western.venom@163.com',	28,	'USA');
insert into teachers values(4,	'Southern Emperor'	,'southern.emperor@qq.com',	21	,'JP');
insert into teachers values(5, 'Linghu Chong',null,18,'CN');

-- 查询教师表 teachers 中教师邮箱为 'qq.com' 结尾的年龄的平均值，最后返回结果列名显示为 'average_teacher_age'
select  avg(age) as  average_teacher_age from teachers
 where 1=1 and email regexp '@qq.com$'; 
 
 select avg(age) as  average_teacher_age from  teachers
 where 1=1 and email like  '%@qq.com';



-- 请编写 SQL 语句，查询教师表 teachers 中，所有使用 qq 邮箱的教师名字和邮箱
-- 方法1
select name,email from teachers where 1=1
and email like '%qq.com';

-- 方法2  返回字符串 s 的后 n 个字符
select name,email from teachers where 1=1
and right(email,length('@qq.com'))='@qq.com';
-- 正则表达式
select name,email from teachers where 1=1
and email regexp '@qq.com$';

 /*
 请编写 SQL 语句，查询教师表 teachers 中除了年龄 age 在 20 岁以上 (不包括 20 岁) 的中国 (CN)
 教师以外所有教师信息
 */
 select id,name,email,age,country
FROM  teachers
where 1=1
and email is null or email='';

-- 将教师表 teachers 中教师名 (name) 为 Linghu Chong 的邮箱 (email) 更新为 "linghu.chong@lintcode.com"
update teachers set  email ='linghu.chong@lintcode.com'
 where name='Linghu Chong';

 -- 方法1
select  id,b.`name`,email,age,country from teachers b where id not in(
select id from teachers a where 1=1 and a.country='cn' and age>20);
 -- 方法2
select id,b.`name`,email,age,country from teachers where not (age > 20 and country = 'cn');
-- 请编写 SQL 语句，使用 LIKE 查询教师表 teachers 中邮箱为腾讯邮箱且国籍为中国的所有教师，并返回所查询教师的全部信息
select id,name,email,age,country
from  teachers
where 1=1
and country='CN'
and email  like '%@qq.com';

 -- 请编写 SQL 语句，查询教师表 teachers 中的中国教师，并按照年龄降序排序

 select id,name,email,age,country
  from  teachers 
  where 1=1
  and country='CN' order by age desc;

 select id,name,email,age,country
  from  teachers 
  where 1=1
  and country='CN' order by age asc;
  
-- 请编写 SQL 语句，从 teachers 表中找出没有邮箱并且年龄大于20岁的教师信息
 select id,name,email,age,country
  from  teachers 
  where 1=1
  and age>20
  and isnull(email);
  



-- 请编写 SQL 语句，查询教师表 teachers 中教师年龄 age 的唯一值，并将结果进行升序排序
select age from teachers group by age asc;

-- 从 teachers 表中找出龄等于 18 并且来自中国 (CN) 的老师信息
select id,name,email,age,country
  from  teachers 
  where 1=1
  and age=18
  and country='CN';
--  查询 courses 表，查询课程创建时间，按照’年 月‘的格式返回结果，返回列名显示为 DATE_FORMAT
select date_format(created_at,'%Y %m') as DATE_FORMAT from courses;

/*
修改 courses 表中课程的课程创建日期，将课程创建日期均推迟一天，
最后返回课程名称 name 及修改后的课程创建时间，修改后的课程创建时间命名为 new_created
*/

select name , date_add(created_at, INTERVAL 1 day) new_created from courses;

-- 统计课程表 courses 中 2020 年 1 月到 5 月之间的课程数量，最后返回统计值，结果列名显示为 course_count

select count(name)  as course_count from courses where 1=1
and created_at between '2020-01-01' and '2020-05-31';

-- 从 courses 表中选取学生人数 student_count 大于 1000 且课程名为 'System Design' 或 'Django' 的所有课程信息
 -- 方法1
 select id,a.`name`,student_count,created_at,teacher_id from courses a
 where 1=1
 and a.`name` in('System Design','Django')
 and student_count>1000;
 -- 查询课程表 courses 中课程名称为 System Design 的课程信息
  select id,a.`name`,student_count,created_at,teacher_id from courses a
 where 1=1
 and a.`name` ='System Design';
 -- 从 courses 表中，选取课程名为 'Web' 或者 'Big Data' 的课程信息，如果这两门课程同时存在，请将这两门课程的信息全部返回
 
 select id,a.`name`,student_count,created_at,teacher_id from courses a
  where 1=1
  and name in('Web', 'Big Data');
  
  -- 请编写 SQL 语句，删除课程表 courses 中课程创建日期 created_at 在 2020 年之前的所有课程
  delete from courses where year(created_at)<'2020';
 
-- 请编写 SQL 语句，查询课程表 courses 中学生人数 student_count 的平均值，
-- 最后返回结果列名显示为 average_student_count

select avg(ifnull(student_count,0)) as average_student_count  from courses;

-- 
select id,name,student_count,created_at,teacher_id
 FROM courses order by student_count asc;
 
/*
请编写 SQL 语句，统计课程表 courses 中不同的教师 id teacher_id 的数量，
最后返回统计值，结果列名显示为 teacher_count
*/
-- 方法1
select count(*) as teacher_count 
 from (select distinct teacher_id  from courses where teacher_id is not null or teacher_id<>'') a;

-- 方法2
select count(*) as teacher_count from 
(select teacher_id 
   from courses where  teacher_id is not null or teacher_id<>''
   group by teacher_id) a;

-- 请编写 SQL 语句，查询课程表 courses 所有课程中最多的学生人数（student_count）

select max(student_count) as max_student_count from courses;

-- 请编写 SQL 语句，从教师表 teachers 中，查询最年长的中国教师信息，并返回该教师的年龄
select max(age) as max_age from teachers 
where 1=1
and country in('CN');



/*
请编写 SQL 语句，查询教师表 teachers 中，国籍为 'CN' 或 'JP' 且 email 信息不为空的所有教师信息
*/
 select id,name,email,age,country
from  teachers
where 1=1
and country in('CN','JP')
and (email is not null or email<>'');


-- 请编写 SQL 语句，查询教师表 teachers 中最小的教师年龄 （age）
select min(age)  as min_age from teachers;

/*
请编写 SQL 语句，统计教师表中年龄在 20 到 28 岁之间，
且国籍为中国或英国的教师人数，最后返回统计值，结果列名显示为 teacher_count
*/

select count(id) as teacher_count from teachers
 where 1=1
 and country in('CN','UK')
 and age between 20 and 28;
 

-- 请编写 SQL 语句，从教师表（teachers）中查询一条年龄最大的中国教师的信息
select id,name,email,age,country
from  teachers
where 1=1
and country ='CN'
order by age desc limit 1;

-- 请编写 SQL 语句，查询教师表 teachers 中的不重复的教师国籍（country）

select country ,count(country) as counts from teachers
where 1=1
group by country 
having count(country)=1;


select country  from teachers
where 1=1
group by country ;

/*
请编写 SQL 语句，查询教师表 teachers 中，20 岁（不包含 20 岁）以上教师的平均年龄，
返回的字段为 avg_teacher_age ，结果保留四舍五入后的整数
*/
select round(avg(age),0) as avg_teacher_age from 
 teachers where 1=1
 and age>20;
 /*
 请编写 SQL 语句，对教师表 teachers 中教师是否拥有邮箱进行判断，最后返回教师姓名和邮箱，
 以及使用函数 ISNULL 、IFNULL 、COALESCE 判断结果
 */
 select name,email,ISNULL(email) as isnull_email ,IFNULL(email,0) 
  as ifnull_email ,COALESCE(email,0)
  as coalesce_email
 from teachers;
 
 select ISNULL(email) from teachers; 

-- 请编写 SQL 语句，查询课程表 courses 中学生人数（student_count）最少的课程的人数

select min(student_count) as min_student_count from courses;

/*
请编写 SQL 语句，统计课程表 courses 中所有课程上课学生人数的总和，
并用 all_student_count 作为结果集列名
*/

select sum(student_count) as all_student_count from courses;
/**
请编写 SQL 语句，统计课程表 courses 中 teacher_id 为 3 的教师所教授的学生总数，并用select_student_sum 作为结果集列名
**/
select sum(ifnull(student_count,0)) as  select_student_sum
from courses
where 1=1
and teacher_id=3;
/*
请编写 SQL 语句，查询课程表 courses 中，课程人数 student_count 的平均值 ，
返回的字段名为 avg_student_count ，且结果保留四舍五入的两位小数
*/
select format(avg(ifnull(student_count,0)),2) as avg_student_count from courses;
select round(avg(ifnull(student_count,0)),2) as avg_student_count from courses;


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
请编写 SQL 语句，查询课程表 courses 中不重复的开课教师编号 teacher_id
*/
select distinct teacher_id from courses;
-- 编写一个 SQL 语句，获取球员 (players) 表中第二高的身高 (height)
select height as second_height from players
group by height  order by height desc limit 1,1 ;

select ifnull(height,null) as second_height from (select distinct height  from players
 order by height desc limit 1,1 ) a;
select ifnull(( 
                select distinct height 
                from players 
                order by height desc 
                limit 1, 1 
        ), null) as second_height;
 
desc players;
select *from  players order by height;
drop table if exists players ;

create table `players`(
    `id` int unsigned auto_increment,
    `height` int not null,
    primary key ( `id` )
);
insert into players values(1,198);
insert into players values(2,226);
insert into players values(3,200);
insert into players values(4,226);
-- 
drop table if exists contacts;

create table `contacts`(
    `id` int unsigned auto_increment,
    `name` varchar(50) not null,
    primary key ( `id` )
);

insert into contacts values(1,'Song Jiang');
insert into contacts values(2,'Lu Junyi');
insert into contacts values(3,'Wu Yong');
insert into contacts values(4,'Song Jiang');
insert into contacts values(5,'Wu Yong');
insert into contacts values(6,'Lin Chong');

select *from contacts;
-- 编写一个 SQL 语句，来删除 contacts 表中所有重复的姓名，重复的姓名里只保留 id 最小的那个

select name,max(id) as number from contacts group by name;

select name,max(id) as maxid,count(name) as count
  from contacts group by name;

--
select name,max(id) as maxid,count(name) as count
  from contacts group by name having count>=2;
-- 
select max(id)
  from contacts group by name having count(name)>=2;
  
-- 
delete from contacts where 1=1
and  id not in (select a.minid from( select name, min(id) 
   as minid from contacts group by name) a);
   
-- second_height ;
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
   
drop table if exists `records`;
--  记录表
create table `records`  (
    `now_time` datetime(3) not null
);
select *from records;
-- '2021-08-07 19:16:49.000'
 truncate records;
 
insert into records values(now(3));
-- 
drop table if exists boxes;
create table `boxes`(
    `id` int  auto_increment,
    `is_empty` int not null,
    primary key ( `id` )
);
/*
某处摆放着一些标有 id 的箱子，其中部分箱子是装有东西的，部分箱子是空闲的。
请编写 SQL 语句，找到空且连续的箱子，并将它们按照 id 递增排序后返回
箱子状态 (0 表示箱子占用，1 表示箱子空闲可用)
*/
--
select distinct a.id
from boxes a,boxes b
where (a.is_empty = 1 and b.is_empty = 1 and (a.id + 1 = b.id or b.id + 1 = a.id))
order by id;

insert into boxes values(1,1);
insert into boxes values(2,0);
insert into boxes values(3,1);
insert into boxes values(4,1);
insert into boxes values(5,1);
insert into boxes values(5,0);
truncate boxes;
-- 插入数据
-- command+b 格式化代码
select a.id,a.is_empty,b.id as  b_id , b.is_empty ,abs(b.is_empty-a.is_empty) as flag
 from boxes a 
inner join boxes b on a.id+1=b.id
where 1=1 and abs(b.is_empty-a.is_empty)=0;
-- 查询出为空的ID和状态
select id,is_empty from boxes a where 1=1
and a.is_empty=1;

-- 查询 rankings 表和 categories 表中所有项目对应的项目名称 (name)、该项目平均分数 (average_score)
select c.name ,round(avg(r.score),2 )as average_score from categories c inner join rankings r
on r.category_id =c.id
group by c.name;
```