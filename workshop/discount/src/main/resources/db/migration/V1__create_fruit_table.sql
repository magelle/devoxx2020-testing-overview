CREATE TABLE FRUIT
(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    PRICE INT NOT NULL
)