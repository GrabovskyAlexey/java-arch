CREATE TABLE products
(
    id    BIGSERIAL primary key,
    title VARCHAR(255),
    price int
);

INSERT INTO products (title, price)
VALUES ('Milk', 25),
       ('Bread', 15),
       ('Cheese', 350),
       ('Chocolate', 150),
       ('Black caviar', 1500),
       ('Beef', 500),
       ('Tomato', 150),
       ('Cucumber', 110),
       ('Sausage', 250),
       ('Beer', 75),
       ('Chips', 57),
       ('Fish', 700),
       ('Hazelnut', 800),
       ('Ketchup', 82),
       ('Mayonnaise', 75),
       ('CreamFresh', 70),
       ('IceCream', 52),
       ('Gin', 1700),
       ('Vodka', 1200),
       ('Whiskey', 1050);