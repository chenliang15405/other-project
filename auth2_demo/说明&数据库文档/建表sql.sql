create table oauth_access_token (
id NUMBER not null primary key,
access_token VARCHAR2(100) not null,
refresh_token VARCHAR2(100) not null,
app_id NUMBER,
expire_in DATE,
create_date DATE,
update_date DATE
);

create sequence seq_oauth_access_token minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1;

create table oauth_app (
app_id NUMBER not null  primary key,
app_secret VARCHAR2(70) not null,
app_name VARCHAR2(70) not null,
app_redirect_url VARCHAR2(200) not null,
create_date DATE
);

create sequence seq_oauth_app minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1;

create table oauth_code (
id NUMBER not null primary key,
code VARCHAR2(50) not null,
expire_in date not null,
create_date DATE
);

create sequence seq_oauth_code minvalue 1 maxvalue 999999999999999999 start with 1 increment by 1;
