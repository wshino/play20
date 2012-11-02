# --- !Ups

CREATE TABLE Task (
  id int(10) NOT NULL AUTO_INCREMENT,
  label varchar(64) NOT NULL,
  PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Task (label) VALUES ("おれお");
INSERT INTO Task (label) VALUES ("おれ");
INSERT INTO Task (label) VALUES ("おれおooooo");

# --- !Downs
DROP TABLE Task