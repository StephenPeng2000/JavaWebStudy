create database day08_homework;
use day08_homework;
# test1
# 完成学员和老师表设计
create table student
(
    id   int primary key auto_increment,
    name varchar(20),
    city varchar(10),
    age  int
);


create table teacher
(
    id   int primary key auto_increment,
    name varchar(20)
);

create table course
(
    id         int primary key auto_increment,
    name       varchar(20),
    teacher_id int,
    foreign key (teacher_id) references teacher (id)
);


create table student_course
(
    student_id int,
    course_id  int,
    score      int,
    foreign key (student_id) references student (id),
    foreign key (course_id) references course (id)
);

# 录入相关数据
insert into teacher
values (null, '关羽');
insert into teacher
values (null, '张飞');
insert into teacher
values (null, '赵云');

insert into student
values (null, '小王', '北京', 20);
insert into student
values (null, '小李', '上海', 18);
insert into student
values (null, '小周', '北京', 22);
insert into student
values (null, '小刘', '北京', 21);
insert into student
values (null, '小张', '上海', 22);
insert into student
values (null, '小赵', '北京', 17);
insert into student
values (null, '小蒋', '上海', 23);
insert into student
values (null, '小韩', '北京', 25);
insert into student
values (null, '小魏', '上海', 25);
insert into student
values (null, '小明', '北京', 20);

insert into course
values (null, '语文', 1);
insert into course
values (null, '数学', 1);
insert into course
values (null, '生物', 2);
insert into course
values (null, '化学', 2);
insert into course
values (null, '物理', 2);
insert into course
values (null, '英语', 3);

insert into student_course
values (1, 1, 80);
insert into student_course
values (1, 2, 90);
insert into student_course
values (1, 3, 85);
insert into student_course
values (1, 4, 78);
insert into student_course
values (2, 2, 53);
insert into student_course
values (2, 3, 77);
insert into student_course
values (2, 5, 80);
insert into student_course
values (3, 1, 71);
insert into student_course
values (3, 2, 70);
insert into student_course
values (3, 4, 80);
insert into student_course
values (3, 5, 65);
insert into student_course
values (3, 6, 75);
insert into student_course
values (4, 2, 90);
insert into student_course
values (4, 3, 80);
insert into student_course
values (4, 4, 70);
insert into student_course
values (4, 6, 95);
insert into student_course
values (5, 1, 60);
insert into student_course
values (5, 2, 70);
insert into student_course
values (5, 5, 80);
insert into student_course
values (5, 6, 69);
insert into student_course
values (6, 1, 76);
insert into student_course
values (6, 2, 88);
insert into student_course
values (6, 3, 87);
insert into student_course
values (7, 4, 80);
insert into student_course
values (8, 2, 71);
insert into student_course
values (8, 3, 58);
insert into student_course
values (8, 5, 68);
insert into student_course
values (9, 2, 88);
insert into student_course
values (10, 1, 77);
insert into student_course
values (10, 2, 76);
insert into student_course
values (10, 3, 80);
insert into student_course
values (10, 4, 85);
insert into student_course
values (10, 5, 83);

-- 1、查询平均成绩大于70分的同学的学号和平均成绩
# select student_id, avg(score) as avg from student_course group by student_id;
select s.id, t.avg
from student as s
         inner join (select student_id, avg(score) as avg from student_course group by student_id) as t
                    on s.id = t.student_id
where t.avg > 70;

-- 2、查询所有同学的学号、姓名、选课数、总成绩
select t.学号, s.name as '姓名', t.选课数, t.总成绩
from student as s
         inner join (select sc.student_id as '学号', count(*) as '选课数', sum(score) as '总成绩'
                     from student_course as sc
                     group by student_id) as t
                    on s.id = t.学号;

-- 3、查询学过赵云老师所教课的同学的学号、姓名
select distinct s.id as '学号', s.name as '姓名'
from student as s
         inner join student_course as sc
         inner join course as c
         inner join teacher as t
                    on c.teacher_id = t.id and s.id = sc.student_id and sc.course_id = c.id
where t.name = '赵云';

-- 4、查询没学过关羽老师课的同学的学号、姓名
SELECT s.name
FROM student as s
         LEFT JOIN (select s.id as '学号', s.name as '姓名'
                    from student as s
                             inner join student_course as sc
                             inner join course as c
                             inner join teacher as t
                                        on c.teacher_id = t.id and s.id = sc.student_id and sc.course_id = c.id
                    where t.name = '关羽') as temp ON s.name = temp.姓名
WHERE temp.姓名 IS NULL;

-- 5、查询没有学三门课以上的同学的学号、姓名
select s.id, s.name
from student as s
         inner join (select sc.student_id, count(*) as number
                     from student_course as sc
                     group by sc.student_id
                     having number > 3) as t on s.id = t.student_id;

-- 6、查询各科成绩最高和最低的分
select sc.course_id, max(score), min(score)
from student_course as sc
group by course_id;

# test2
create table dept
(
    deptno    int primary key auto_increment,
    dname     varchar(14),
    dlocation varchar(13)
);

create table emp
(
    empno    int primary key auto_increment,
    ename    varchar(10),
    JOB      varchar(9),
    MGR      int,
    HIREDATE date,
    SAL      int,
    COMM     int,
    DEPTNO   int not null,
    foreign key (DEPTNO) references dept (deptno)
);
-- 录入相关数据
INSERT INTO dept
VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO dept
VALUES (20, 'RESEARCH', 'DALLAS');
INSERT INTO dept
VALUES (30, 'SALES', 'CHICAGO');
INSERT INTO dept
VALUES (40, 'OPERATIONS', 'BOSTON');
INSERT INTO emp
VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800, NULL, 20);
INSERT INTO emp
VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600, 300, 30);
INSERT INTO emp
VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250, 500, 30);
INSERT INTO emp
VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975, NULL, 20);
INSERT INTO emp
VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250, 1400, 30);
INSERT INTO emp
VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850, NULL, 30);
INSERT INTO emp
VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450, NULL, 10);
INSERT INTO emp
VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1987-06-13', 3000, NULL, 20);
INSERT INTO emp
VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000, NULL, 10);
INSERT INTO emp
VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500, 0, 30);
INSERT INTO emp
VALUES (7876, 'ADAMS', 'CLERK', 7788, '1987-06-13', 1100, NULL, 20);
INSERT INTO emp
VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950, NULL, 30);
INSERT INTO emp
VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000, NULL, 20);
INSERT INTO emp
VALUES (7934, 'MILLER', 'CLERK', 7782, '1983-01-23', 1300, NULL, 10);

# 1．列出至少有4个员工的所有部门。
select emp.DEPTNO, count(ename)
from emp
group by emp.DEPTNO
having count(ename) >= 4;

# 2．列出薪金比“SMITH”多的所有员工。
select emp.SAL
from emp
where ename = 'SMITH';
select emp.ename
from emp
         inner join (select emp.SAL from emp where ename = 'SMITH') as s
where emp.SAL > s.SAL;

# 3．列出所有员工的姓名及其直接上级的姓名。
select t.e as '员工姓名', emp.ename as '上级'
from emp
         inner join (select emp.ename as e, emp.MGR from emp) as t
where t.MGR = emp.empno;

# 4．列出受雇日期早于其直接上级的所有员工。
select distinct emp.ename, emp.HIREDATE
from emp
         inner join (select emp.ename, emp.HIREDATE, emp.empno
                     from emp
                              inner join (select emp.MGR from emp) as m
                     where emp.empno = m.MGR) as m
where m.HIREDATE > emp.HIREDATE;

# 5．列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。
select dept.dname, e.ename from dept left join emp e on dept.deptno = e.DEPTNO;

# 6．列出所有“CLERK”（办事员）的姓名及其部门名称。
select e.ename, d.dname from emp e inner join dept d on e.DEPTNO = d.deptno where e.JOB = 'CLERK';

# 7．列出最低薪金大于1500的各种工作。
select min(SAL), emp.JOB from emp group by JOB having min(SAL) > 1500;

