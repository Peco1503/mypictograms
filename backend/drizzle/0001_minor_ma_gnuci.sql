CREATE TABLE `admins` (
	`id` integer PRIMARY KEY NOT NULL
);
--> statement-breakpoint
CREATE TABLE `images` (
	`id` integer PRIMARY KEY NOT NULL
);
--> statement-breakpoint
ALTER TABLE students ADD `diagnostic` text;--> statement-breakpoint
ALTER TABLE students ADD `therapist_id` integer REFERENCES admins(id);--> statement-breakpoint
ALTER TABLE students ADD `parent_id` integer REFERENCES admins(id);--> statement-breakpoint
/*
 SQLite does not support "Creating foreign key on existing column" out of the box, we do not generate automatic migration for that, so it has to be done manually
 Please refer to: https://www.techonthenet.com/sqlite/tables/alter_table.php
                  https://www.sqlite.org/lang_altertable.html

 Due to that we don't generate migration automatically and it has to be done manually
*/