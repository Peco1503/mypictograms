import { eq } from "drizzle-orm";
import express from "express";
import { Singleton } from "../db/connection";
import { students } from "../db/schema";
import z from "zod";
import { FirebaseSingleton } from "../db/firebase";
import { getDownloadURL, getStorage, listAll, ref } from "firebase/storage";

const imagesRouter = express.Router();

imagesRouter.get(
  "/images/category/:categoryName/student/:studentId",
  async (req, res) => {
    const { categoryName, studentId } = z
      .object({ categoryName: z.string(), studentId: z.coerce.number() })
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

    const [defaultCategoryResult, studentCategoryResult] = await Promise.all([
      listAll(ref(storage, `/Defecto/${categoryName}`)),
      listAll(ref(storage, `/${student.id}-${student.name}/${categoryName}`)),
    ]);

    const imageURLSPromises = [];
    for (const item of defaultCategoryResult.items) {
      imageURLSPromises.push(getDownloadURL(item));
    }
    for (const item of studentCategoryResult.items) {
      imageURLSPromises.push(getDownloadURL(item));
    }

    const imageURLS = await Promise.all(imageURLSPromises);
    res.json(imageURLS);
  }
);

export default imagesRouter;
