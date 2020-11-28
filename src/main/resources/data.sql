INSERT INTO BLOGGER(username,name,email,birth_date,password,enabled) VALUES('bela','Kis Béla','bela@bela.com','1956-12-20','$2y$12$n6Umc4w0LTqPiEim.MRP/ePHE4HYUPv0t/L.g1WkthprPnswEcBV2',true);
INSERT INTO BLOGGER(username,name,email,birth_date,password,enabled) VALUES('sanyi','Nagy Sándor','sanyi@sanyi.com','1970-06-28','$2y$12$n6Umc4w0LTqPiEim.MRP/ePHE4HYUPv0t/L.g1WkthprPnswEcBV2',true);
INSERT INTO BLOGGER(username,name,email,birth_date,password,enabled) VALUES('admin','Admin Admin','admin@springblog.com','1990-06-18','$2y$12$n6Umc4w0LTqPiEim.MRP/ePHE4HYUPv0t/L.g1WkthprPnswEcBV2',true);


INSERT INTO CATEGORY(name) VALUES('Sport');
INSERT INTO CATEGORY(name) VALUES('Politika');
INSERT INTO CATEGORY(name) VALUES('Vallás');
INSERT INTO CATEGORY(name) VALUES('Kultúra');

INSERT INTO ROLE (name) VALUES('USER');
INSERT INTO ROLE (name) VALUES('ADMIN');

INSERT INTO BLOGGER_ROLES VALUES('bela','1');
INSERT INTO BLOGGER_ROLES VALUES('sanyi','1');
INSERT INTO BLOGGER_ROLES VALUES('admin','1');
INSERT INTO BLOGGER_ROLES VALUES('admin','2');

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

INSERT INTO COMMENT(text,blog_post_id,created_by) VALUES('Elso komment','1','bela89');