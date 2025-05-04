// src/redux/actions.js

// ➤ Ajouter un produit
export const addProduct = (product) => {
  return async (dispatch) => {
    try {
      const response = await fetch("http://localhost:5000/api/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(product),
      });
      const data = await response.json();
      dispatch({ type: "ADD_PRODUCT", payload: data });
    } catch (error) {
      console.error("Erreur addProduct:", error);
    }
  };
};

// ➤ Modifier un produit
export const editProduct = (product) => {
  return async (dispatch) => {
    try {
      const response = await fetch(`http://localhost:5000/api/products/${product._id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(product),
      });
      const data = await response.json();
      dispatch({ type: "EDIT_PRODUCT", payload: data });
    } catch (error) {
      console.error("Erreur editProduct:", error);
    }
  };
};

// ➤ Supprimer un produit
export const deleteProduct = (id) => {
  return async (dispatch) => {
    try {
      await fetch(`http://localhost:5000/api/products/${id}`, {
        method: "DELETE",
      });
      dispatch({ type: "DELETE_PRODUCT", payload: id });
    } catch (error) {
      console.error("Erreur deleteProduct:", error);
    }
  };
};

// ✅ ➤ Récupérer les produits (manquait probablement l'export ici !)
export const fetchProducts = () => {
  return async (dispatch) => {
    try {
      const response = await fetch("http://localhost:5000/api/products");
      const data = await response.json();
      dispatch({ type: "SET_PRODUCTS", payload: data });
    } catch (error) {
      console.error("Erreur fetchProducts:", error);
    }
  };
};
