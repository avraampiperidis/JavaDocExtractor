CREATE TABLE `classentry` (
	`classname`	TEXT NOT NULL,
	`description`	TEXT,
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
);

CREATE TABLE `methodentry` (
	`methodtype`	TEXT,
	`methodname`	TEXT,
	`methoddesc`	TEXT,
	`methodclass`	TEXT,
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
);