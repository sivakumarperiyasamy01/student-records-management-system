create database student_db;

use student_db;

create table schools(school_id int primary key, school_name varchar(100) not null unique); --- wrong 

correct :  




select * from schools;

create table students(
register_Number varchar(20) primary key,
student_Name varchar(20) not null,
date_of_birth date not null,
year_of_passing int not null,
school_id int not null,

constraint fk_school
foreign key(school_id)
references schools (school_id)
on delete restrict);

select*from students;


create table subject(subject_id int primary key auto_increment  , subject_Name varchar(100));


select*from subject;

insert into subject (subject_Name) values
('Tamil'),
('English'),('Physics'),('Chemistry'),('computer Science'),('Mathematics');

create table students_marks (
register_Number varchar(20),
subject_id int ,
marks int check(marks between 0 and 100),

constraint studnet_info
foreign key (register_Number) references students(register_Number) on delete cascade,


constraint subject_info
foreign key (subject_id) references subject(subject_id) on delete restrict);

select*from students_marks;




