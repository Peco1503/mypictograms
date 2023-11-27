import express from "express";
import { Singleton } from "../db/connection";
import { students } from "../db/schema";
import { createInsertSchema } from "drizzle-zod";
import { z } from "zod";
import { eq } from "drizzle-orm";

const studentsRouter = express.Router();

const insertStudentSchema = createInsertSchema(students, {
  birthYear: z.number().positive(),
  maximumMinigameLevel: z.number().min(1).max(4),
});

studentsRouter.get("/students", async (req, res) => {
  const { therapistId } = z
    .object({ therapistId: z.coerce.number().optional() })
    .parse(req.query);

  const db = await Singleton.getDB();
  if (therapistId !== undefined) {
    const result = await db
      .select()
      .from(students)
      .where(eq(students.therapistId, therapistId));
    res.json(result);
    return;
  }

  const result = await db.select().from(students);
  res.json(result);
});

studentsRouter.get("/students/parent/:parentId", async (req, res) => {
  const { parentId } = z
    .object({ parentId: z.coerce.number() })
    .parse(req.params);

  const db = await Singleton.getDB();
  const result = await db
    .select()
    .from(students)
    .where(eq(students.parentId, parentId));
  res.json(result);
});

studentsRouter.get("/students/:id", async (req, res) => {
  const { id } = z.object({ id: z.coerce.number() }).parse(req.params);

  const db = await Singleton.getDB();
  const result = await db.select().from(students).where(eq(students.id, id));

  if (result.length !== 1) {
    throw new Error("No se encontró el estudiante");
  }

  res.json(result);
});

studentsRouter.post("/students", async (req, res) => {
  const newStudent = insertStudentSchema.parse(req.body);
  const db = await Singleton.getDB();
  await db.insert(students).values(newStudent).returning();
  res.sendStatus(200);
});

studentsRouter.put("/students/:id", async (req, res) => {
  const { id } = z.object({ id: z.coerce.number() }).parse(req.params);
  const newStudent = insertStudentSchema.parse(req.body);
  const db = await Singleton.getDB();
  await db.update(students).set(newStudent).where(eq(students.id, id));
  res.sendStatus(200);
});

studentsRouter.delete("/students/:id", async (req, res) => {
  const { id } = z.object({ id: z.coerce.number() }).parse(req.params);
  const db = await Singleton.getDB();
  await db.delete(students).where(eq(students.id, id));
  res.sendStatus(200);
});

export default studentsRouter;
