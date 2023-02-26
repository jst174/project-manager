INSERT INTO department
VALUES (1,'AR');
INSERT INTO department
VALUES (2,'STR');
INSERT INTO department
VALUES (3, 'HVAC');

INSERT INTO employee
VALUES (1, 'Perceval', 'Franchi', 'pfranchi0@sitemeter.com', 'Staff Accountant III', '918-974-5150',
        'https://www.bootdey.com/img/Content/avatar/avatar1.png', 1);
INSERT INTO employee
VALUES (2, 'Munroe', 'Butson', 'mbutson1@bluehost.com', 'Staff Accountant II', '552-330-8924',
        'https://www.bootdey.com/img/Content/avatar/avatar2.png', 2);
INSERT INTO employee
VALUES (3, 'Cam', 'Espinos', 'cespinos2@pinterest.com', 'Structural Analysis Engineer', '891-835-7039',
        'https://www.bootdey.com/img/Content/avatar/avatar3.png', 3);
INSERT INTO employee
VALUES (4, 'Kimmie', 'Lazenbury', 'klazenbury3@gnu.org', 'VP Product Management', '514-570-2309',
        'https://www.bootdey.com/img/Content/avatar/avatar4.png', 1);
INSERT INTO employee
VALUES (5, 'Kimmie', 'Lazenbury', 'klazenbury3@gnu.org', 'VP Product Management', '514-570-2309',
        'https://www.bootdey.com/img/Content/avatar/avatar5.png', 2);
INSERT INTO employee
VALUES (6, 'Kimmie', 'Lazenbury', 'klazenbury3@gnu.org', 'VP Product Management', '514-570-2309',
        'https://www.bootdey.com/img/Content/avatar/avatar2.png',3);
INSERT INTO employee
VALUES (7, 'Kimmie', 'Lazenbury', 'klazenbury3@gnu.org', 'VP Product Management', '514-570-2309',
        'https://www.bootdey.com/img/Content/avatar/avatar3.png',1);
INSERT INTO employee
VALUES (8, 'Kimmie', 'Lazenbury', 'klazenbury3@gnu.org', 'VP Product Management', '514-570-2309',
        'https://www.bootdey.com/img/Content/avatar/avatar4.png',3);



insert into address (id, country, city, street)
values (1, 'China', 'Yaodu', 'Surrey');
insert into address (id, country, city, street)
values (2, 'Thailand', 'Sa Kaeo', 'Mosinee');
insert into address (id, country, city, street)
values (3, 'China', 'Shuangxikou', 'Fallview');
insert into address (id, country, city, street)
values (4, 'Indonesia', 'Waso', 'Montana');
insert into address (id, country, city, street)
values (5, 'China', 'Shigou', 'Vermont');

INSERT INTO client (id, name)
VALUES (1, 'Brusnika');
INSERT INTO client (id, name)
VALUES (2, 'Atom');
INSERT INTO client (id, name)
VALUES (3, 'LSR');
INSERT INTO client (id, name)
VALUES (4, 'GLORAX');

INSERT INTO project (id, name, address_id, client_id, image_url)
values (1, 'Living house', 1, 1, 'http://dummyimage.com/250x250.png/dddddd/000000');
INSERT INTO project (id, name, address_id, client_id, image_url)
values (2, 'Factory', 2, 2, 'http://dummyimage.com/250x250.png/ff4444/ffffff');
INSERT INTO project (id, name, address_id, client_id, image_url)
values (3, 'Supermarket', 3, 3, 'http://dummyimage.com/250x250.png/5fa2dd/ffffff');
INSERT INTO project (id, name, address_id, client_id, image_url)
values (4, 'Mall', 4, 4, 'http://dummyimage.com/250x250.png/dddddd/000000');
INSERT INTO project (id, name, address_id, client_id, image_url)
values (5, 'Living house 2', 5, 2, 'http://dummyimage.com/250x250.png/5fa2dd/ffffff');