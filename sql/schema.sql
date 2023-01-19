CREATE TABLE IF NOT EXISTS example(
    id               BIGSERIAL PRIMARY KEY,
    varchar          VARCHAR  NOT NULL,
    json             JSON  NOT NULL,
    date             DATE NOT NULL,
    bool             BOOLEAN NOT NULL                   DEFAULT true,
    created_at       TIMESTAMPTZ NOT NULL               DEFAULT current_timestamp,
    updated_at       TIMESTAMPTZ NOT NULL               DEFAULT current_timestamp
);

INSERT INTO example (id, varchar, json, date, bool, created_at, updated_at)
    VALUES (1, 'Abc', '{"key": "val"}', '2022-07-07', true, '2022-07-07', '2022-07-07');
