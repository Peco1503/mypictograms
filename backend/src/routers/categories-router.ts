import { eq } from "drizzle-orm";
import express from "express";
import { Singleton } from "../db/connection";
import { students } from "../db/schema";
import { FirebaseSingleton } from "../db/firebase";
import { getStorage, ref, listAll, getDownloadURL } from "firebase/storage";
import z from "zod";
import { removeImageExtension } from "../shared/utils";

const categoriesRouter = express.Router();

class Category {
  name: string;
  thumbnail: string;
  constructor(name: string, thumbnail: string) {
    this.name = name;
    this.thumbnail = thumbnail;
  }
}

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

  let studentFolderName;
  const rootFolderResult = await listAll(ref(storage, "/"));
  for (const prefix of rootFolderResult.prefixes) {
    const folderName = prefix.name;
    const splittedFolderName = folderName.split("-");
    if (
      splittedFolderName.length == 2 &&
      splittedFolderName[0] === String(student.id)
    ) {
      studentFolderName = `/${folderName}`;
    }
  }

  if (!studentFolderName) {
    throw new Error("Could not find student's folder on Firebase");
  }

  const [defaultFolderResult, studentFolderResult] = await Promise.all([
    listAll(ref(storage, "/Defecto")),
    listAll(ref(storage, studentFolderName)),
  ]);

  const categoryFolders = [];
  for (const prefix of defaultFolderResult.prefixes) {
    categoryFolders.push(prefix.fullPath);
  }
  for (const prefix of studentFolderResult.prefixes) {
    categoryFolders.push(prefix.fullPath);
  }

  const categoryFolderContentsPromises = [];
  for (const categoryFolder of categoryFolders) {
    categoryFolderContentsPromises.push(listAll(ref(storage, categoryFolder)));
  }
  const categoryFolderContents = await Promise.all(
    categoryFolderContentsPromises,
  );

  const categoryNames = new Set();
  const thumbnailURLSPromises: any = [];
  for (const categoryFolderContent of categoryFolderContents) {
    for (const item of categoryFolderContent.items) {
      const categoryName = item.parent!.name;
      if (
        removeImageExtension(item.name) === "portada" &&
        !(categoryName in categoryNames)
      ) {
        thumbnailURLSPromises.push(getDownloadURL(item));
        categoryNames.add(categoryName);
        break;
      }
    }
  }

  const thumbnailURLS = await Promise.all(thumbnailURLSPromises);

  const categories: Category[] = [];
  categoryNames.clear();
  let i = 0;
  for (const categoryFolderContent of categoryFolderContents) {
    for (const item of categoryFolderContent.items) {
      const categoryName = item.parent!.name;
      if (
        removeImageExtension(item.name) === "portada" &&
        !(categoryName in categoryNames)
      ) {
        categories.push(new Category(categoryName, thumbnailURLS[i]));
        i += 1;
        categoryNames.add(categoryName);
        break;
      }
    }
  }

  res.json({ categories });
});

export default categoriesRouter;
