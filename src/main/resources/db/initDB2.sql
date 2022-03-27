create table humans
(
	human_id serial
		constraint humans_pk
			primary key,
	first_name varchar(20),
	last_name varchar(25),
	gender smallint
);
