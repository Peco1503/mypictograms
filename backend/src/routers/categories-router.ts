import express from "express";
import { FirebaseSingleton } from "../db/firebase";
import { getStorage, ref, listAll } from "firebase/storage";

const categoriesRouter = express.Router();

categoriesRouter.get("/categories", async (_, res) => {
  const firebaseApp = FirebaseSingleton.getApp();
  const storage = getStorage(firebaseApp);
  const categoryRefs = (await listAll(ref(storage, "/"))).prefixes;
  const promises = categoryRefs.map((categoryRef) => listAll(categoryRef));
  const results = await Promise.all(promises);

  const categories = new Set();
  for (const result of results) {
    for (const prefix of result.prefixes) {
      categories.add(prefix.name);
    }
  }

  res.json({ categories: Array.from(categories) });
});

export default categoriesRouter;
