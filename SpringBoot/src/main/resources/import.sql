DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (id bigserial, title VARCHAR(255), customerName VARCHAR(255), PRIMARY KEY(id));
INSERT INTO orders (title, customerName) VALUES ('Software Item', 'Wife');
INSERT INTO orders (title, customerName) VALUES ('Hardware Item', 'Husband');
INSERT INTO orders (title, customerName) VALUES ('Toys Item', 'Son');
INSERT INTO orders (title, customerName) VALUES ('Food Item', 'Granny');
INSERT INTO orders (title, customerName) VALUES ('Car Item', 'Granddad');