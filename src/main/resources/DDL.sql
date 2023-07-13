CREATE DATABASE usermanagement;

\c usermanagement;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  date_of_birth DATE NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  email_address VARCHAR(255) NOT NULL
);
