import { eq } from "drizzle-orm";
import express from "express";
import { Singleton } from "../db/connection";
import { students } from "../db/schema";
import { FirebaseSingleton } from "../db/firebase";
import { getStorage, ref, listAll } from "firebase/storage";
import z from "zod";

const categoriesRouter = express.Router();

categoriesRouter.get("/categories/student/:studentId", async (req, res) => {
  const { studentId } = z
    .object({ studentId: z.coerce.number() })
    .parse(req.params);

  const db = await Singleton.getDB();
  const result = await db
    .select()
    .from(students)
    .where(eq(students.id, studentId));
  if (result.length < 1) {
    throw new Error("Student was not found");
  }
  const student = result[0];

  const firebaseApp = FirebaseSingleton.getApp();
  const storage = getStorage(firebaseApp);

  const [defaultFolderResult, studentFolderResult] = await Promise.all([
    listAll(ref(storage, "/Defecto")),
    listAll(ref(storage, `/${student.id}-${student.name}`)),
  ]);

  const categories = new Set();
  for (const prefix of defaultFolderResult.prefixes) {
    categories.add(prefix.name);
  }
  for (const prefix of studentFolderResult.prefixes) {
    categories.add(prefix.name);
  }

  res.json({ categories: Array.from(categories) });
});

export default categoriesRouter;
