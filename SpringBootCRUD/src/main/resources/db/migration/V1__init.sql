DROP TABLE items IF EXISTS;
CREATE TABLE IF NOT EXISTS items (id bigserial, title VARCHAR(255), customerName VARCHAR(255), price INTEGER, PRIMARY KEY(id));
INSERT INTO items (title, customerName, price) VALUES
    ('Software Item', 'Wife', 500),
    ('Hardware Item', 'Husband', 10000),
    ('Toys Item', 'Son', 2000),
    ('Food Item', 'Granny', 1000),
    ('Car Item', 'Granddad', 1000000);