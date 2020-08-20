drop table if exists member CASCADE;
create table member(
 id bigint(11) NOT NULL AUTO_INCREMENT,
 name varchar(255) NOT NULL,
 primary key (id));