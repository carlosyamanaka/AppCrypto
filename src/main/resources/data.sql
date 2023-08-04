CREATE TABLE coin
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    RANK INTEGER,
    SYMBOL VARCHAR(10),
    NAME VARCHAR(100),
    SUPPLY NUMERIC(70,30),
    MAXSUPPLY NUMERIC(70,30),
    MARKETCAPUSD NUMERIC(70,30),
    VOLUMEUSD24HR NUMERIC(70,30),
    PRICEUSD NUMERIC(70,30),
    CHANGEPERCENT24HR NUMERIC(70,30),
    VWAP24HR NUMERIC(70,30),
    EXPLORER VARCHAR(70)
);

CREATE TABLE userI(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    EMAIL VARCHAR(70),
    PASS VARCHAR(70)
)