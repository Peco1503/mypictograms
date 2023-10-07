import express from "express";
import { Singleton } from "../db/connection";
import { NewStudent, students } from "../db/schema";

const studentsRouter = express.Router();

studentsRouter.get("/add-student", async (_, res) => {
  const newStudent: NewStudent = {
    name: "Pedro Alonso Moreno Salcedo",
    birthYear: 2003,
    cognitiveLevel: -1,
    gender: "male",
  };

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
