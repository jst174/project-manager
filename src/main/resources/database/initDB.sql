SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS department;
CREATE TABLE department
(
    id   BIGINT AUTO_INCREMENT primary key NOT NULL,
    name VARCHAR(50)                       NOT NULL
);

DROP TABLE IF EXISTS employee cascade;
CREATE TABLE employee
(
    id            BIGINT AUTO_INCREMENT primary key NOT NULL,
    first_name    VARCHAR(50)                       NOT NULL,
    last_name     VARCHAR(100)                      NOT NULL,
    email         VARCHAR(50)                       NOT NULL,
    job_title     VARCHAR(50)                       NOT NULL,
    phone         VARCHAR(50)                       NOT NULL,
    image_url     varchar(1000),
    department_id BIGINT,
    FOREIGN KEY (department_id) references department (id) on update cascade
);

DROP TABLE IF EXISTS address;
CREATE TABLE address
(
    id      BIGINT AUTO_INCREMENT primary key NOT NULL,
    country VARCHAR(50)                       NOT NULL,
    city    VARCHAR(50)                       NOT NULL,
    street  VARCHAR(50)                       NOT NULL
);

DROP TABLE IF EXISTS client;
CREATE TABLE client
(
    id   BIGINT AUTO_INCREMENT primary key NOT NULL,
    name VARCHAR(50)                       NOT NULL
);

DROP TABLE IF EXISTS project cascade;
CREATE TABLE project
(
    id          BIGINT AUTO_INCREMENT primary key NOT NULL,
    name        VARCHAR(50)                       NOT NULL,
    address_id  BIGINT,
    client_id   BIGINT,
    image_url   varchar(1000),
    FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE

);

DROP TABLE IF EXISTS project_employee cascade;
create table project_employee
(
    project_id  bigint references project (id) ON DELETE CASCADE,
    employee_id bigint references employee (id) ON DELETE CASCADE,
    primary key (project_id, employee_id)
);