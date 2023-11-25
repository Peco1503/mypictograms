import { createHash } from "crypto";

export const hashPassword = (password: string) => {
  return createHash("sha256").update(password).digest("hex");
};

export const validatePassword = (password: string) => {
  // https://stackoverflow.com/questions/12090077/javascript-regular-expression-password-validation-having-special-characters

  if (password.length < 12) {
    throw new Error("La contraseña debe ser de al menos 12 caracteres");
  }

  const containsUppercase = /^(?=.*[A-Z]).*$/;
  if (!containsUppercase.test(password)) {
    throw new Error("La contraseña debe tener al menos una mayúscula");
  }

  const containsLowercase = /^(?=.*[a-z]).*$/;
  if (!containsLowercase.test(password)) {
    throw new Error("La contraseña debe tener al menos una minúscula");
  }

  const containsNumber = /^(?=.*[0-9]).*$/;
  if (!containsNumber.test(password)) {
    throw new Error("La contraseña debe tener al menos un dígito");
  }

  const containsSymbol = /^(?=.*[~`!@#$%^&*()--+={}\[\]|\\:;"'<>,.?/_₹]).*$/;
  if (!containsSymbol.test(password)) {
    throw new Error("La contraseña debe tener al menos un símbolo especial");
  }
};

export const removeImageExtension = (url: string) => {
  // https://stackoverflow.com/questions/1818310/regular-expression-to-remove-a-files-extension
  return url.substring(0, url.indexOf(".")) || url;
};
