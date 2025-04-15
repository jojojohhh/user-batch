CREATE TABLE user_info (
    emp_no BIGINT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    dept_code VARCHAR(255),
    dept_name VARCHAR(255),
    pos_name VARCHAR(255),
    email_address VARCHAR(255),
    is_leader TINYINT(1),
    is_active TINYINT(1),
    is_updated TINYINT(1)
);
