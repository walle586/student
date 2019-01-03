module.exports = {
    lintOnSave: true,

    devServer: {
        proxy: {
            '/mock': {
                target: 'http://localhost:8081',
                changeOrigin: true,
                pathRewrite: {
                    '^/mock': '/api'
                }
            }
        }
    }
}