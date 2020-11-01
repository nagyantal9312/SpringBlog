INSERT INTO BLOGGER(name, birth_date) VALUES('Kiss Béla', '1956-09-11');
INSERT INTO BLOGGER(name, birth_date) VALUES('Nagy Sándor', '1993-12-30');

INSERT INTO CATEGORY(name) VALUES('Sport');
INSERT INTO CATEGORY(name) VALUES('Politika');
INSERT INTO CATEGORY(name) VALUES('Vallás');
INSERT INTO CATEGORY(name) VALUES('Kultúra');

INSERT INTO BLOG_POST(text, title, author_id) VALUES('Ez az első szöveg', 'Első poszt', 1);
INSERT INTO BLOG_POST(text, title, author_id) VALUES('Ez a második szöveg', 'Második poszt', 1);

INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('1','Politika');
INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('2','Politika');
INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('2','Vallás');

INSERT INTO COMMENT(text,author_id,blog_post_id) VALUES('Elso komment', '2', '1');
INSERT INTO COMMENT(text,author_id,blog_post_id) VALUES('Masodik komment', '2', '1');
INSERT INTO COMMENT(text,author_id,blog_post_id) VALUES('Harmadik komment', '1', '1');
INSERT INTO COMMENT(text,author_id,blog_post_id) VALUES('Negyedik komment', '1', '2');

