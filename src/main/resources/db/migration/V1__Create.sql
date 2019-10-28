


CREATE TABLE user_ (
    id SERIAL ,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    username varchar(50) NOT NULL,
    password varchar(255)  NOT NUll,
    role varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE USER_ACTIVITY (
    id SERIAL,
    id_users INT REFERENCES user_(id),
    activity_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    action_type VARCHAR (20),
    PRIMARY KEY(id)
);

