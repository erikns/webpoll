-- Seed the database with sample data
set search_path to webpoll;

-- clear out data
delete from "user";
delete from "survey";
delete from "question";
delete from "option";
delete from "answer";
delete from "response";

-- 1	Testundersøkelse	2016-10-31 22:11:55.566000	2016-11-05 22:12:02.312000	1	testabc	true

-- insert test user with password 'test'
insert into "user" (fname, lname, email, password) values (
    'Test',
    'Testesen',
    'admin@stud.hib.no',
    '$2a$05$hnntq1UZ6aCYyN1xBfQiLeHRt346T1sQ6ZlEl6.zN8RpeXXECdFTS'
);

insert into "survey" (owner_id, name, date, deadline, code, active) values (
    1,
    'Testundersøkelse',
    '2016-10-31 22:00:00.000000',
    '2016-11-30 23:59:59.999999',
    'testabc',
    true
);

insert into "question" (survey_id, question_text, question_type, multiple) values (
    1,
    'Synes du P-Poll ser visuelt bra ut?',
    'MULTIPLE_CHOICE_RADIO',
    false
), (
    1,
    'Har du funnet noen bugs i P-Poll?',
    'MULTIPLE_CHOICE_RADIO',
    false
), (
    1,
    'Har du noen forslag til forbedringer i P-Poll?',
    'FREE_TEXT',
    false
);

insert into "option" (question_id, option_text)
  values (1, 'Ja'),
    (1, 'Nei'),
    (2, 'Ja'),
    (2, 'Nei');


