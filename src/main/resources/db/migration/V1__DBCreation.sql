create table roles (
       id  bigserial not null,
        name varchar(255),
        primary key (id)
    );
create table users (
       id  bigserial not null,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
    );
create table users_roles (
       user_id int8 not null,
        role_id int8 not null
    );
alter table users 
       add constraint email_constraint unique (email);
alter table users 
       add constraint username_constraint unique (username);
alter table users_roles 
       add constraint role_id_fk 
       foreign key (role_id) 
       references roles;
alter table users_roles 
       add constraint user_id_fk 
       foreign key (user_id) 
       references users;