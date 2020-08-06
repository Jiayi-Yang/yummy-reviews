ALTER TABLE users RENAME COLUMN user_name TO username;
ALTER TABLE users ADD UNIQUE (username);
ALTER TABLE users ADD UNIQUE (email);