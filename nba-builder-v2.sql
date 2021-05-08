BEGIN TRANSACTION;

CREATE TABLE nba_player_salary (
player_id SERIAL  PRIMARY KEY,
player_name VARCHAR(64) NOT NULL,
player_salary decimal (5, 3),
team_code VARCHAR(64)
);


CREATE TABLE nba_team_salary (
nba_team_id SERIAL PRIMARY KEY,
team_name VARCHAR(64) NOT NULL,
team_salary decimal (6, 3)


);

CREATE TABLE created_teams (
created_team_id SERIAL PRIMARY KEY,
team_name  VARCHAR(64),
team_location VARCHAR(64),
team_salary decimal (6,3)

);

CREATE TABLE created_team_players(
player_team_id SERIAL PRIMARY KEY,
player_id integer ,
created_team_id integer ,


FOREIGN KEY (player_id) REFERENCES nba_player_salary(player_id),
FOREIGN KEY (created_team_id) REFERENCES created_teams(created_team_id)




);




COMMIT;