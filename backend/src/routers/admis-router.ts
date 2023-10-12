import express from "express";
import { Singleton } from "../db/connection";
import { NewAdmin, admins } from "../db/schema";

const adminsRouter = express.Router();

adminsRouter.post("/add-admin", async (_, res) => {
  const newAdmin: NewAdmin = {
    user: "Laura",
    password: "admin1234",
  };

  const db = await Singleton.getDB();
  const insertedStudents = await db.insert(admins).values(newAdmin).returning();
  res.send(insertedStudents);
});

adminsRouter.get("/admins", async (_, res) => {
  const db = await Singleton.getDB();
  const result = await db.select().from(admins);
  res.send(result);
});

export default adminsRouter;
