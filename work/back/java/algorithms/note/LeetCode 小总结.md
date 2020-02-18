# LeetCode 小总结

## 1、Mysql

存储过程

```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
			--在此定义数据
			-- 变量名在类型前面
      declare p int;
      --使用set 做数据运算
      set p = N - 1;
  RETURN (
      # Write your MySQL query statement below. 
      select  
      ifnull(
      (
          select distinct Salary from Employee order by Salary desc limit p,1
      ) ,null) as SecondHighestSalary

  );
END
```

distinct ，

ifnull（xxx, null）

排名：(自连)

```sql
select a.Score ,
(select count(distinct b.Score) from Scores b where b.score >= a.Score) as Rank 
from Scores a order by a.Score desc
```

having 用法（与where相似，此语句通常与group by一起用）

```sql
select Email
from Person
group by Email
having count(Email) > 1;
```

in可以比较多行多列

```sql
SELECT
    Department.name AS 'Department',
    Employee.name AS 'Employee',
    Salary
FROM
    Employee
        JOIN
    Department ON Employee.DepartmentId = Department.Id
WHERE
    (Employee.DepartmentId , Salary) IN
    (   SELECT
            DepartmentId, MAX(Salary)
        FROM
            Employee
        GROUP BY DepartmentId
	) 
```

部门前3工资（子连）

```sql
SELECT
	Department.NAME AS Department,
	e1.NAME AS Employee,
	e1.Salary AS Salary 
FROM
	Employee AS e1,Department 
WHERE
	e1.DepartmentId = Department.Id 
	AND 3 > (SELECT  count( DISTINCT e2.Salary ) 
			 FROM	Employee AS e2 
			 WHERE	e1.Salary < e2.Salary 	AND e1.DepartmentId = e2.DepartmentId 	) 
ORDER BY Department.NAME,Salary DESC; 


```

