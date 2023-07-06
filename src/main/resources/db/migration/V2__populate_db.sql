INSERT INTO client
(name)
VALUES
    ('Nick'),
    ('Max'),
    ('Jack'),
    ('Orlando'),
    ('Mary'),
    ('Sam'),
    ('Jane'),
    ('Michael'),
    ('Taylor'),
    ('Jennifer'),
    ('Miranda');

INSERT INTO planet
(id, name)
VALUES
    ('MER', 'MERCURY'),
    ('VEN', 'VENUS'),
    ('EARTH', 'EARTH'),
    ('MARS', 'MARS'),
    ('JUP', 'JUPITER'),
    ('SAT', 'SATURN'),
    ('URAN', 'URANUS'),
    ('NEP', 'NEPTUNE');

INSERT INTO ticket
(created_at, client_id, from_planet_id, to_planet_id)
VALUES
    (CAST('2022-01-01 00:00:00' AS TIMESTAMP), 1, 'MER', 'JUP'),
    (CAST('2022-01-02 00:00:00' AS TIMESTAMP), 2, 'VEN', 'SAT'),
    (CAST('2022-01-03 00:00:00' AS TIMESTAMP), 3, 'NEP', 'URAN'),
    (CAST('2022-01-04 00:00:00' AS TIMESTAMP), 4, 'MARS', 'VEN'),
    (CAST('2022-01-05 00:00:00' AS TIMESTAMP), 5, 'EARTH', 'SAT'),
    (CAST('2022-01-06 00:00:00' AS TIMESTAMP), 6, 'JUP', 'MER'),
    (CAST('2022-01-07 00:00:00' AS TIMESTAMP), 7, 'URAN', 'EARTH'),
    (CAST('2022-01-08 00:00:00' AS TIMESTAMP), 8, 'SAT', 'EARTH'),
    (CAST('2022-01-09 00:00:00' AS TIMESTAMP), 9, 'MARS', 'NEP'),
    (CAST('2022-01-10 00:00:00' AS TIMESTAMP), 10, 'VEN', 'JUP'),
    (CAST('2022-01-11 00:00:00' AS TIMESTAMP), 11, 'EARTH', 'MARS'),
    (CAST('2022-01-12 00:00:00' AS TIMESTAMP), 11, 'MARS', 'EARTH');