import express from "express";
import studentsRouter from "./routers/students-router";
const app = express();
const PORT = 3000;

app.use(express.json());
app.use("/api", studentsRouter);

app.listen(PORT, () => {
  console.log(`Example app listening on port ${PORT}`);
});
