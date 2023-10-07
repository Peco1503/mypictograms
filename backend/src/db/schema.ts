import { InferInsertModel, InferSelectModel } from "drizzle-orm";
import { integer, sqliteTable, text } from "drizzle-orm/sqlite-core";

export const students = sqliteTable("students", {
  id: integer("id").primaryKey(),
  name: text("name"),
  birthYear: integer("birth_year"),
  cognitiveLevel: integer("cognitive_level"),
  gender: text("gender", { enum: ["male", "female"] }),
});

export type Student = InferSelectModel<typeof students>;
export type NewStudent = InferInsertModel<typeof students>;
