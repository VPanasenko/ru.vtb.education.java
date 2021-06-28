DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS users (id bigserial primary key, user_name VARCHAR(255));
INSERT INTO users (user_name) VALUES
    ('User #1'),
    ('User #2'),
    ('User #3'),
    ('User #4'),
    ('User #5');

-- DROP TABLE products IF EXISTS;
-- CREATE TABLE IF NOT EXISTS products (id bigserial, productTitle VARCHAR(255), price INTEGER, PRIMARY KEY(id));
-- INSERT INTO products (productTitle, price) VALUES
--     ('Software Item', 500),
--     ('Hardware Item', 10000),
--     ('Toys Item', 2000),
--     ('Food Item', 1000),
--     ('Car Item', 1000000);

DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (id bigserial primary key, order_date DATE, user_id bigint references users(id));
INSERT INTO orders (order_date, user_id) VALUES
    (current_date, 1),
    (current_date, 2),
    (current_date, 3),
    (current_date, 4),
    (current_date, 5);

-- DROP TABLE order_components IF EXISTS;
-- CREATE TABLE IF NOT EXISTS order_components (id bigserial, order_id bigint references orders (id), product_id bigint references products (id), productFixedPrice integer, productAmount integer, PRIMARY KEY(id));
