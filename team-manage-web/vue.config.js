const env = process.env.VUE_APP_CURRENT_ENV || 'dev'

const path = require('path')

function resolve(dir) {
  return path.join(__dirname, './', dir)
}

// 基础配置项缓存
let util = {
  indexPath: 'index.html',
  publicPath: '/',
  outputDir: 'dist' //打包生成的文件目录
}

if (['dev'].includes(env)) {
  util.devServer = {
    // 代理
    port: 8080, // 端口号
    host: '0.0.0.0',
    disableHostCheck: true,
    proxy: {
      '/': {
        target: 'http://localhost:8888',
        changeOrigin: true,
        cookieDomainRewrite: {
          '*': ''
        }
      }
    }
  }
}

if (env === 'production') {
  util.publicPath = ''
}

module.exports = {
  chainWebpack: config => {
    config.plugin('define').tap(args => {
      const argv = process.argv
      const icourt = argv[argv.indexOf('--icourt-mode') + 1]

      args[0]['process.env'].MODE = `"${icourt}"`

      return args
    })
    // svg rule loader
    const svgRule = config.module.rule('svg') // 找到svg-loader
    svgRule.uses.clear() // 清除已有的loader, 如果不这样做会添加在此loader之后
    svgRule.exclude.add(/node_modules/) // 正则匹配排除node_modules目录
    svgRule // 添加svg新的loader处理
      .test(/\.svg$/)
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })

    // 修改images loader 添加svg处理
    const imagesRule = config.module.rule('images')
    imagesRule.exclude.add(resolve('src/icons'))
    config.module.rule('images').test(/\.(png|jpe?g|gif|svg)(\?.*)?$/)
  },
  configureWebpack: {
    performance: {
      hints: false
    },
    resolve: {
      alias: {
        assets: '@/assets',
        components: '@/components',
        views: '@/views',
        network: '@/network',
        utils: '@/utils',
        store: '@/store'
      }
    }
  },
  ...util
}
