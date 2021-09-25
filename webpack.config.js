var path = require('path');

module.exports = {
    entry: './src/main/js/app.js',
    devtool: 'hidden-source-map',
    cache: true,
    mode: 'production',
    output: {
        path: __dirname,
        filename: './src/resources/public/js/bundle.js'
    },
    devServer: {
        inline: false,
        contentBase: "./dist",
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            },{
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
              },

        ]
    },
    
};