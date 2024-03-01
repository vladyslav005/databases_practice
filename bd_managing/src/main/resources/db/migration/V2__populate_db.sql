insert into worker (NAME, BIRTHDAY, level, SALARY) values('Ivan',   '2005-05-18', 'Trainee', 200);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('Vlad',   '1998-06-27', 'Senior', 6000);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('Alina',  '2001-02-07', 'Junior', 1200);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('Taras',  '2000-04-08', 'Junior', 1200);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('Maksym', '2003-08-14', 'Middle', 4000);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('John',   '1999-01-30', 'Senior', 7500);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('Anton',  '2004-09-11', 'Trainee', 400);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('Pavlo',  '2002-12-20', 'Middle', 2500);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('Valeria','2003-09-29', 'Junior', 1500);
insert into worker (NAME, BIRTHDAY, level, SALARY) values('Oksana','2006-01-29', 'Trainee', 100);


insert into client (NAME) values ('Oleksandr');
insert into client (NAME) values ('Artem');
insert into client (NAME) values ('Larysa');
insert into client (NAME) values ('Mykola');
insert into client (NAME) values ('Roman');


insert into project (name, CLIENT_ID, start_date, finish_date) values ('E-shop', 1,'2018-01-01', '2019-01-01');
insert into project (name, CLIENT_ID, start_date, finish_date) values ('Car service', 1,'2018-01-01', '2019-10-11');
insert into project (name, CLIENT_ID, start_date, finish_date) values ('FaceHook offline social notwork', 1,'2020-01-01', '2020-12-01');

insert into project (name, CLIENT_ID, start_date, finish_date) values ('Education system', 2,'2021-03-05', '2023-04-01');

insert into project (name, CLIENT_ID, start_date, finish_date) values ('delivery service', 3,'2019-05-08', '2021-01-01');
insert into project (name, CLIENT_ID, start_date, finish_date) values ('Cafe page', 3,'2018-01-01', '2019-01-01');

insert into project (name, CLIENT_ID, start_date, finish_date) values ('Security system', 4,'2023-01-01', '2023-08-09');
insert into project (name, CLIENT_ID, start_date, finish_date) values ('Fitnes tracker', 4,'2020-01-01', '2021-06-05');

insert into project (name, CLIENT_ID, start_date, finish_date) values ('Storage register system', 5,'2019-01-01', '2020-05-13');
insert into project (name, CLIENT_ID, start_date, finish_date) values ('Secret project', 5,'2023-01-01', '2023-08-11');


insert into project_worker (project_id, worker_id) values (1, 1);

insert into project_worker (project_id, worker_id) values (2, 1);
insert into project_worker (project_id, worker_id) values (2, 2);

insert into project_worker (project_id, worker_id) values (3, 3);
insert into project_worker (project_id, worker_id) values (3, 4);
insert into project_worker (project_id, worker_id) values (3, 5);

insert into project_worker (project_id, worker_id) values (4, 5);
insert into project_worker (project_id, worker_id) values (4, 6);
insert into project_worker (project_id, worker_id) values (4, 7);
insert into project_worker (project_id, worker_id) values (4, 8);

insert into project_worker (project_id, worker_id) values (5, 9);
insert into project_worker (project_id, worker_id) values (4, 10);

insert into project_worker (project_id, worker_id) values (6, 5);

insert into project_worker (project_id, worker_id) values (7, 6);
insert into project_worker (project_id, worker_id) values (7, 7);
insert into project_worker (project_id, worker_id) values (7, 8);

insert into project_worker (project_id, worker_id) values (8, 3);
insert into project_worker (project_id, worker_id) values (8, 4);

insert into project_worker (project_id, worker_id) values (9, 2);
insert into project_worker (project_id, worker_id) values (9, 5);
insert into project_worker (project_id, worker_id) values (9, 8);

insert into project_worker (project_id, worker_id) values (10, 1);
insert into project_worker (project_id, worker_id) values (10, 3);
insert into project_worker (project_id, worker_id) values (10, 5);
insert into project_worker (project_id, worker_id) values (10, 7);
insert into project_worker (project_id, worker_id) values (10, 9);



