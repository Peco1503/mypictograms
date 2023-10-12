import { InferInsertModel, InferSelectModel } from "drizzle-orm";
import { integer, sqliteTable, text } from "drizzle-orm/sqlite-core";

export const admins = sqliteTable("admins", {
  id: integer("id").primaryKey(),
  user: text("user"),
  password: text("password"),
});

export const students = sqliteTable("students", {
  id: integer("id").primaryKey(),
  name: text("name"),
  birthYear: integer("birth_year"),
  cognitiveLevel: integer("cognitive_level"),
  gender: text("gender", { enum: ["male", "female"] }),
  diagnostic: text("diagnostic"),
  therapistId: integer("therapist_id").references(() => admins.id),
  parentId: integer("parent_id").references(() => admins.id),
});

export const images = sqliteTable("images", {
  id: integer("id").primaryKey(),
});

// Students
export type Student = InferSelectModel<typeof students>;
export type NewStudent = InferInsertModel<typeof students>;

// Admins
export type Admin = InferSelectModel<typeof admins>;
export type NewAdmin = InferInsertModel<typeof admins>;
