INSERT INTO users (id,principal,email,name,password,version) VALUES  (1,'admin','admin@gmail.com','Administrator','admin',0);
INSERT INTO users (id,principal,email,name,password,version) VALUES (2,'other','other@gmail.com','Other','other',0);

INSERT INTO roles (id,role_name,user_id,version) VALUES  (1,'ROLE_ADMIN',1,0);
INSERT INTO roles (id,role_name,user_id,version)  VALUES (2,'ROLE_USER',1,0);
INSERT INTO roles (id,role_name,user_id,version)  VALUES (3,'ROLE_USER',2,0);

INSERT INTO todos (id,description,title,version)  VALUES (1,'to do','TITLE',0);
