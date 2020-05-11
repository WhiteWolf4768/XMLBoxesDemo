insert into boxTbl (boxId, containedIn) values (1, null);
insert into boxTbl (boxId, containedIn) values (2, null);
insert into boxTbl (boxId, containedIn) values (3, null);
insert into boxTbl (boxId, containedIn) values (4, 3);

insert into itemTbl (itemId, containedIn, color) values (1, null, null);
insert into itemTbl (itemId, containedIn, color) values (2, null, 'red');
insert into itemTbl (itemId, containedIn, color) values (3, null, 'black');
insert into itemTbl (itemId, containedIn, color) values (4, 3, 'white');