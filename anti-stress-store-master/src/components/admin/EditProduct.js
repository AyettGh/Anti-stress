import React, { useState, useEffect } from 'react';
import { Form, Button, Spinner, Alert } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';

const EditProduct = () => {
  const { id } = useParams(); // récupère l'ID de l'URL
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Charger le produit depuis le backend
  useEffect(() => {
    fetch(`http://localhost:8089/ecommerce/getproduct/${id}`)
      .then((res) => {
        if (!res.ok) throw new Error("Erreur de chargement du produit");
        return res.json();
      })
      .then((data) => {
        // Mapper _id vers id pour compatibilité avec ManageProducts
        setProduct({ ...data, id: data._id });
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, [id]);

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setError(null);
    setLoading(true);

    // Créer un objet à envoyer sans champ "id", mais avec "_id"
    const updatedProduct = { ...product, _id: product.id };
    delete updatedProduct.id;

    fetch(`http://localhost:8089/ecommerce/updateproduct/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedProduct),
    })
      .then((res) => {
        if (!res.ok) throw new Error("Erreur lors de la modification");
        return res.json();
      })
      .then(() => {
        alert('Produit modifié avec succès');
        navigate('/admin/manage-products'); // Redirige vers la page admin
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  };

  if (loading) return <Spinner animation="border" />;

  if (!product) return <div>Chargement du produit...</div>;

  return (
    <div>
      <h2>Modifier le produit</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="name">
          <Form.Label>Nom</Form.Label>
          <Form.Control
            type="text"
            name="name"
            value={product.name}
            onChange={handleChange}
          />
        </Form.Group>

        <Form.Group controlId="price">
          <Form.Label>Prix</Form.Label>
          <Form.Control
            type="number"
            name="price"
            value={product.price}
            onChange={handleChange}
          />
        </Form.Group>

        <Form.Group controlId="stock">
          <Form.Label>Stock</Form.Label>
          <Form.Control
            type="number"
            name="stock"
            value={product.stock}
            onChange={handleChange}
          />
        </Form.Group>

        <Form.Group controlId="description">
          <Form.Label>Description</Form.Label>
          <Form.Control
            as="textarea"
            name="description"
            value={product.description}
            onChange={handleChange}
          />
        </Form.Group>

        <Button variant="primary" type="submit" disabled={loading}>
          {loading ? 'Modification...' : 'Modifier'}
        </Button>
      </Form>
    </div>
  );
};

export default EditProduct;
