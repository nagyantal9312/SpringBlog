INSERT INTO BLOGGER(username,name, birth_date,enabled) VALUES('bela89','Kiss Béla', '1956-09-11','true');
INSERT INTO BLOGGER(username, name, birth_date,enabled) VALUES('sanyi','Nagy Sándor', '1993-12-30','1');

INSERT INTO CATEGORY(name) VALUES('Sport');
INSERT INTO CATEGORY(name) VALUES('Politika');
INSERT INTO CATEGORY(name) VALUES('Vallás');
INSERT INTO CATEGORY(name) VALUES('Kultúra');

INSERT INTO ROLE (name) VALUES('USER');
INSERT INTO ROLE (name) VALUES('ADMIN');

INSERT INTO BLOG_POST(text, title) VALUES('Ez az első szöveg', 'Első poszt');
INSERT INTO BLOG_POST(text, title) VALUES('Ez a második szöveg', 'Második poszt');

INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('1','Politika');
INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('2','Politika');
INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('2','Vallás');

/*INSERT INTO COMMENT(text,author_username,blog_post_id) VALUES('Elso komment', 'sanyi', '1');
INSERT INTO COMMENT(text,author_username,blog_post_id) VALUES('Masodik komment', 'sanyi', '1');
INSERT INTO COMMENT(text,author_username,blog_post_id) VALUES('Harmadik komment', 'bela89', '1');
INSERT INTO COMMENT(text,author_username,blog_post_id) VALUES('Negyedik komment', 'bela89', '2');*/

/*INSERT INTO COMMENT(text,blog_post_id) VALUES('Elso komment', '1');
INSERT INTO COMMENT(text,blog_post_id) VALUES('Masodik komment', '1');
INSERT INTO COMMENT(text,blog_post_id) VALUES('Harmadik komment', '1');
INSERT INTO COMMENT(text,blog_post_id) VALUES('Negyedik komment',  '2');*/

