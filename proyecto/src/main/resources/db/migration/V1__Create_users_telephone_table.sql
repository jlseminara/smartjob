CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_random_salt VARCHAR(1000) NOT NULL,
    password_bcrypt VARCHAR(1000) NOT NULL,
    token VARCHAR(1000) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN NOT NULL
);

CREATE TABLE telephones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    number VARCHAR(20) NOT NULL,
    city_code VARCHAR(20) NOT NULL,
    country_code VARCHAR(20) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_users_name ON users(name);
CREATE INDEX idx_telephones_user ON telephones(user_id);
