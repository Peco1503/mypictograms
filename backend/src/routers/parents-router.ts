import express from "express";
import { Singleton } from "../db/connection";
import { parents } from "../db/schema";
import { createInsertSchema } from "drizzle-zod";

const parentsRouter = express.Router();

const insertParentSchema = createInsertSchema(parents);

parentsRouter.post("/parents", async (req, res) => {
  const newAdmin = insertParentSchema.parse(req.body);
  const db = await Singleton.getDB();
  await db.insert(parents).values(newAdmin);
  res.sendStatus(200);
});

parentsRouter.get("/parents", async (_, res) => {
  const db = await Singleton.getDB();
  const result = await db.select().from(parents);
  res.send(result);
});

export default parentsRouter;
