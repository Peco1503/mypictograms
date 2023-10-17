import express from "express";
import "express-async-errors";
import type { ErrorRequestHandler } from "express";
import studentsRouter from "./routers/students-router";
import adminsRouter from "./routers/admis-router";

const app = express();
const PORT = 3000;

app.use(express.json());
app.use("/api", studentsRouter);
app.use("/api", adminsRouter);
app.use(((error, _, res, __) => {
  console.error(error.stack);
  res.status(500).json({ error: String(error) });
}) as ErrorRequestHandler);

app.listen(PORT, () => {
  console.log(`Example app listening on port ${PORT}`);
});
