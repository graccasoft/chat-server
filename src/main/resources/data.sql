INSERT INTO chat_room(id, name)
VALUES(1, 'Fishing');

INSERT INTO app_user(id,username, password)
VALUES(1, 'angler', '$2a$12$6wTMtUZ9z5XAhxx.YNlVIeDRhK.dT/gImfJBAjN7dCxOqYLO71TrO');

INSERT INTO chat_message(id,app_user_id, chat_room_id, message, sent_date)
VALUES(1, 1, 1, 'Where is the cheapest tackle shop', CURRENT_DATE());