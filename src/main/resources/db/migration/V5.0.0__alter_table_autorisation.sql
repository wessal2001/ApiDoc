ALTER TABLE AUTORISATION
    DROP PRIMARY KEY, -- Drop existing primary key constraint
    ADD PRIMARY KEY (ID_DOCUMENT, ID_USER, DROIT_ACCES); -- Create new composite primary key