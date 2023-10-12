CREATE TABLE `admins` (
	`id` integer PRIMARY KEY NOT NULL
);
--> statement-breakpoint
CREATE TABLE `images` (
	`id` integer PRIMARY KEY NOT NULL
);
--> statement-breakpoint
CREATE TABLE `students` (
	`id` integer PRIMARY KEY NOT NULL,
	`name` text,
	`birth_year` integer,
	`cognitive_level` integer,
	`gender` text,
	`diagnostic` text,
	`therapist_id` integer,
	`parent_id` integer,
	FOREIGN KEY (`therapist_id`) REFERENCES `admins`(`id`) ON UPDATE no action ON DELETE no action,
	FOREIGN KEY (`parent_id`) REFERENCES `admins`(`id`) ON UPDATE no action ON DELETE no action
);
