import express from "express";
import { Singleton } from "../db/connection";
import { admins } from "../db/schema";
import { createInsertSchema } from "drizzle-zod";

const adminsRouter = express.Router();

const insertAdminSchema = createInsertSchema(admins);

adminsRouter.post("/admins", async (req, res) => {
  const newAdmin = insertAdminSchema.parse(req.body);
  const db = await Singleton.getDB();
  await db.insert(admins).values(newAdmin);
  res.sendStatus(200);
});

adminsRouter.get("/admins", async (_, res) => {
  const db = await Singleton.getDB();
  const result = await db.select().from(admins);
  res.send(result);
});

export default adminsRouter;
