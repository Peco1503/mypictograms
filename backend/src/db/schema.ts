import { InferInsertModel, InferSelectModel } from "drizzle-orm";
import { integer, sqliteTable, text } from "drizzle-orm/sqlite-core";

export const admins = sqliteTable("admins", {
  id: integer("id").primaryKey(),
  user: text("user").notNull(),
  password: text("password").notNull(),
});

export type Admin = InferSelectModel<typeof admins>;
export type NewAdmin = InferInsertModel<typeof admins>;

export const parents = sqliteTable("parents", {
  id: integer("id").primaryKey(),
  user: text("user").notNull(),
  password: text("password").notNull(),
});

export type Parent = InferSelectModel<typeof parents>;
export type NewParent = InferInsertModel<typeof parents>;

export const students = sqliteTable("students", {
  id: integer("id").primaryKey(),
  name: text("name").notNull(),
  birthYear: integer("birth_year").notNull(),
  gender: text("gender", { enum: ["male", "female"] }).notNull(),
  parentId: integer("parent_id").references(() => parents.id),
  maximumMinigameLevel: integer("maximum_minigame_level").notNull(),
  description: text("description"),
  cognitiveLevel: text("cognitive_level"),
  therapistId: integer("therapist_id")
    .references(() => admins.id)
    .notNull(),
});

export type Student = InferSelectModel<typeof students>;
export type NewStudent = InferInsertModel<typeof students>;

export const images = sqliteTable("images", {
  id: integer("id").primaryKey(),
  title: text("title").notNull(),
  category: text("category", {
    enum: [
      "animals",
      "office",
      "transport",
      "weather",
      "family",
      "places",
      "numbers",
      "verbs",
      "sports",
    ],
  }).notNull(),
  src: text("src").notNull(),
});

export type Image = InferSelectModel<typeof images>;
export type NewImage = InferInsertModel<typeof images>;
