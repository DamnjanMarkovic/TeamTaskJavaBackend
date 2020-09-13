package TeamTask.constants;

public class BackupDocs {


    /*

ALTER TABLE task
DROP COLUMN taskscheduled;

ALTER TABLE task
DROP COLUMN tasksetat;
ALTER TABLE task ADD COLUMN taskscheduled TIMESTAMP WITH TIME ZONE;
ALTER TABLE task ALTER COLUMN taskscheduled SET DEFAULT now();
ALTER TABLE task ADD COLUMN tasksetat TIMESTAMP WITH TIME ZONE;
ALTER TABLE task ALTER COLUMN tasksetat SET DEFAULT now();



INSERT INTO task(tasktitle, tasktext,taskcompleted)
VALUES ('naslov zadatka1', 'tekst zadatka1', true),
('naslov zadatka2', 'tekst zadatka2', true),
 ('naslov zadatka3', 'tekst zadatka3', true),
 ('naslov zadatka4', 'tekst zadatka4', true),
 ('naslov zadatka5', 'tekst zadatka5', true),
 ('naslov zadatka6', 'tekst zadatka6', true)

;


select * from task;





    CREATE DATABASE teamtask;


CREATE TABLE users (
id_user SERIAL PRIMARY KEY,
username TEXT NOT NULL,
password TEXT NOT NULL,
userFirstName TEXT NOT NULL,
id_image INT NOT NULL,
status BOOLEAN NOT NULL,
userEmail TEXT NOT NULL
);

CREATE TABLE user_teams (
id_user_team SERIAL PRIMARY KEY,
id_team INT,
id_user INT
);

CREATE TABLE user_roles (
id_user_roles SERIAL PRIMARY KEY,
id_user INT,
id_role INT
);

CREATE TABLE teams (
id_team SERIAL PRIMARY KEY,
name_team TEXT NOT NULL,
id_image INT
);

CREATE TABLE role (
id_role SERIAL PRIMARY KEY,
role_label TEXT NOT NULL
);

CREATE TABLE images (
id_image SERIAL PRIMARY KEY NOT NULL,
imageLocation TEXT NOT NULL,
imagename TEXT NOT NULL
);

CREATE TABLE hibernate_sequence (
next_val INT
);




INSERT INTO users (id_user, username, password, userFirstName, id_image, status, userEmail) values (1, ’o1’, 'o1', 'Burns', 1, true, 'burns@email.com');

INSERT INTO users (id_user, username, password, userFirstName, id_image, status, userEmail) values (2, 'w1', 'w1', 'Moe', 2, true, 'moe@email.com'),
(3, 'w2', 'w2', 'Bart', 3, true, 'bart@email.com'),
(4, 'w3', 'w3', 'Lisa', 4, true, 'lisa@email.com'),
(5, 'm1', 'm1', 'Apu', 5, true, 'apu@email.com'),
(6, 'm2', 'm2', 'Homer', 6, true, 'homer@email.com'),
(7, 'm3', 'm3', 'Ned', 7, true, 'ned@email.com') ;

insert into role (id_role, role_label) values (1, ‘admin'),(2, 'user');

insert into user_teams (id_user_team, id_team, id_user) values (7,1,1),(11,1,2),(12,2,5),(13,1,3),(14,2,6),(15,2,4),(16,2,7);

insert into user_roles (id_user_roles, id_user, id_role) values (1,1,1),(2,2,2),(3,3,2),(4,4,1),(5,5,2),(6,6,2),(7,7,2);i

insert into teams (id_team, name_team, id_image) values (1,'Burns Team',1),(2,'Lisa Team',4);

insert into images (id_image, imageLocation, imagename) values (1,'src/main/java/restaurantIOS/images/Mr.Burns.jpeg','Mr.Burns.jpeg'),(2,'src/main/java/restaurantIOS/images/Moe.png','Moe.png'),(3,'src/main/java/restaurantIOS/images/bart_simpson_teaser.jpg','bart_simpson_teaser.jpg'),(4,'src/main/java/restaurantIOS/images/lisaSimpson.jpg','lisaSimpson.jpg'),(5,'src/main/java/restaurantIOS/images/apu.jpg','apu.jpg'),(6,'src/main/java/restaurantIOS/images/Homer.jpeg','Homer.jpeg'),(7,'src/main/java/restaurantIOS/images/Ned_Flanders.png','Ned_Flanders.png');
     */

}
