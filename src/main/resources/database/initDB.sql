DROP TABLE IF EXISTS employee;
CREATE TABLE employee
(
    id         BIGINT AUTO_INCREMENT primary key NOT NULL,
    first_name VARCHAR(50)                       NOT NULL,
    last_name  VARCHAR(100)                      NOT NULL,
    email      VARCHAR(50)                       NOT NULL,
    job_title  VARCHAR(50)                       NOT NULL,
    phone      VARCHAR(50)                       NOT NULL,
    image_url  varchar(1000)
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

DROP TABLE IF EXISTS project;
CREATE TABLE project
(
    id   BIGINT AUTO_INCREMENT primary key NOT NULL,
    name VARCHAR(50)                       NOT NULL,
    address_id BIGINT,
    employee_id BIGINT,
    client_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE RESTRICT,
    FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE RESTRICT,
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE RESTRICT
);