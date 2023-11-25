import express from "express";
import { Singleton } from "../db/connection";
import { admins, parents } from "../db/schema";
import { createInsertSchema } from "drizzle-zod";
import { eq } from "drizzle-orm";
import { hashPassword, validatePassword } from "../shared/utils";

const adminsRouter = express.Router();

const insertAdminSchema = createInsertSchema(admins);

adminsRouter.post("/admins", async (req, res) => {
  const newAdmin = insertAdminSchema.parse(req.body);
  const db = await Singleton.getDB();

  const existsOnParentsTable =
    (await db.select().from(parents).where(eq(parents.user, newAdmin.user)))
      .length > 0;

  if (existsOnParentsTable) {
    throw new Error("El usuario ya está registrado como papá");
  }

  validatePassword(newAdmin.password);
  const hashedPassword = hashPassword(newAdmin.password);

  await db.insert(admins).values({ ...newAdmin, password: hashedPassword });
  res.sendStatus(200);
});

adminsRouter.get("/admins", async (_, res) => {
  const db = await Singleton.getDB();
  const result = await db.select().from(admins);
  res.send(result);
});

export default adminsRouter;
