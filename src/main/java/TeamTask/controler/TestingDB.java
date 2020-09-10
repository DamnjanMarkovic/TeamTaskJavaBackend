package TeamTask.controler;

public class TestingDB {

    /*

    //prebacivanje iz int u uuid
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
ALTER TABLE users ALTER COLUMN id_user DROP DEFAULT,
ALTER COLUMN id_user SET DATA TYPE UUID USING (uuid_generate_v4()),
ALTER COLUMN id_user SET DEFAULT uuid_generate_v4();

ALTER TABLE teams ALTER COLUMN id_team DROP DEFAULT,
ALTER COLUMN id_team SET DATA TYPE UUID USING (uuid_generate_v4()),
ALTER COLUMN id_team SET DEFAULT uuid_generate_v4();

ALTER TABLE user_teams ALTER COLUMN id_user DROP DEFAULT,
ALTER COLUMN id_user SET DATA TYPE UUID USING (uuid_generate_v4());

ALTER TABLE user_roles ALTER COLUMN id_user DROP DEFAULT,
ALTER COLUMN id_user SET DATA TYPE UUID USING (uuid_generate_v4());

ALTER TABLE user_teams ALTER COLUMN id_team DROP DEFAULT,
ALTER COLUMN id_team SET DATA TYPE UUID USING (uuid_generate_v4());


Burns: e3c8c817-8321-4ddd-a683-d99e6adcd2b9

prebacivanje iz necega u uuid (user_roles, user_teams)
ALTER TABLE user_teams ALTER COLUMN id_user DROP DEFAULT,
ALTER COLUMN id_user SET DATA TYPE UUID USING (uuid_generate_v4());
//ubacivanje usera:
INSERT INTO users (username, password, userfirstname,id_image, status, useremail)
VALUES
    ('oo','oo','prvo Ime',2,1,'john.smith@example.com')

    ;

















CREATE TABLE contacts (
    contact_id uuid DEFAULT uuid_generate_v4 (),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    phone VARCHAR,
    PRIMARY KEY (contact_id)
);



     */
}
