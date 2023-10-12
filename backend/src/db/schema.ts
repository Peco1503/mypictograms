import { InferInsertModel, InferSelectModel } from "drizzle-orm";
import { integer, sqliteTable, text } from "drizzle-orm/sqlite-core";

export const admins = sqliteTable("admins", {
  id: integer("id").primaryKey(),
});

export const students = sqliteTable("students", {
  id: integer("id").primaryKey(),
  name: text("name").notNull(),
  birthYear: integer("birth_year").notNull(),
  cognitiveLevel: text("cognitive_level"),
  maximumMinigameLevel: integer("maximum_minigame_level").notNull(),
  gender: text("gender", { enum: ["male", "female"] }).notNull(),
  diagnostic: text("diagnostic"),
  therapistId: integer("therapist_id")
    .references(() => admins.id)
    .notNull(),
  parentId: integer("parent_id").references(() => admins.id),
});

export const images = sqliteTable("images", {
  id: integer("id").primaryKey(),
});

export type Student = InferSelectModel<typeof students>;
export type NewStudent = InferInsertModel<typeof students>;
