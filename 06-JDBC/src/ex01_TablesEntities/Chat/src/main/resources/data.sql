insert into chat.User(user_login text, user_password) values ('user1', 'password1');
insert into chat.User(user_login text, user_password) values ('user2', 'password2');
insert into chat.User(user_login text, user_password) values ('user3', 'password3');
insert into chat.User(user_login text, user_password) values ('user4', 'password4');
insert into chat.User(user_login text, user_password) values ('user5', 'password5');

insert into chat.Chatroom(chatroom_name, chatroom_owner) values ('chat1', 1);
insert into chat.Chatroom(chatroom_name, chatroom_owner) values ('chat2', 2);
insert into chat.Chatroom(chatroom_name, chatroom_owner) values ('chat3', 3);
insert into chat.Chatroom(chatroom_name, chatroom_owner) values ('chat4', 4);
insert into chat.Chatroom(chatroom_name, chatroom_owner) values ('chat5', 5);

insert into chat.Message(message_author, message_room, message_text) values (1, 2, 'Hello!');
insert into chat.Message(message_author, message_room, message_text) values (2, 3, 'Hello :)');
insert into chat.Message(message_author, message_room, message_text) values (3, 4, 'Hello)');
insert into chat.Message(message_author, message_room, message_text) values (4, 5, 'Hello ');
insert into chat.Message(message_author, message_room, message_text) values (5, 1, 'Hello.');