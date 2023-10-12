import express from "express";
import studentsRouter from "./routers/students-router";
import adminsRouter from "./routers/admis-router";
const app = express();
const PORT = 3000;

app.use(express.json());
app.use("/api", studentsRouter);
app.use("/api", adminsRouter);

app.listen(PORT, () => {
  console.log(`Example app listening on port ${PORT}`);
});
