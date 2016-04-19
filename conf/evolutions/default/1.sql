# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apiarys (
  ap_id                     bigint auto_increment not null,
  min_temp                  varchar(255),
  max_temp                  varchar(255),
  min_hum                   varchar(255),
  max_hum                   varchar(255),
  gps                       varchar(255),
  an_mas                    integer,
  an_temp                   integer,
  an_hum                    integer,
  user_id                   bigint,
  constraint pk_apiarys primary key (ap_id))
;

create table hives (
  hive_id                   bigint auto_increment not null,
  an_mas                    integer,
  an_temp                   integer,
  an_hum                    integer,
  coordinates               varchar(255),
  state                     varchar(255),
  phone                     varchar(255),
  ap_id                     bigint,
  constraint pk_hives primary key (hive_id))
;

create table registr (
  reg_id                    bigint auto_increment not null,
  login                     varchar(255),
  pass                      varchar(255),
  user_id                   bigint,
  constraint uq_registr_user_id unique (user_id),
  constraint pk_registr primary key (reg_id))
;

create table user (
  user_id                   bigint auto_increment not null,
  name                      varchar(255),
  surname                   varchar(255),
  patronymic                varchar(255),
  phone                     varchar(255),
  email                     varchar(255),
  constraint pk_user primary key (user_id))
;

alter table apiarys add constraint fk_apiarys_user_1 foreign key (user_id) references user (user_id) on delete restrict on update restrict;
create index ix_apiarys_user_1 on apiarys (user_id);
alter table hives add constraint fk_hives_apiary_2 foreign key (ap_id) references apiarys (ap_id) on delete restrict on update restrict;
create index ix_hives_apiary_2 on hives (ap_id);
alter table registr add constraint fk_registr_user_3 foreign key (user_id) references user (user_id) on delete restrict on update restrict;
create index ix_registr_user_3 on registr (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table apiarys;

drop table hives;

drop table registr;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

