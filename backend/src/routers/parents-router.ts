import express from "express";
import { Singleton } from "../db/connection";
import { admins, parents } from "../db/schema";
import { createInsertSchema } from "drizzle-zod";
import { eq } from "drizzle-orm";
import { hashPassword, validatePassword } from "../shared/utils";

const parentsRouter = express.Router();

const insertParentSchema = createInsertSchema(parents);

parentsRouter.post("/parents", async (req, res) => {
  const newParent = insertParentSchema.parse(req.body);
  const db = await Singleton.getDB();

  const existsOnAdminsTable =
    (await db.select().from(admins).where(eq(admins.user, newParent.user)))
      .length > 0;

  if (existsOnAdminsTable) {
    throw new Error("The user is already registered as an admin");
  }

  validatePassword(newParent.password);
  const hashedPassword = hashPassword(newParent.password);

  await db.insert(parents).values({ ...newParent, password: hashedPassword });
  res.sendStatus(200);
});

parentsRouter.get("/parents", async (_, res) => {
  const db = await Singleton.getDB();
  const result = await db.select().from(parents);
  res.send(result);
});

export default parentsRouter;
