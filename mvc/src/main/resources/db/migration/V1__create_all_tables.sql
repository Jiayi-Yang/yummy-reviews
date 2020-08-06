CREATE TABLE users(
	user_id BIGSERIAL PRIMARY KEY,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	user_name VARCHAR(100),
	created_on TIMESTAMP
);
CREATE TABLE items(
	item_id BIGSERIAL PRIMARY KEY,
	item_name VARCHAR(50) NOT NULL,
	item_description VARCHAR(100),
	first_came_out TIMESTAMP
);
CREATE TABLE ratings(
	rating_id BIGSERIAL PRIMARY KEY,
	rating_score INTEGER NOT NULL,
	good_reviews VARCHAR(200),
	bad_reviews VARCHAR(200),
	user_id BIGINT REFERENCES users(user_id),
	item_id BIGINT REFERENCES items(item_id)
);