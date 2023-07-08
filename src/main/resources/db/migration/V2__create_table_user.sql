CREATE TABLE "user" (
       username VARCHAR(100) NOT NULL PRIMARY KEY,
       password VARCHAR(100) NOT NULL
);

INSERT INTO "user" (username, password)
VALUES ('user', 'jdbcDefault');