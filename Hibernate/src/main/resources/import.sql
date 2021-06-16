DROP TABLE students IF EXISTS;
CREATE TABLE IF NOT EXISTS students (id bigserial, name VARCHAR(255), score INTEGER, PRIMARY KEY(id));
INSERT INTO students (name, score) VALUES ('Victor', 100);

DROP TABLE animals IF EXISTS;
CREATE TABLE IF NOT EXISTS animals (id bigserial, typename VARCHAR(255), age INTEGER, PRIMARY KEY(id));
INSERT INTO animals (typename, age) VALUES ('Cat', 2);

DROP TABLE vehicles IF EXISTS;
CREATE TABLE IF NOT EXISTS vehicles (id bigserial, manufactor VARCHAR(255), model VARCHAR(255), PRIMARY KEY(id));
INSERT INTO vehicles (manufactor, model) VALUES ('Nissan', 'Tiida');