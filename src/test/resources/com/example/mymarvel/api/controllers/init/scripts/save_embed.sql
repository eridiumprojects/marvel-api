-- CREATE TABLE comics_test
--     (
--         id SERIAL PRIMARY KEY,
--         name VARCHAR(30) NOT NULL
-- );
--
-- CREATE TABLE characters_test
--     (
--         id SERIAL PRIMARY KEY,
--         name VARCHAR(30) NOT NULL
-- );
--
-- -- CREATE TABLE comics_characters__fk
-- -- (
-- --     COMIC_ID BIGINT NOT NULL
-- --             REFERENCES comics_test (id),
-- --     CHARACTER_ID BIGINT NOT NULL
-- --             REFERENCES characters_test (id)
-- -- );
--
-- CREATE TABLE comic_characters_test__fk
-- (
--     comic_id INTEGER,
--     characters_id INTEGER,
--     FOREIGN KEY (comic_id) REFERENCES comics_test (id),
--     FOREIGN KEY (characters_id) REFERENCES characters_test (id)
-- );

INSERT INTO comics(id, name)
VALUES (1, 'Marvel');
SELECT setval('comics_id_seq', (SELECT MAX(id) FROM comics));



