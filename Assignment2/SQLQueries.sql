1. Find all web servers by name that have an inout parameter.
ANSWER : 

SELECT s.name
FROM SERVER s, parameter p, method m
WHERE s.id=m.partOf
AND m.id=p.usedby
AND p.alternative="inout";

select ws.name
  from Server ws, ServerType t
 where ws.type = t.id
   and t.name = 'web'
   and exists(
         select *
           from Method m, Parameter p
          where m.partOf = ws.id
            and p.usedBy = m.id
            and p.type = 'inout' );



2. Find all unspecified database servers with a method that has 3 or more parameters. Show the server name and the methods that have 3 or more parameters.

ANSWER : Assuming here that unspecified server can be either blank or NULL or 'unspecified'

SELECT s.name,m.name
FROM SERVER s, method m, parameter p
WHERE
s.specification='' OR s.specification IS NULL OR s.specification='unspecified'
AND m.partof=s.id
AND p.usedby=m.id
GROUP BY s.name
HAVING COUNT(s.name)>2;


select s.name serverName, m.id methodName
  from ServerType t, Server s, Method m
 where m.partOf = s.id
   and s.type = t.id
   and t.name = 'database'
   and s.specification is null
   and exists (
         select count(*)
           from Parameter p
          where p.usedBy = m.id
         having count(*) >= 3 );


3. List all servers that use the "content management" server type.
ANSWER :

SELECT s.name,s.id
FROM SERVER s, servertype st
WHERE s.type=st.id
AND st.name="content management";

4. List all methods that can return a document. For each method show its name and the name of its server.
ANSWER : 


SELECT m.name AS 'method name', s.name AS 'server name'
FROM method m,SERVER s, parameter p
WHERE m.partOf=s.id
AND m.id=p.usedby
AND p.type='document'

select m.name, s.name
  from Method m inner join Server s on (m.partOf = s.id)
 where exists ( 
        select *
           from Parameter p
          where p.usedBy = m.id
            and p.alternative in ('out', 'inout')
            and p.type = 'document' );


5. Show how to add a new web server with a single method that has no parameters. 

ANSWER : 

START TRANSACTION;
INSERT INTO SERVER (NAME, specification, TYPE)
SELECT 's1', st.name,st.id
FROM ServerType st WHERE st.name='Web';

INSERT INTO method (NAME,partof) 
SELECT 'm1',s.id
FROM SERVER s WHERE s.name='s1';
commit;

6. Find all types used by input methods of a server specified by its name as well as all servers that the specified server uses.
ANSWER :

SELECT
p.type
FROM
SERVER s, method m, parameter p,serverusage su
WHERE s.id=m.partof
AND m.id=p.usedby
AND s.id=su.usedByServer 
AND s.name='s1'
AND p.alternative IN ('in', 'inout')
GROUP BY p.type;

select distinct p.type
  from Parameter p
 where p.alternative in ('in', 'inout')
   and exists (
        select *
          from Server s, Method m
         where s.name = '<specified name>'
           and m.partOf = s.id
           and p.usedBy = m.id );
		   
select u.usesServer
  from Server s, ServerUsage u
 where u.usedByServer = s.id
   and s.name = '<specified name>';



7. Show how to add a new parameter to an existing method.
ANSWER : Assuming here that the method is the method m1 that we added in answer 5.

START TRANSACTION;
SET @methodID= (SELECT m.id
FROM method m,SERVER s,servertype st 
WHERE m.partOf=s.id 
AND s.type=st.id 
AND st.name="web"
 AND s.name='s1');

SELECT @methodID;

INSERT INTO parameter (NAME,usedby,TYPE,alternative) 
VALUES ('paramnew',@methodID,'String','in');

insert into Parameter(name, usedBy, type, alternative)
  select '<parameter name>', m.id, '<parameter type>', '<parameter alternative>'
    from Method m, Server s
   where s.name = '<server name>'
     and m.name = '<method name>'
     and m.partOf = s.id;


commit;

8. Show how to delete a method from a server.

ANSWER :

START TRANSACTION;

DELETE FROM method
WHERE NAME='m1';

delete from Method
  where exists (
    select *
      from Server s
     where name = '<method name>'
       and s.name = '<server name>'
       and m.partOf = s.id


-- rollback;

commit;

9. Provide a view for all "content management" servers, including all of their methods and parameters and all properties of the methods and parameters.

ANSWER :

CREATE VIEW ContentManagement AS
SELECT p.id AS pid,p.name AS pname, p.usedby, p.type, p.alternative,
m.id AS MID,m.name AS mname,m.partof FROM method m, parameter p,servertype st, SERVER s
WHERE s.type=st.id
AND st.name='content management' 
AND s.id = m.partof
AND m.id= p.usedby;

