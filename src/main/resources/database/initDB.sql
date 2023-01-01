DROP TABLE IF EXISTS employee;
CREATE TABLE employee
(
    id         BIGINT AUTO_INCREMENT primary key NOT NULL,
    first_name VARCHAR(50)           NOT NULL,
    last_name  VARCHAR(100)          NOT NULL,
    email      VARCHAR(50)           NOT NULL,
    job_title  VARCHAR(50)           NOT NULL,
    phone      VARCHAR(50)           NOT NULL,
    image_url  varchar(1000)
);