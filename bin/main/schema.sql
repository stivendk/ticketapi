DROP TABLE IF EXISTS tbl_tickets;
DROP TABLE IF EXISTS tbl_users;

CREATE TABLE tbl_users (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           document_number VARCHAR(10) NOT NULL,
                           first_name VARCHAR(50) NOT NULL,
                           last_name VARCHAR(50) NOT NULL
);

CREATE TABLE tbl_tickets (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             description TEXT(250) NOT NULL,
                             status ENUM('ABIERTO', 'CERRADO') NOT NULL,
                             user_id BIGINT,
                             FOREIGN KEY (user_id) REFERENCES tbl_users(id)
);
