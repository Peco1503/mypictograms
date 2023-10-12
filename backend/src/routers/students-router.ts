import express from "express";
import { Singleton } from "../db/connection";
import { students } from "../db/schema";
import { createInsertSchema } from "drizzle-zod";
import { z } from "zod";

const studentsRouter = express.Router();

const insertStudentSchema = createInsertSchema(students, {
  birthYear: z.number().positive(),
  maximumMinigameLevel: z.number().min(1).max(4),
});

studentsRouter.post("/students/therapist/:therapistId", async (req, res) => {
  const newStudent = insertStudentSchema.parse(req.body);
  const db = await Singleton.getDB();
  const insertedStudents = await db
    .insert(students)
    .values(newStudent)
    .returning();
  res.send(insertedStudents);
});

studentsRouter.get("/students", async (_, res) => {
  const db = await Singleton.getDB();
  const result = await db.select().from(students);
  res.send(result);
});

export default studentsRouter;
