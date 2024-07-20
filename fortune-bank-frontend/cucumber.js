module.exports = {
    default: {
      paths: ["src/test/features/"],
      format: ["json:./reports/cucumber.json"],
      formatOptions: { snippetInterface: "async-await" },
      require: ["src/test/steps/*.ts"],
      requireModule: ["ts-node/register"],
    }
  };
  