import express from "express";
import { Singleton } from "../db/connection";
import { admins } from "../db/schema";
import { createInsertSchema } from "drizzle-zod";
import { z } from "zod";

const adminsRouter = express.Router();

const insertAdminSchema = createInsertSchema(admins, {
  user: z.string(),
  password: z.string(),
});

adminsRouter.post("/admins", async (req, res) => {
  const newAdmin = insertAdminSchema.parse(req.body);
  const db = await Singleton.getDB();
  const insertedAdmins = await db.insert(admins).values(newAdmin).returning();
  res.send(insertedAdmins);
});

adminsRouter.get("/admins", async (_, res) => {
  const db = await Singleton.getDB();
  const result = await db.select().from(admins);
  res.send(result);
});

export default adminsRouter;
