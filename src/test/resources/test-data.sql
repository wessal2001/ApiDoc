TRUNCATE TABLE doc_user;
ALTER TABLE doc_user ALTER COLUMN idUser RESTART WITH 1;

INSERT INTO doc_user (username, password, email)
VALUES
    ('test', 'test','test@gmail.com');
