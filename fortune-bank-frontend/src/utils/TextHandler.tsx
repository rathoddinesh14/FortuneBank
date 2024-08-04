export const ParseText = (input: string) => {
  if (!input) {
    return "Null";
  }
  return input;
};

export const isEnable = (input: string) => {
  if (input === "ENABLED") {
    return true;
  }
  return false;
};
