import express from "express";
import { Singleton } from "../db/connection";
import { students } from "../db/schema";
import { z } from "zod";

const studentsRouter = express.Router();

const studentSchema = z.object({
  name: z.string(),
  birthYear: z.number().positive(),
  cognitiveLevel: z.string(),
  maximumMinigameLevel: z.number().min(1).max(4),
  gender: z.enum(students.gender.enumValues),
  diagnostic: z.string(),
});

studentsRouter.post("/students", async (req, res) => {
  const newStudent = studentSchema.parse(req.body);

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
