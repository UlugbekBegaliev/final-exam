CREATE TABLE IF NOT EXISTS reviews (
id INT NOT NULL,
place_id INT NOT NULL,
user_id INT NOT NULL,
rating INT NOT NULL,
content VARCHAR(128) NOT NULL,
review_date DATE NOT NULL,
review_time TIME NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY ("place_id") REFERENCES places (id),
FOREIGN KEY ("user_id") REFERENCES users (id)
);