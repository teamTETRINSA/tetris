DROP TABLE IF EXISTS BestScores;
DROP TABLE IF EXISTS Players;

CREATE TABLE Players (
  name VARCHAR(200),
  PRIMARY KEY (name)
);

CREATE TABLE BestScores (
  pseudo VARCHARD(200),
  date DATE,
  difficulty INT(10),
  BestScore DATETIME,
  FOREIGN KEY (pseudo) REFERENCES Players(name)
);
