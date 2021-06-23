DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (id bigserial, title VARCHAR(255), customerName VARCHAR(255), PRIMARY KEY(id));
INSERT INTO orders (title, customerName) VALUES ('Software order', 'Wife');
INSERT INTO orders (title, customerName) VALUES ('Hardware order', 'Husband');
INSERT INTO orders (title, customerName) VALUES ('Toys order', 'Son');
INSERT INTO orders (title, customerName) VALUES ('Food order', 'Granny');
INSERT INTO orders (title, customerName) VALUES ('Car order', 'Granddad');