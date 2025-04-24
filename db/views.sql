CREATE TABLE musician_bands
(
    id            SERIAL PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    country       VARCHAR(50),
    genre         VARCHAR(50),
    founding_year INTEGER
);

CREATE TABLE albums
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    release_year      INTEGER,
    release_country   VARCHAR(255),
    label             VARCHAR(255),
    musician_bands_id INTEGER REFERENCES musician_bands (id)
);

CREATE TABLE songs
(
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR(255) NOT NULL,
    duration_in_seconds INTEGER,
    albums_id           INTEGER REFERENCES albums (id)
);

INSERT INTO musician_bands (title, country, genre, founding_year)
VALUES
    ('The Beatles', 'UK', 'Rock', 1960),
    ('Queen', 'UK', 'Rock', 1970),
    ('Metallica', 'USA', 'Heavy Metal', 1981),
    ('Led Zeppelin', 'UK', 'Hard Rock', 1968);

INSERT INTO albums (name, release_year, release_country, label, musician_bands_id)
VALUES ('Abbey Road', 1969, 'UK', 'Apple Records', 1),
       ('A Night at the Opera', 1975, 'UK', 'EMI Records', 2),
       ('Metallica', 1991, 'USA', 'Elektra', 3),
       ('Led Zeppelin IV', 1971, 'UK', 'Atlantic', 4),
       ('Sgt. Peppers Lonely Hearts Club Band', 1967, 'UK', 'Parlophone', 1),
       ('The Game', 1980, 'UK', 'EMI Records', 2),
       ('News of the World', 1977, 'UK', 'Parlophone', 2),
       ('…And Justice for All', 1988, 'USA', 'Elektra', 3),
       ('ReLoad', 1997, 'USA', 'Elektra', 3),
       ('Ride the Lightning', 1984, 'USA', 'Elektra', 3),
       ('Led Zeppelin III', 1970, 'UK', 'Atlantic', 4),
       ('Sheer Heart Attack', 1974, 'UK', 'Parlophone', 2);

INSERT INTO songs (name, duration_in_seconds, albums_id)
VALUES
    ('Come Together', 259, 1),
    ('You Never Give Me Your Money', 243, 1),
    ('Here Comes the Sun', 185, 1),
    ('Something', 182, 1),
    ('Bohemian Rhapsody', 355, 2),
    ('Love of My Life', 219, 2),
    ('Enter Sandman', 332, 3),
    ('Nothing Else Matters', 388, 3),
    ('Wherever I May Roam', 404, 3),
    ('The Unforgiven', 387, 3),
    ('Stairway to Heaven', 482, 4),
    ('Black Dog', 294, 4),
    ('Lovely Rita', 162, 5),
    ('A Day in the Life', 337, 5),
    ('Lucy in the Sky with Diamonds', 206, 5),
    ('Another One Bites the Dust', 215, 6),
    ('Crazy Little Thing Called Love', 182, 6),
    ('The Show Must Go On', 253, 6),
    ('We Are the Champions', 179, 7),
    ('We Will Rock You', 122, 7),
    ('One', 448, 8),
    ('Blackened', 402, 8),
    ('The Unforgiven II', 397, 9),
    ('The Memory Remains', 279, 9),
    ('Fade to Black', 418, 10),
    ('Ride the Lightning', 397, 10),
    ('Immigrant Song', 143, 11),
    ('Since I’ve Been Loving You', 444, 11),
    ('Now I’m Here', 250, 12),
    ('Killer Queen', 181, 12);

create view show_albums
as
select distinct al.id, al.name, release_year, release_country, label, musician_bands_id
from musician_bands mb
join albums al on mb.id = musician_bands_id
join songs s on al.id = albums_id
where release_year > (
  select release_year
  from albums
  where name = 'The Game')
order by al.id;

select * from show_albums;

alter view show_albums rename to show_albums_newer_than_the_game;

select * from show_albums_newer_than_the_game;

drop view show_albums_newer_than_the_game;