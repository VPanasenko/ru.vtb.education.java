DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS users (id bigserial primary key, user_name VARCHAR(255));
INSERT INTO users (user_name) VALUES
    ('User 1'),
    ('User 2'),
    ('User 3'),
    ('User 4'),
    ('User 5');

DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (id bigserial primary key, order_date DATE, user_id bigint references users(id));
INSERT INTO orders (order_date, user_id) VALUES
    (current_date, 1),
    (current_date, 1),
    (current_date, 1),
    (current_date, 1),
    (current_date, 1);

DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial primary key, product_name VARCHAR(255), product_price int);
INSERT INTO products (product_name, product_price) VALUES
    ('One hundred', 100),
    ('Two hundred', 200),
    ('Three hundred', 300),
    ('Four hundred', 400),
    ('Five hundred', 500);

DROP TABLE order_details IF EXISTS;
CREATE TABLE IF NOT EXISTS order_details (id bigserial primary key, order_product_amount int, order_product_fixedPrice int, order_id bigint references orders(id), product_id bigint references products(id));
INSERT INTO order_details (order_product_amount, order_product_fixedPrice, order_id, product_id) VALUES
(101, 500, 5, 5),
(202, 400, 4, 4),
(303, 300, 3, 3),
(404, 200, 2, 2),
(505, 100, 1, 1);