show databases;
create database if not exists hd;
use hd;

select * from mysql.user;

create user hd_fid identified by 'hd_fid_pwd123';

show grants for hd_fid;

GRANT CREATE,SELECT, INSERT, UPDATE,DELETE,EXECUTE,ALTER,DROP,REFERENCES
ON *.*
TO hd_fid;
flush privileges;