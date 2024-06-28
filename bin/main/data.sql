DELETE FROM tbl_users;
INSERT INTO tbl_users (document_number, first_name, last_name) VALUES ('1234567890', 'John', 'Doe');
INSERT INTO tbl_users (document_number, first_name, last_name) VALUES ('0987654321', 'Jane', 'Smith');

DELETE FROM tbl_tickets;
INSERT INTO tbl_tickets (status, user_id, description, creation_date, update_date) VALUES ('ABIERTO', 1, 'Lorem ipsum dolor, sit amet consectetur adipisicing elit.', '2024-06-26T13:17:15.5503421', '2024-06-27T09:32:25.5503421');
INSERT INTO tbl_tickets (status, user_id, description, creation_date, update_date) VALUES ('CERRADO', 2, 'Obcaecati, fugiat similique? Numquam perferendis quisquam fugit, quas dolore, veritatis blanditiis esse vel tenetur sequi, repellendus eligendi.', '2024-06-20T13:15:15.5503421', '2024-06-23T09:32:25.5503421');
INSERT INTO tbl_tickets (status, user_id, description, creation_date, update_date) VALUES ('ABIERTO', 1, 'Esse et sequi iste eos.', '2024-06-25T10:05:17.5503421', '2024-06-26T19:25:15.5503421');
