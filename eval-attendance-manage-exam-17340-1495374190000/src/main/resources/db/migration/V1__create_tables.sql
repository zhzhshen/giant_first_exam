CREATE TABLE users (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role VARCHAR(255),
  password varchar(255)
);

CREATE TABLE employees (
  id int(5) NOT NULL auto_increment,
  name varchar(20) NOT NULL,
  department_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  gender varchar(10) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE attendances (
  id int(5) NOT NULL auto_increment,
  employee_id int(5) NOT NULL,
  description varchar(50) NOT NULL,
  present BOOLEAN NOT NULL,
  from_date VARCHAR(50) NOT NULL,
  to_date VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

insert into users (id, name, email, role, password) values ("001", "admin", "admin@example.com", "BACKGROUND_JOB", "$2a$04$DbgJbGA4dkQSzAvXvJcGBOv5kHuMBzrWfne3x3Cx4JQv4IJcxtBIW");
