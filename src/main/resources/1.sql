create table t_user
(
  id       int auto_increment
    primary key,
  username varchar(10) null,
  password varchar(50) null,
  constraint t_user_id_uindex
  unique (id)
);


create table t_user_permission
(
  id         int auto_increment
    primary key,
  username   varchar(10) null,
  permission varchar(30) null,
  constraint t_user_permission_id_uindex
  unique (id)
);
INSERT INTO `test`.`t_user_permission` (`username`, `permission`) VALUES ('test', 'user:delete')


create table t_user_role
(
  id        int auto_increment
    primary key,
  username  varchar(10) null,
  role_name varchar(30) null,
  constraint t_user_role_id_uindex
  unique (id)
);
INSERT INTO `test`.`t_user_role` (`username`, `role_name`) VALUES ('test', 'admin')