create database day12_homework;
use day12_homework;
-- 部门表
create table dept
(
    deptno int primary key auto_increment, -- 部门编号
    dname  varchar(14),                    -- 部门名字
    loc    varchar(13)                     -- 地址
);
-- 员工表
create table emp
(
    empno    int primary key auto_increment,-- 员工编号
    ename    varchar(10),                   -- 员工姓名										-
    job      varchar(9),                    -- 岗位
    mgr      int,                           -- 直接领导编号
    hiredate date,                          -- 雇佣日期，入职日期
    sal      int,                           -- 薪水
    comm     int,                           -- 提成
    deptno   int not null,                  -- 部门编号
    foreign key (deptno) references dept (deptno)
);
insert into dept
values (10, '财务部', '北京');
insert into dept
values (20, '研发部', '上海');
insert into dept
values (30, '销售部', '广州');
insert into dept
values (40, '行政部', '深圳');
insert into emp
values (7369, '刘一', '职员', 7902, '1980-12-17', 800, null, 20);
insert into emp
values (7499, '陈二', '推销员', 7698, '1981-02-20', 1600, 300, 30);
insert into emp
values (7521, '张三', '推销员', 7698, '1981-02-22', 1250, 500, 30);
insert into emp
values (7566, '李四', '经理', 7839, '1981-04-02', 2975, null, 20);
insert into emp
values (7654, '王五', '推销员', 7698, '1981-09-28', 1250, 1400, 30);
insert into emp
values (7698, '赵六', '经理', 7839, '1981-05-01', 2850, null, 30);
insert into emp
values (7782, '孙七', '经理', 7839, '1981-06-09', 2450, null, 10);
insert into emp
values (7788, '周八', '分析师', 7566, '1987-06-13', 3000, null, 20);
insert into emp
values (7839, '吴九', '总裁', null, '1981-11-17', 5000, null, 10);
insert into emp
values (7844, '郑十', '推销员', 7698, '1981-09-08', 1500, 0, 30);
insert into emp
values (7876, '郭靖', '职员', 7788, '1987-06-13', 1100, null, 20);
insert into emp
values (7900, '令狐冲', '职员', 7698, '1981-12-03', 950, null, 30);
insert into emp
values (7902, '张无忌', '分析师', 7566, '1981-12-03', 3000, null, 20);
insert into emp
values (7934, '杨过', '职员', 7782, '1983-01-23', 1300, null, 10);

-- 列出所有job为“职员”的姓名及其部门名称。
select e.ename, d.dname
from emp as e
         inner join dept d on e.deptno = d.deptno
where e.job = '职员';
-- 列出最低薪金大于1500的各种工作。
select distinct e.job
from emp as e
where e.sal > 1500;

-- 列出在部门 "销售部" 工作的员工的姓名，假定不知道销售部的部门编号。
select e.ename
from emp as e
         inner join dept d on e.deptno = d.deptno
where d.dname = '销售部';

-- 列出薪金高于公司平均薪金的所有员工。
select e.ename
from emp as e
         inner join (select avg(sal) as avg from emp) as t
where e.sal > t.avg;

-- 列出与"周八"从事相同工作的所有员工。
select e.ename
from emp as e
         inner join (select e.job from emp as e where e.ename = '周八') as t
where e.job = t.job;

-- 列出薪金等于部门30中员工的薪金的所有员工的姓名和薪金。
select e.ename, e.sal
from emp as e
         inner join (select e.sal from emp as e where e.deptno = 30) as t
where e.sal in (t.sal);

-- 列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金。
select e.ename, e.sal
from emp as e
         inner join (select max(e.sal) as max from emp as e where e.deptno = 30) as t
where e.sal > t.max;

-- 列出在每个部门工作的员工数量、平均工资。
select count(*), avg(e.sal), e.deptno
from emp as e
group by deptno;

-- 列出所有员工的姓名、部门名称和工资。
select e.ename, d.dname, e.sal
from emp as e
         inner join dept d on e.deptno = d.deptno;

-- 列出所有部门的详细信息和部门人数。
select *
from dept as d
         inner join (select count(*), e.deptno from emp as e group by deptno) as t
where d.deptno = t.deptno;
