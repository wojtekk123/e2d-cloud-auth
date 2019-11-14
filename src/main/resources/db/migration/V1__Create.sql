
CREATE TABLE user_activity
(
    id          SERIAL,
    action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    action_type VARCHAR(20),
    PRIMARY KEY (id)
);


CREATE TABLE user_registration
(
    id          SERIAL,
    user_name    varchar(50)  NOT NULL,
    password    varchar(255) NOT NUll,
    role        varchar(20)  NOT NULL,
    create_date BIGINT REFERENCES user_activity (id),
    PRIMARY KEY (id),
    UNIQUE (user_name)
);

CREATE TABLE user_login
(
    id          SERIAL,
    user_name    varchar(50)  NOT NULL,
    action_date BIGINT REFERENCES user_activity (id),
    PRIMARY KEY (id)
);

