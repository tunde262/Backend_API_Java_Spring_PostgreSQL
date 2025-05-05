CREATE DATABASE "revature-p1-database";

\connect "revature-p1-database";

CREATE SEQUENCE users_sequence INCREMENT 1 START 1;

CREATE TABLE users(
    user_id INTEGER PRIMARY KEY DEFAULT nextVal('users_sequence'),
    userName VARCHAR (20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password TEXT NOT NULL,
    role VARCHAR(10) NOT NULL DEFAULT 'user' CHECK (role IN ('user', 'admin'))
);

CREATE TABLE products(
    product_id SERIAL PRIMARY KEY,
    title VARCHAR (100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    quantity INTEGER NOT NULL DEFAULT 1,
    img TEXT -- base64 string
);

ALTER TABLE products
ADD CONSTRAINT user_foreignKey
FOREIGN KEY (user_id) REFERENCES users(user_id)
ON DELETE CASCADE;