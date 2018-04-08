// vue.config.js
module.exports = {
  chainWebpack: webpackConfig => {
    webpackConfig.module
      .rule('worker')
      .test(/\.worker\.js$/)
      .use('worker-loader')
      .loader('worker-loader')
  }
}