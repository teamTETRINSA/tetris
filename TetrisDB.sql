DROP TABLE IF EXISTS BestScores;
DROP TABLE IF EXISTS Players;

CREATE TABLE Players (
    idPlayer INT(200),
    playerName VARCHAR(200) NOT NULL,
    PRIMARY KEY (idPlayer)
);

CREATE TABLE Parties (
  idParty INT(200),
  pseudo VARCHARD(200),
  difficulty INT(10),
  Score DATETIME, #?
  partyDate DATE,
  PRIMARY KEY (idParty)
  FOREIGN KEY (pseudo) REFERENCES Players(name)
);
