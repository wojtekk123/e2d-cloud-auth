CREATE TABLE USER_ACTIVITY (
    id SERIAL,
    id_users INT REFERENCES users(id),
    activity_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    action_type VARCHAR (20),
    PRIMARY KEY(id)
);
