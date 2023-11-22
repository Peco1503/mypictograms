import express from "express";
import "express-async-errors";
import type { ErrorRequestHandler } from "express";
import loginRouter from "./routers/login-router";
import studentsRouter from "./routers/students-router";
import adminsRouter from "./routers/admins-router";
import parentsRouter from "./routers/parents-router";
import categoriesRouter from "./routers/categories-router";
import morgan from "morgan";
import "dotenv/config";

const app = express();
const PORT = 3000;

app.use(express.json());
app.use(morgan("tiny"));
app.use("/api", loginRouter);
app.use("/api", studentsRouter);
app.use("/api", adminsRouter);
app.use("/api", parentsRouter);
app.use("/api", categoriesRouter);
app.use(((error, _, res, __) => {
  console.error(error.stack);
  res.status(500).json({ error: String(error) });
}) as ErrorRequestHandler);

app.listen(PORT, () => {
  console.log(`Example app listening on port ${PORT}`);
});
