CREATE TABLE boxTbl (
                        boxId INTEGER PRIMARY KEY,
                        containedIn INTEGER REFERENCES boxTbl(boxId)
);

CREATE TABLE itemTbl (
                         itemId INTEGER PRIMARY KEY,
                         containedIn INTEGER REFERENCES boxTbl(boxId),
                         color VARCHAR(100)
);