create database day15_homework;
use day15_homework;
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

-- 31.显示所有员工的名字、薪水、奖金，如果没有奖金，暂时显示100.
select e.ename, e.sal, IFNULL(e.comm, 100)
from emp as e;
-- 32.显示出薪水最高人的职位。
select e.job
from emp as e
         inner join (select max(sal) as max from emp) as t
where e.sal = t.max;

-- 33.查出emp表中所有部门的最高薪水和最低薪水，部门编号为10的部门不显示。
select deptno, max(sal) as max, min(sal) as min
from emp
group by deptno;
select d.deptno, d.dname, t.max, t.min
from dept as d
         inner join (select deptno, max(sal) as max, min(sal) as min from emp group by deptno) as t
on d.deptno = t.deptno where d.deptno<>10;
-- 34.删除10号部门薪水最高的员工。 看答案
select empno
from emp
where deptno = 10
  and sal >= all (select sal from emp where deptno = 10);

delete
from emp
where empno = (select empno
               from (select empno
                     from emp
                     where deptno = 10
                       and sal >= all (select sal from emp where deptno = 10)) as temp);

-- 35.将薪水最高的员工的薪水降30%。
select empno
from emp
where sal >= all (select sal from emp);
update emp
set sal=sal * (1 - 0.3)
where empno in (select temp.empno
                from (select empno
                      from emp
                      where sal >= all (select sal from emp)) temp);

-- 36.查询员工姓名，工资和 工资级别(工资>=3000 为3级，工资>2000 为2级，工资<=2000 为1级)
-- 语法：case when ... then ... when ... then ... else ... end
select ename,
       case
           when sal >= 3000 then '3级'
           when sal > 2000 then '2级'
           when sal <= 2000 then '1级' end
from emp;
