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

INSERT INTO BLOG_POST(title, text, created_by,created_date) VALUES('Lorem ipsum', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed id justo a tortor pellentesque suscipit. Pellentesque felis felis, auctor in nulla in, condimentum imperdiet dolor. Nulla nec dignissim nisi, et accumsan tortor. Integer at mi ultrices risus dignissim euismod. Suspendisse commodo nisl ac velit scelerisque efficitur. Sed nibh augue, tristique eget interdum nec, finibus ut nulla. Nulla tempus rutrum dolor vitae imperdiet. Nam scelerisque arcu quis elementum molestie. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras et dolor libero. Integer mauris magna, malesuada nec vulputate eget, interdum quis arcu. Pellentesque mattis id sapien quis vehicula. In commodo, sapien vitae viverra porttitor, est lectus varius dui, et convallis odio lorem eu velit. Cras sodales, mauris sit amet maximus aliquet, massa est fermentum lorem, eget cursus metus tortor non neque. Aliquam in tortor sed magna tempor elementum. Vivamus pellentesque nulla sit amet felis feugiat, vitae iaculis velit elementum. Fusce et metus quis leo tincidunt euismod. Suspendisse semper, ante ac luctus suscipit, ipsum ex molestie ligula, vel pretium leo urna sed felis. Vestibulum ac vulputate arcu, ut bibendum dolor. Duis ultrices auctor nisl, nec ultricies sem aliquet sed. Sed ac accumsan est. Fusce augue nisi, mollis maximus viverra a, ultrices in lectus. Integer lacinia finibus nisl, sagittis tempus neque egestas quis.',
'bela','2020-09-28');
INSERT INTO BLOG_POST(title, text, created_by,created_date) VALUES('Dolor sit amet', 'Integer at magna vitae orci iaculis molestie et laoreet sapien. Sed in tortor sagittis, aliquam orci id, efficitur quam. Proin a varius eros. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Phasellus at dolor vel arcu interdum tristique. Nunc sit amet massa hendrerit, aliquam dui ut, pellentesque nisl. Pellentesque dolor arcu, mollis at consequat non, aliquet vitae arcu. Aliquam erat volutpat. Sed iaculis risus sit amet turpis dignissim cursus. Quisque tincidunt, tellus a maximus commodo, enim ligula vestibulum dui, at auctor purus diam ut eros. Nunc auctor, nulla sit amet varius consectetur, sapien diam pharetra velit, eget imperdiet metus urna ac risus. Vestibulum a scelerisque sem, sit amet imperdiet massa. Sed at ornare urna.',
'sanyi','2019-06-25');

INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('1','Politika');
INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('2','Politika');
INSERT INTO BLOG_POST_CATEGORIES(blog_posts_id, categories_name) VALUES('2','Vallás');

INSERT INTO COMMENT(text,blog_post_id,created_by,created_date) VALUES('Cras non sagittis tellus. Sed ac leo ac libero bibendum feugiat. Fusce maximus justo a metus vestibulum vestibulum.',
'1','bela','2020-10-10');
INSERT INTO COMMENT(text,blog_post_id,created_by,created_date) VALUES('Nullam non diam at velit venenatis tristique vel sed augue.',
'1','admin','2020-10-11');

INSERT INTO BLOG_POST_REACTION(reaction_type, blogger_username, blog_post_id) VALUES (true, 'bela', '1');
INSERT INTO BLOG_POST_REACTION(reaction_type, blogger_username, blog_post_id) VALUES (false, 'bela', '2');
INSERT INTO BLOG_POST_REACTION(reaction_type, blogger_username, blog_post_id) VALUES (false, 'admin', '1');
INSERT INTO BLOG_POST_REACTION(reaction_type, blogger_username, blog_post_id) VALUES (false, 'admin', '2');

INSERT INTO COMMENT_REACTION(reaction_type, blogger_username, comment_id) VALUES (true, 'bela', '1');
INSERT INTO COMMENT_REACTION(reaction_type, blogger_username, comment_id) VALUES (true, 'bela', '2');
INSERT INTO COMMENT_REACTION(reaction_type, blogger_username, comment_id) VALUES (true, 'admin', '1');
INSERT INTO COMMENT_REACTION(reaction_type, blogger_username, comment_id) VALUES (true, 'admin', '2');