import { createHash } from "crypto";

export const hashPassword = (password: string) => {
  return createHash("sha256").update(password).digest("hex");
};

export const validatePassword = (password: string) => {
  // https://stackoverflow.com/questions/12090077/javascript-regular-expression-password-validation-having-special-characters

  if (password.length < 12) {
    throw new Error("Password must be at least 12 characters");
  }

  const containsUppercase = /^(?=.*[A-Z]).*$/;
  if (!containsUppercase.test(password)) {
    throw new Error("Password must have at least one Uppercase Character.");
  }

  const containsLowercase = /^(?=.*[a-z]).*$/;
  if (!containsLowercase.test(password)) {
    throw new Error("Password must have at least one Lowercase Character.");
  }

  const containsNumber = /^(?=.*[0-9]).*$/;
  if (!containsNumber.test(password)) {
    throw new Error("Password must contain at least one Digit.");
  }

  const containsSymbol = /^(?=.*[~`!@#$%^&*()--+={}\[\]|\\:;"'<>,.?/_₹]).*$/;
  if (!containsSymbol.test(password)) {
    throw new Error("Password must contain at least one Special Symbol.");
  }
};

export const removeImageExtension = (url: string) => {
  // https://stackoverflow.com/questions/1818310/regular-expression-to-remove-a-files-extension
  return url.substring(0, url.indexOf(".")) || url;
};
