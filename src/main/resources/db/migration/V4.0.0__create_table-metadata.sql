create table metadata(
                         ID_METADATA int AUTO_INCREMENT PRIMARY KEY,
                         KEYNAME VARCHAR(30),
                         VALUE VARCHAR(100),
                         DocumentID INT NOT NULL,
                         FOREIGN KEY (DocumentID) REFERENCES DOCUMENT(ID_DOCUMENT)
)