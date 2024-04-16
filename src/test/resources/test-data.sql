TRUNCATE TABLE doc_user;
ALTER TABLE doc_user ALTER COLUMN idUser RESTART WITH 1;

INSERT INTO doc_user (username, password, email)
VALUES
    ('test', 'test','test@gmail.com');
ALTER TABLE document ALTER COLUMN   ID_DOCUMENT RESTART WITH 1;

INSERT INTO document (NOM,TYPE,DATE_CREATION,URL_DOCUMENT)
values('testDoc','type',CURRENT_DATE,'link')