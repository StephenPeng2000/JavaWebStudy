create database day14_homework;
use day14_homework;
-- 部门表
create table dept(
	deptno int primary key auto_increment, -- 部门编号
	dname varchar(14) ,	  -- 部门名字
	loc varchar(13)   -- 地址
) ;
-- 员工表
create table emp(
	empno int primary key auto_increment,-- 员工编号
	ename varchar(10), -- 员工姓名										-
	job varchar(9),	-- 岗位
	mgr int,	 -- 直接领导编号
	hiredate date, -- 雇佣日期，入职日期
	sal int, -- 薪水
	comm int,  -- 提成
	deptno int not null, -- 部门编号
	foreign key (deptno) references dept(deptno)
);
insert into dept values(10,'财务部','北京');
insert into dept values(20,'研发部','上海');
insert into dept values(30,'销售部','广州');
insert into dept values(40,'行政部','深圳');
insert into emp values(7369,'刘一','职员',7902,'1980-12-17',800,null,20);
insert into emp values(7499,'陈二','推销员',7698,'1981-02-20',1600,300,30);
insert into emp values(7521,'张三','推销员',7698,'1981-02-22',1250,500,30);
insert into emp values(7566,'李四','经理',7839,'1981-04-02',2975,null,20);
insert into emp values(7654,'王五','推销员',7698,'1981-09-28',1250,1400,30);
insert into emp values(7698,'赵六','经理',7839,'1981-05-01',2850,null,30);
insert into emp values(7782,'孙七','经理',7839,'1981-06-09',2450,null,10);
insert into emp values(7788,'周八','分析师',7566,'1987-06-13',3000,null,20);
insert into emp values(7839,'吴九','总裁',null,'1981-11-17',5000,null,10);
insert into emp values(7844,'郑十','推销员',7698,'1981-09-08',1500,0,30);
insert into emp values(7876,'郭靖','职员',7788,'1987-06-13',1100,null,20);
insert into emp values(7900,'令狐冲','职员',7698,'1981-12-03',950,null,30);
insert into emp values(7902,'张无忌','分析师',7566,'1981-12-03',3000,null,20);
insert into emp values(7934,'杨过','职员',7782,'1983-01-23',1300,null,10);

-- 21.查询出emp表中部门编号为20，薪水在2000以上（不包括2000）的所有员工，显示他们的员工号，姓名以及薪水，以如下列名显示：员工编号 员工名字 薪水
select e.empno as '员工编号', e.ename as '员工名字', e.sal '薪水' from emp as e where e.deptno = 20 and e.sal > 2000;

-- 22.查询出emp表中所有的工作种类（无重复）
select distinct e.job from emp as e;

-- 23.查询出所有奖金（comm）字段不为空的人员的所有信息。
select * from emp as e where comm is not null;

-- 24.查询出薪水在800到2500之间（闭区间）所有员工的信息。（注：使用两种方式实现and以及between and）
select * from emp as e where e.sal between 800 and 2500;
select * from emp as e where e.sal >= 800 and e.sal <= 2500;

-- 25.查询出员工号为7521，7900，7782的所有员工的信息。（注：使用两种方式实现，or以及in）
select * from emp as e where e.empno = 7521 or e.empno = 7900 or e.empno = 7782;
select * from emp as e where e.empno in (7521,7900,7782);

-- 26.查询出名字中有“张”字符，并且薪水在1000以上（不包括1000）的所有员工信息。
select * from emp as e where e.ename like '%张%' and e.sal > 1000;

-- 27.查询出名字第三个汉字是“忌”的所有员工信息。
select * from emp as e where e.ename like '__忌';

-- 28.将所有员工按薪水升序排序，薪水相同的按照入职时间降序排序。
select * from emp order by sal asc, hiredate desc;

-- 29.将所有员工按照名字首字母升序排序，首字母相同的按照薪水降序排序。 order by convert(name using gbk) asc;


-- 30.查询出最早工作的那个人的名字、入职时间和薪水。
select min(e.hiredate) as min from emp as e;
select e.ename, e.hiredate, e.sal from emp as e inner join (select min(e.hiredate) as min from emp as e) as t where e.hiredate = t.min;