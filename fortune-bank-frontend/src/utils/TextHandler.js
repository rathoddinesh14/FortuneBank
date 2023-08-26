export const ParseText = (input) => {
  if (!input) {
    return "Null";
  }
  return input;
};

export const isEnable = (input) => {
  if (input === "ENABLED") {
    return true;
  }
  return false;
};
