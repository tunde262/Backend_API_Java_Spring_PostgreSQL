CREATE DATABASE "revature-p1-database";

\connect "revature-p1-database";

CREATE SEQUENCE users_sequence INCREMENT 1 START 1;

CREATE TABLE users(
    user_id INTEGER PRIMARY KEY DEFAULT nextVal('users_sequence'),
    userName VARCHAR (20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password TEXT NOT NULL
    role VARCHAR(10) NOT NULL DEFAULT 'user' CHECK (role IN ('user', 'admin'))
);