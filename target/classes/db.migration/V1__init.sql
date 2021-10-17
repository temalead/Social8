create table pupil(
    id int8 primary key,
    first_name varchar(10),
    last_name varchar(25),
    password varchar(255),
    email varchar(255),

);
create table study_class(
    id int 8 primary key,
    number_of_class number
);
create table pupil_role(
    pupil_id int8 not null,
    role varchar(8)
);
alter table pupil_role
    add constraint pupil_role foreign key(pupil_id) references pupil;
alter table pupil
    add constraint study_class foreign(number_of_class) references study_class;