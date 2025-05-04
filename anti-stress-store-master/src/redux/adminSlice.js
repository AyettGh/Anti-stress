// src/redux/adminSlice.js

const initialState = {
    products: [],
  };
  
  const adminReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'ADD_PRODUCT':
        return {
          ...state,
          products: [...state.products, action.payload],
        };
      case 'EDIT_PRODUCT':
        return {
          ...state,
          products: state.products.map((product) =>
            product.id === action.payload.id ? action.payload : product
          ),
        };
      case 'DELETE_PRODUCT':
        return {
          ...state,
          products: state.products.filter(
            (product) => product.id !== action.payload
          ),
        };
      default:
        return state;
    }
  };
  
  export default adminReducer;
  