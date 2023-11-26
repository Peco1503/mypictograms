import express from "express";
import z from "zod";
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
    throw new Error("El usuario ya está registrado como administrador");
  }

  validatePassword(newParent.password);
  const hashedPassword = hashPassword(newParent.password);

  await db.insert(parents).values({ ...newParent, password: hashedPassword });
  res.sendStatus(200);
});

parentsRouter.get("/parents/:id", async (req, res) => {
  const { id } = z.object({ id: z.coerce.number() }).parse(req.params);
  const db = await Singleton.getDB();

  const results = await db
    .select({ id: parents.id, user: parents.user })
    .from(parents)
    .where(eq(parents.id, id));
  if (results.length < 1) {
    throw new Error("El padre no fue encontrado");
  }
  const parent = results[0];

  res.json(parent);
});

parentsRouter.get("/parents", async (_, res) => {
  const db = await Singleton.getDB();
  const result = await db
    .select({ id: parents.id, user: parents.user })
    .from(parents);
  res.send(result);
});

export default parentsRouter;
