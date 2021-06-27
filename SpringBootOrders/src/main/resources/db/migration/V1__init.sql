DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS users (userId bigserial, userName VARCHAR(255), PRIMARY KEY(userId));
INSERT INTO users (userName) VALUES
    ('User #1'),
    ('User #2'),
    ('User #3'),
    ('User #4'),
    ('User #5');

DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (productId bigserial, productTitle VARCHAR(255), price INTEGER, PRIMARY KEY(productId));
INSERT INTO products (productTitle, price) VALUES
    ('Software Item', 500),
    ('Hardware Item', 10000),
    ('Toys Item', 2000),
    ('Food Item', 1000),
    ('Car Item', 1000000);

DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (orderId bigserial, orderDate DATE, userId bigint references users(userId), PRIMARY KEY(orderId));

DROP TABLE order_components IF EXISTS;
CREATE TABLE IF NOT EXISTS order_components (orderComponentId bigserial, orderId bigint references orders (orderId), productId bigint references products (productId), productFixedPrice integer, productAmount integer, PRIMARY KEY(orderId));