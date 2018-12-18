module.exports = {
  root: true,
  parserOptions: {
    sourceType: "module",
    parser: "babel-eslint"
  },
  env: {
    browser: true
  },
  extends: [
    "prettier",
    "prettier/standard",
    "plugin:vue/recommended"
  ],
  plugins: [
    "vue",
    "prettier"
  ],
  rules: {
    "prettier/prettier": "error",
    "vue/html-self-closing": 0,
    "vue/max-attributes-per-line": 0,
    "vue/attributes-order": 0,
    "vue/no-unused-vars": 0
  }
};