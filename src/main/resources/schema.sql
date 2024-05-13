CREATE TABLE IF NOT EXISTS chat_room(
    id INT NOT NULL,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS chat_message(
    id INT NOT NULL,
    app_user_id INT NOT NULL,
    chat_room_id INT NOT NULL,
    message VARCHAR(100) NOT NULL,
    sent_date DATE
);

CREATE TABLE IF NOT EXISTS app_user(
    id INT NOT NULL,
    username VARCHAR(50),
    password VARCHAR(100)
);