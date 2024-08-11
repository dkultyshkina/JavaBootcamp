drop schema if exists chat cascade;
drop table if exists chat.User, chat.Message, chat.Chatroom;

create schema if not exists chat;

create table if not exists chat.User (
    user_id serial primary key,
    user_login text not null unique,
    user_password text not null
);

create table if not exists chat.Chatroom (
    chatroom_id serial primary key,
    chatroom_name text not null unique,
    chatroom_owner text not null
);

create table if not exists chat.Message (
    message_id serial primary key,
    message_author bigint not null,
    message_room bigint not null,
    message_text text not null,
    message_date timestamp default CURRENT_TIMESTAMP
);