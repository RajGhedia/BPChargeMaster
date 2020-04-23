
export const getSvgUrl = (image) => {
    const images = require.context("../assets/", false, /\.svg$/);
    return images(`./${image}.svg`)
}