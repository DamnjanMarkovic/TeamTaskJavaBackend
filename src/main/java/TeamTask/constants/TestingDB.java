package TeamTask.constants;

public class TestingDB {

    /*


    delete from images
WHERE id_image BETWEEN -35 AND -4;


    ALTER TABLE users
ALTER COLUMN status
SET default true;
CREATE SEQUENCE images_sequence START 1;
ALTER TABLE images ALTER COLUMN id_image SET DEFAULT nextval('images_sequence');
CREATE SEQUENCE user_images_sequence START 1;
ALTER TABLE user_images ALTER COLUMN id_user_images SET DEFAULT nextval('user_images_sequence');
CREATE SEQUENCE user_teams_sequence START 1;
ALTER TABLE user_teams ALTER COLUMN id_user_teams SET DEFAULT nextval('user_teams_sequence');
CREATE SEQUENCE user_roles_sequence START 1;
ALTER TABLE user_roles ALTER COLUMN id_user_roles SET DEFAULT nextval('user_roles_sequence');

DELETE FROM images WHERE id_image = 1;

    CREATE SEQUENCE my_table_column_id_seq;
SELECT 1
  FROM team_task
 HAVING COUNT(distinct id_team_task)=COUNT(id_team_task)
    AND SUM(CASE WHEN id_team_task IS NULL THEN 0 ELSE 1 END)=
        SUM(CASE WHEN id_team_task IS NULL THEN 1 ELSE 1 END);

ALTER TABLE team_task
ALTER COLUMN id_team_task SET DEFAULT nextval('my_table_column_id_seq');



CREATE TABLE date_test (datetime timestamp(3) with time zone);
insert into date_test values(to_timestamp(1525745241.879));
select EXTRACT(epoch FROM datetime) from date_test;

ALTER TABLE task ALTER COLUMN taskscheduled DROP DEFAULT,
ALTER COLUMN taskscheduled SET DATA TYPE datetime timestamp(3) with time zone,
ALTER COLUMN taskscheduled SET DEFAULT datetime timestamp(3) with time zone;




DELETE FROM users
WHERE id_user = 'ba06171d-b89f-4b1f-b4b8-a0e0dda1eb57';

ALTER TABLE team_task ALTER COLUMN taskid SET DATA TYPE TEXT;
select * from team_task


ALTER TABLE task ALTER COLUMN taskid DROP DEFAULT,
ALTER COLUMN taskid SET DATA TYPE UUID USING (uuid_generate_v4()),
ALTER COLUMN taskid SET DEFAULT uuid_generate_v4();

ALTER TABLE task ALTER COLUMN teamid DROP DEFAULT,
ALTER COLUMN teamid SET DATA TYPE UUID USING (uuid_generate_v4());

ALTER TABLE task ALTER COLUMN userid DROP DEFAULT,
ALTER COLUMN userid SET DATA TYPE UUID USING (uuid_generate_v4());


CREATE TABLE task (
    taskid uuid DEFAULT uuid_generate_v4 (),
    tasktitle VARCHAR NOT NULL,
	taskscheduled timestamp,
    tasktext VARCHAR NOT NULL,
	tasksetat timestamp,
	taskcompleted Boolean,
    PRIMARY KEY (taskid)
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
ALTER TABLE users ALTER COLUMN id_user DROP DEFAULT,
ALTER COLUMN id_user SET DATA TYPE UUID USING (uuid_generate_v4()),
ALTER COLUMN id_user SET DEFAULT uuid_generate_v4();

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
ALTER TABLE teams ALTER COLUMN id_team DROP DEFAULT,
ALTER COLUMN id_team SET DATA TYPE UUID USING (uuid_generate_v4()),
ALTER COLUMN id_team SET DEFAULT uuid_generate_v4();

ALTER TABLE user_teams ALTER COLUMN id_user DROP DEFAULT,
ALTER COLUMN id_user SET DATA TYPE UUID USING (uuid_generate_v4());

ALTER TABLE user_roles ALTER COLUMN id_user DROP DEFAULT,
ALTER COLUMN id_user SET DATA TYPE UUID USING (uuid_generate_v4());

ALTER TABLE user_teams ALTER COLUMN id_team DROP DEFAULT,
ALTER COLUMN id_team SET DATA TYPE UUID USING (uuid_generate_v4());
src/main/java/TeamTask/images/apu.jpg

CREATE TABLE user_images (
    id_user_images serial not null,
    id_user UUID,
    id_image INT,
    PRIMARY KEY (id_user_images)
);
CREATE TABLE user_task (
    id_user_task serial not null,
    id_user UUID,
    taskid UUID,
    PRIMARY KEY (id_user_task)
);
CREATE TABLE team_task (
    id_team_task serial not null,
    id_team UUID,
    taskid UUID,
    PRIMARY KEY (id_team_task)
);

//ubacivanje usera:
INSERT INTO users (username, password, userfirstname,id_image, status, useremail)
VALUES
    ('oo','oo','prvo Ime',2,1,'john.smith@example.com')

    ;

// spajanje slika sa user-om

CREATE TABLE user_images (
    id_user_images serial not null,
    id_user UUID,
    id_image INT,
    PRIMARY KEY (id_user_images)
);
CREATE TABLE user_task (
    id_user_task serial not null,
    id_user UUID,
    taskid UUID,
    PRIMARY KEY (id_user_task)
);
CREATE TABLE team_task (
    id_team_task serial not null,
    id_team UUID,
    taskid UUID,
    PRIMARY KEY (id_team_task)
);



CREATE TABLE contacts (
    contact_id uuid DEFAULT uuid_generate_v4 (),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    phone VARCHAR,
    PRIMARY KEY (contact_id)
);
CREATE TABLE task (
    taskid uuid DEFAULT uuid_generate_v4 (),
    tasktitle VARCHAR NOT NULL,
	taskscheduled timestamp,
    tasktext VARCHAR NOT NULL,
	tasksetat timestamp,
	taskcompleted Boolean,
    PRIMARY KEY (taskid)
);



     */
}
