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
	`name` text NOT NULL,
	`birth_year` integer NOT NULL,
	`cognitive_level` text,
	`maximum_minigame_level` integer NOT NULL,
	`gender` text NOT NULL,
	`diagnostic` text,
	`therapist_id` integer NOT NULL,
	`parent_id` integer,
	FOREIGN KEY (`therapist_id`) REFERENCES `admins`(`id`) ON UPDATE no action ON DELETE no action,
	FOREIGN KEY (`parent_id`) REFERENCES `admins`(`id`) ON UPDATE no action ON DELETE no action
);
