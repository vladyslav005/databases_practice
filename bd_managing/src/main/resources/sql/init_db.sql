CREATE SEQUENCE public.worker_seq start 1;
CREATE SEQUENCE public.client_seq start 1;
CREATE SEQUENCE public.project_seq start 1;

create table public.worker (
	ID int primary key NOT NULL DEFAULT nextval('worker_seq'),
	NAME varchar(1000) NOT NULL check(length(NAME) > 2),
	BIRTHDAY date CHECK (1990 < extract(year from BIRTHDAY)),
	--BIRTHDAY date CHECK (BIRTHDAY > TO_DATE('1990-01-01', 'yyyy-mm-dd')),
	LEVEL varchar(16) not null check(level = 'Trainee' or level = 'Junior' or level = 'Middle' or level = 'Senior'), 
	SALARY int check(SALARY >= 100 and SALARY <= 100000)
);

create table client (
	ID int primary key NOT NULL DEFAULT nextval('client_seq'),
	NAME varchar(1000) NOT NULL check(length(NAME) > 2)
);


create table project (
	ID int primary key NOT NULL DEFAULT nextval('project_seq'),
	NAME varchar(1000) NOT NULL check(length(NAME) > 2),
	CLIENT_ID int,
	START_DATE date,
	FINISH_DATE date,
	CONSTRAINT fk_client
      FOREIGN KEY(CLIENT_ID) 
        REFERENCES client(ID)
);

create table project_worker (
	PROJECT_ID int,
	WORKER_ID int,
	
	CONSTRAINT fk_project FOREIGN KEY(PROJECT_ID) REFERENCES project(ID),
	CONSTRAINT fk_worker FOREIGN KEY(WORKER_ID) REFERENCES worker(ID)
);

