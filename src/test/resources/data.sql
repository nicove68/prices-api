DROP TABLE IF EXISTS prices;

CREATE TABLE prices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE DEFAULT NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE DEFAULT NOT NULL,
    price_list INT NOT NULL,
    product_id BIGINT NOT NULL,
    priority INT NOT NULL,
    amount DECIMAL(13,2) NOT NULL,
    curr VARCHAR(3) NOT NULL
);

INSERT INTO prices (brand_id, start_date, end_date, price_list, product_id, priority, amount, curr) VALUES
    (1, '2020-06-14T00:00:00Z', '2020-12-31T23:59:59Z', 1, 35455, 0, 35.50, 'EUR'),
    (1, '2020-06-14T15:00:00Z', '2020-06-14T18:30:00Z', 2, 35455, 1, 25.45, 'EUR'),
    (1, '2020-06-15T00:00:00Z', '2020-06-15T11:00:00Z', 3, 35455, 1, 30.50, 'EUR'),
    (1, '2020-06-15T16:00:00Z', '2020-12-31T23:59:59Z', 4, 35455, 1, 38.95, 'EUR');

CREATE INDEX idx_start_date_end_date_product_id_brand_id ON prices (start_date, end_date, product_id, brand_id);