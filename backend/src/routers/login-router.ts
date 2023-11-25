import { eq, and } from "drizzle-orm";
import express from "express";
import { Singleton } from "../db/connection";
import { admins, parents } from "../db/schema";
import z from "zod";
import { hashPassword } from "../shared/utils";

const loginRouter = express.Router();

loginRouter.post("/login", async (req, res) => {
  const { user, password } = z
    .object({
      user: z.string(),
      password: z.string(),
    })
    .parse(req.body);

  const hashedPassword = hashPassword(password);

  const db = await Singleton.getDB();
  const adminsFound = await db
    .select()
    .from(admins)
    .where(and(eq(admins.user, user), eq(admins.password, hashedPassword)));

  if (adminsFound.length > 0) {
    res.json({
      id: adminsFound[0].id,
      user: adminsFound[0].user,
      type: "ADMIN",
    });
    return;
  }

  const parentsFound = await db
    .select()
    .from(parents)
    .where(and(eq(parents.user, user), eq(parents.password, hashedPassword)));

  if (parentsFound.length > 0) {
    res.json({
      id: parentsFound[0].id,
      user: parentsFound[0].user,
      type: "PARENT",
    });
    return;
  }

  throw new Error("El usuario o la contraseña es incorrecto");
});

export default loginRouter;
