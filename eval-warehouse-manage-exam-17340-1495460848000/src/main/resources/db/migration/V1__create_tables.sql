CREATE TABLE users (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role VARCHAR(255),
  password varchar(255)
);

CREATE TABLE warehouses (
  id int(5) NOT NULL auto_increment,
  name varchar(20) NOT NULL,
  containers varchar(200),
  PRIMARY KEY (id)
);

CREATE TABLE containers (
  container_id int(5) NOT NULL auto_increment,
  id int(5) NOT NULL,
  PRIMARY KEY (container_id)
);

insert into users (id, name, email, role, password) values ("001", "admin", "admin@example.com", "BACKGROUND_JOB", "$2a$04$DbgJbGA4dkQSzAvXvJcGBOv5kHuMBzrWfne3x3Cx4JQv4IJcxtBIW");
