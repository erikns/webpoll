drop schema if exists webpoll cascade;
create schema webpoll;

set search_path to webpoll;

create table "user" (
    id serial primary key,
    fname varchar(40),
    lname varchar(40),
    email varchar(256),
    password varchar(256)
);

create table "survey" (
    id serial primary key,
    name varchar(128) not null,
    date timestamp not null,
    deadline timestamp,
    owner_id integer not null references "user" (id),
    code varchar(32) not null
);

create type q_type as enum ('multiple_choice', 'free_text');
create table "question" (
    id serial primary key,
    survey_id integer not null references "survey" (id),
    question_text text not null,
    question_type q_type not null,
    multiple boolean not null
);

create table "option" (
    id serial primary key,
    question_id integer not null references "question" (id),
    option_text text not null
);

create table "response" (
    id serial primary key,
    survey_id integer not null references "survey" (id)
);

create table "answer" (
    id serial primary key,
    response_id integer not null references "response" (id),
    question_id integer not null references "question" (id),
    option_id integer references "option" (id),
    free_text text
);

