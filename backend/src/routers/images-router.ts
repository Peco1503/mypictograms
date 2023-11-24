import express from "express";
import z from "zod";
import { FirebaseSingleton } from "../db/firebase";
import { getDownloadURL, getStorage, listAll, ref } from "firebase/storage";
import { removeImageExtension } from "../shared/utils";

const imagesRouter = express.Router();

class Image {
  name: string;
  url: string;
  constructor(name: string, url: string) {
    this.name = name;
    this.url = url;
  }
}

imagesRouter.get(
  "/images/category/:categoryName/student/:studentId",
  async (req, res) => {
    const { categoryName, studentId } = z
      .object({ categoryName: z.string(), studentId: z.coerce.number() })
      .parse(req.params);

    const firebaseApp = FirebaseSingleton.getApp();
    const storage = getStorage(firebaseApp);

    let studentFolderName;
    const rootFolderResult = await listAll(ref(storage, "/"));
    for (const prefix of rootFolderResult.prefixes) {
      const folderName = prefix.name;
      const splittedFolderName = folderName.split("-");
      if (
        splittedFolderName.length == 2 &&
        splittedFolderName[0] === String(studentId)
      ) {
        studentFolderName = `/${folderName}`;
      }
    }

    if (!studentFolderName) {
      throw new Error("Could not find student's folder on Firebase");
    }

    const [defaultCategoryResult, studentCategoryResult] = await Promise.all([
      listAll(ref(storage, `/Defecto/${categoryName}`)),
      listAll(ref(storage, `${studentFolderName}/${categoryName}`)),
    ]);

    const files = [
      ...defaultCategoryResult.items,
      ...studentCategoryResult.items,
    ];

    const imageURLSPromises = [];
    const imageNames = [];
    for (const file of files) {
      const imageName = removeImageExtension(file.name);
      if (imageName != "portada") {
        imageURLSPromises.push(getDownloadURL(file));
        imageNames.push(imageName);
      }
    }
    const imageURLS = await Promise.all(imageURLSPromises);

    const images: Image[] = [];
    for (let i = 0; i < imageURLS.length; i++) {
      images.push(new Image(imageNames[i], imageURLS[i]));
    }

    res.json(images);
  },
);

export default imagesRouter;
