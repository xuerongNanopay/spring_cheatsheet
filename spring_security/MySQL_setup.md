# Set up my sql using Docker

## Pull image
```
docker pull mysql:5.7
```
## Run image
```
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
```

## Create Tables
```
docker exec -it mysql mysql -u root -p123456
```
```
create database spring_security_demo;
```
```
create table users(
  username varchar(50) not null primary key,
  password varchar(500) not null,
  enabled boolean not null
);
```
```
create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username)
);
```
```
create unique index idx_auth_username on authoritie(username, authority);
```

#### Remember Me
```
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key,token varchar(64) not null, last_used timestamp not null);
```

## insert default admin
```
//password: adminAb1
insert into users(username, password, enabled)
  values('admin', '{bcrypt}$2a$10$NHN1X3wvAEhHRl9iYYalcu4jTbneanEph7Omf5y839nn/SNPFVncK', TRUE);
```
```
insert into authorities(username, authority) values('admin', 'ROLE_ADMIN');
insert into authorities(username, authority) values('admin', 'ROLE_USER');
```