CREATE TABLE dogs (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    breed VARCHAR(50) NOT NULL,
    gender INTEGER,
    image TEXT
);