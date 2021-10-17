module.exports = {
  transpileDependencies: ["vuetify"],
  devServer: {
    port: 3000,
    proxy: "http://localhost:8080",
  },
};
