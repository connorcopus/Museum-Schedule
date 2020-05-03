BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Shifts" (
	"ShiftID"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"Start_time"	TEXT NOT NULL,
	"End_time"	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS "Assignments" (
	"VolunteerID"	INTEGER NOT NULL,
	"ShiftID"	INTEGER NOT NULL,
	FOREIGN KEY("ShiftID") REFERENCES "Shifts",
	FOREIGN KEY("VolunteerID") REFERENCES "Volunteers"("VolunteerID")
);
CREATE TABLE IF NOT EXISTS "Volunteers" (
	"VolunteerID"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"Name"	TEXT NOT NULL
);
INSERT INTO "Shifts" VALUES (1,'0700','0900');
INSERT INTO "Shifts" VALUES (2,'0900','1100');
INSERT INTO "Shifts" VALUES (3,'1100','1300');
INSERT INTO "Shifts" VALUES (4,'1300','1500');
INSERT INTO "Shifts" VALUES (5,'1500','1700');
INSERT INTO "Assignments" VALUES (1,1);
INSERT INTO "Assignments" VALUES (2,2);
INSERT INTO "Assignments" VALUES (3,3);
INSERT INTO "Assignments" VALUES (4,4);
INSERT INTO "Assignments" VALUES (5,5);
INSERT INTO "Assignments" VALUES (6,1);
INSERT INTO "Assignments" VALUES (7,2);
INSERT INTO "Assignments" VALUES (8,3);
INSERT INTO "Assignments" VALUES (9,4);
INSERT INTO "Assignments" VALUES (10,5);
INSERT INTO "Assignments" VALUES (11,1);
INSERT INTO "Assignments" VALUES (12,2);
INSERT INTO "Assignments" VALUES (13,3);
INSERT INTO "Assignments" VALUES (2,4);
INSERT INTO "Assignments" VALUES (11,5);
INSERT INTO "Assignments" VALUES (13,1);
INSERT INTO "Assignments" VALUES (3,2);
INSERT INTO "Assignments" VALUES (1,3);
INSERT INTO "Assignments" VALUES (7,4);
INSERT INTO "Assignments" VALUES (4,5);
INSERT INTO "Volunteers" VALUES (1,'Ben');
INSERT INTO "Volunteers" VALUES (2,'Kassidy');
INSERT INTO "Volunteers" VALUES (3,'Connor');
INSERT INTO "Volunteers" VALUES (4,'Francine');
INSERT INTO "Volunteers" VALUES (5,'Parker');
INSERT INTO "Volunteers" VALUES (6,'Jorge');
INSERT INTO "Volunteers" VALUES (7,'Hset Hset');
INSERT INTO "Volunteers" VALUES (8,'Eliza');
INSERT INTO "Volunteers" VALUES (9,'Mac');
INSERT INTO "Volunteers" VALUES (10,'Marco');
INSERT INTO "Volunteers" VALUES (11,'Abbie');
INSERT INTO "Volunteers" VALUES (12,'Wentao');
INSERT INTO "Volunteers" VALUES (13,'Yvonne');
COMMIT;
