import { eq, and } from "drizzle-orm";
import express from "express";
import { Singleton } from "../db/connection";
import { admins, parents } from "../db/schema";
import z from "zod";

const loginRouter = express.Router();

loginRouter.post("/login", async (req, res) => {
  const { user, password } = z
    .object({
      user: z.string(),
      password: z.string(),
    })
    .parse(req.body);

  const db = await Singleton.getDB();
  const adminsFound = await db
    .select()
    .from(admins)
    .where(and(eq(admins.user, user), eq(admins.password, password)));

  if (adminsFound.length > 0) {
    res.json({ ...adminsFound[0], type: "ADMIN" });
    return;
  }

  const parentsFound = await db
    .select()
    .from(parents)
    .where(and(eq(parents.user, user), eq(parents.password, password)));

  if (parentsFound.length > 0) {
    res.json({ ...adminsFound[0], type: "PARENT" });
    return;
  }

  throw new Error("User or Password was incorrect");
});

export default loginRouter;
