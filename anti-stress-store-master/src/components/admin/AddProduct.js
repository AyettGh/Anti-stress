import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { addProduct } from '../../redux/actions';

const AddProduct = () => {
  const [product, setProduct] = useState({
    name: '',
    price: '',
    description: '',
    stock: 1, // valeur par défaut
  });

  const [imageFile, setImageFile] = useState(null);
  const [preview, setPreview] = useState(null);
  const dispatch = useDispatch();

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImageFile(file);
      setPreview(URL.createObjectURL(file));
    } else {
      setImageFile(null);
      setPreview(null);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!product.name || !product.price || !product.description || !imageFile) {
      alert('Veuillez remplir tous les champs et ajouter une image.');
      return;
    }

    const formData = new FormData();
    formData.append('name', product.name);
    formData.append('price', product.price);
    formData.append('description', product.description);
    formData.append('stock', product.stock);
    formData.append('image', imageFile);

    try {
      const response = await fetch('http://localhost:8089/ecommerce/test', {
        method: 'POST',
        body: formData,
      });

      if (response.ok) {
        const data = await response.json();
        alert('Produit ajouté avec succès !');
        dispatch(addProduct(data));
        setProduct({ name: '', price: '', description: '', stock: 1 });
        setImageFile(null);
        setPreview(null);
      } else {
        alert("Erreur lors de l'ajout du produit.");
      }
    } catch (error) {
      alert('Erreur : ' + error.message);
    }
  };

  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-white rounded-2xl shadow-lg">
      <h2 className="text-2xl font-bold mb-6 text-center text-gray-700">Ajouter un nouveau produit</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block mb-1 text-sm font-medium text-gray-600">Nom du produit</label>
          <input
            type="text"
            name="name"
            value={product.name}
            onChange={handleChange}
            className="w-full border border-gray-300 rounded-lg px-4 py-2"
            placeholder="Entrez le nom"
          />
        </div>
        <div>
          <label className="block mb-1 text-sm font-medium text-gray-600">Prix (TND)</label>
          <input
            type="number"
            name="price"
            value={product.price}
            onChange={handleChange}
            className="w-full border border-gray-300 rounded-lg px-4 py-2"
            placeholder="Entrez le prix"
          />
        </div>
        <div>
          <label className="block mb-1 text-sm font-medium text-gray-600">Description</label>
          <textarea
            name="description"
            value={product.description}
            onChange={handleChange}
            rows="3"
            className="w-full border border-gray-300 rounded-lg px-4 py-2"
            placeholder="Décris ton produit"
          ></textarea>
        </div>
        <div>
          <label className="block mb-1 text-sm font-medium text-gray-600">Image</label>
          <input
            type="file"
            name="image"
            accept="image/*"
            onChange={handleImageChange}
            className="w-full border border-gray-300 rounded-lg px-3 py-2"
          />
          {preview && (
            <div className="mt-3">
              <img
                src={preview}
                alt="Prévisualisation"
                className="w-40 h-40 object-cover rounded-lg border border-gray-300"
              />
            </div>
          )}
        </div>
        <div className="text-center">
          <button
            type="submit"
            className="bg-blue-600 text-white font-semibold px-6 py-2 rounded-lg hover:bg-blue-700 transition duration-200"
          >
            Ajouter le produit
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddProduct;
