INSERT INTO CUSTOMER (id, name,email,password) VALUES (1, 'Mick', 'mickknutson@gmail.com', 'mick00');
INSERT INTO CUSTOMER (id, name,email,password) VALUES (2, 'Bruce', 'brucewayne@gmail.com', 'batman01');
INSERT INTO CUSTOMER (id, name,email,password) VALUES (3, 'Clark', 'clarkkent@gmail.com', 'superman02');

INSERT INTO EVENT (id,code,title,description) VALUES (1, 'CNF001', 'All-Java Conference', 'Lectures and exhibits covering all Java topics');
INSERT INTO EVENT (id,code,title,description) VALUES (2, 'CNC001', 'Rock Concert', 'full-blown rock concert');
INSERT INTO EVENT (id,code,title,description) VALUES (3, 'CNC002', 'Hip-Hop Concert', '90s hip-hop concert');

INSERT INTO REGISTRATION (id, event_id,customer_id,registration_date, notes) VALUES (1, 1, 1, '2020-02-02', 'are meals included?');
INSERT INTO REGISTRATION (id, event_id,customer_id,registration_date, notes) VALUES (2, 3, 3, '2020-01-15', 'is parking available');
INSERT INTO REGISTRATION (id, event_id,customer_id,registration_date, notes) VALUES (3, 2, 2, '2020-03-01', 'is parking available');