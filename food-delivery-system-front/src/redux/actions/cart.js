export const addProductToCart = (product) => ({
    type: 'INCREASE_COUNT_OF_PRODUCT',
    payload: product,
});