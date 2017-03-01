CREATE TABLE task_logs (
	task_id int,
	log_time timestamp,
	latitude double,
	longitude double,
	speed double,
	altitude double,
	employee_id int,
	car_id int,
	PRIMARY KEY (task_id, log_time)
);