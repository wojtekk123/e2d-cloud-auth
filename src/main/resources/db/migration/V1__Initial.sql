


CREATE TABLE users (
    id SERIAL ,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    username varchar(50) NOT NULL,
    password varchar(255)  NOT NUll,
    role varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO USERS ( username,creation_date, password, role)
 VALUES ('wojtek1','2019-10-24 11:15:24','81dc9bdb52d04dc20036dbd8313ed055','ROLE_ADMIN');
INSERT INTO USERS ( username,creation_date, password, role)
 VALUES ('jacek1','2016-03-02 12:05:00','81dc9bdb52d04dc20036dbd8313ed055','ROLE_USER');
