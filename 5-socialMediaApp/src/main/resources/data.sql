INSERT INTO user_details(date_of_birth, name, email, password)
VALUES 
  ('1997-02-03', 'Amr Ahmed', 'Amr@gmail.com', 'password123'),
  ('2025-12-25', 'Murad Amr', 'Murad@gmail.com', 'password123'),
  ('1990-01-01', 'John Doe', 'John@gmail.com', 'password123');


CREATE SEQUENCE IF NOT EXISTS posts_seq START WITH 1 INCREMENT BY 1;

INSERT INTO posts(content, user_id)
VALUES 
  ('Hello World!', 1),
  ('Spring Boot is awesome!', 1),
  ('Java is great!', 2),
  ('Learning REST APIs', 2);

