USE adlister_db;
DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS ads_categories;
CREATE TABLE users (
                       id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                       username VARCHAR(240) NOT NULL UNIQUE,
                       email VARCHAR(240) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id)
);
CREATE TABLE ads (
                     id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                     user_id INT UNSIGNED NOT NULL,
                     title VARCHAR(240) NOT NULL,
                     description TEXT NOT NULL,
                     PRIMARY KEY (id),
                     FOREIGN KEY (user_id) REFERENCES users(id)
                         ON DELETE CASCADE
);
CREATE TABLE categories (
                            id BIGINT unsigned NOT NULL AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id)
);
CREATE TABLE ads_categories (
                                ad_id BIGINT unsigned NOT NULL,
                                category_id BIGINT unsigned NOT NULL,
                                FOREIGN KEY (ad_id) REFERENCES ads(id),
                                FOREIGN KEY (category_id) REFERENCES categories(id)
);
INSERT INTO categories(name) VALUES
('Free'),
('Cars'),
('Furniture'),
('Animals'),
('Electronics'),
('Help Wanted'),
('Bikes'),
('For Rent'),
('For Sale'),
('Tools');

INSERT INTO ads_categories(ad_id, category_id)
VALUES (1, 2), (1, 3), (2, 4), (3, 1),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),
       (2,1),(2,3),(2,5),(2,6),(2,8),(2,9),(2,10), (3, 2), (3, 3), (3, 4),(3, 5),
       (3, 6),(3, 7),(3, 8),(3, 9),(3, 10),(4, 1),(4, 2), (5, 1);