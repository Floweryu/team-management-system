module.exports = {
  root: true, // 当前配置为根配置，将不再从上级文件夹查找配置
  env: {
    browser: true, // 设置为所需检查的代码是在浏览器环境运行的
    es6: true, // 设置所需检查代码为 es6 语法书写
    node: true // Node.js 全局变量和 Node.js 作用域
  },
  extends: ['plugin:vue/essential', 'eslint:recommended', '@vue/prettier'], // 扩展使用 vue 检查规则和eslint推荐规则
  parserOptions: {
    parser: 'babel-eslint', // 采用 babel-eslint 作为语法解析器
    sourceType: 'module', // 指定来源的类型，有两种script或module
    ecmaVersion: 6 //指定ECMAScript支持的版本，6为ES6
  },
  rules: {
    'no-console': 0,
    'no-debugger': 0,
    'prettier/prettier': 'error',
    semi: [2, 'never'], // 不使用分号,
    quotes: [2, 'single'] // 强制使用一致的反勾号、双引号或单引号
  }
}
