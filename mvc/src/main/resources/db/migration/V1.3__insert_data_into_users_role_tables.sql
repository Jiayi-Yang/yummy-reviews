insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/depts,/departments,/employees,/ems,/acnts,/accounts', 'Y', 'Y', 'Y', 'N'),
('user', '/employees,/ems,/acnts,/accounts', 'Y', 'N', 'N', 'N')
;
commit;

insert into users (email,password,username,created_on) values
('dwang@training.ascendingdc.com', '25f9e794323b453885f5181f1b624d0b', 'dwang' , CURRENT_TIMESTAMP ),
('rhang@training.ascendingdc.com', '25f9e794323b453885f5181f1b624d0b', 'rhang',  CURRENT_TIMESTAMP),
('xyhuang@training.ascendingdc.com', '25f9e794323b453885f5181f1b624d0b', 'xyhuang', CURRENT_TIMESTAMP )
;
commit;

insert into users_role values
(1, 1),
(2, 2),
(3, 3),
(1, 2),
(1, 3)
;
commit;