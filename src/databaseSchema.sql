
CREATE TABLE Customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Transaction (
    id SERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

CREATE TABLE RewardPoints (
    id SERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    month VARCHAR(50) NOT NULL,
    points INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);