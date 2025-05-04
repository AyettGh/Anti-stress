import React, { useEffect, useState } from "react";
import { Table, Button, Container, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";

const ManageProducts = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8089/ecommerce/allproduct") // Ton endpoint API
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json(); // Tente de parser la réponse JSON
      })
      .then((data) => setProducts(data))
      .catch((error) => setError(error.message)); // Gérer les erreurs
  }, []);

  const handleDelete = (id) => {
    if (window.confirm("Êtes-vous sûr de vouloir supprimer ce produit ?")) {
      fetch(`http://localhost:8089/ecommerce/deleteproduct/${id}`, {
        method: "DELETE",
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Erreur lors de la suppression");
          }

          // Met à jour la liste après suppression
          setProducts(products.filter((product) => product._id !== id));
          alert('Article supprimé avec succés!')
          // Recharger les produits après suppression
        fetch("http://localhost:8089/ecommerce/allproduct")
        .then((res) => res.json())
        .then((data) => setProducts(data));
        })
        .catch((error) => {
          setError(error.message);
        });
    }
  };
  
  return (
    <Container className="mt-4">
      <h2 className="mb-4">Gérer les produits</h2>
      {error && <div className="alert alert-danger">{error}</div>} {/* Afficher l'erreur */}
      <Table striped bordered hover responsive>
        <thead>
          <tr>
            <th>Image</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product._id}>
              <td>
                <img src={product.image} alt={product.name} width="100" />
              </td>
              <td>{product.name}</td>
              <td>{product.price} D</td>
              <td>
                <Row className="g-1">
                  <Col xs={6}>
                    <Link to={`/admin/edit-product/${product.id}`}>
                      <Button variant="success" size="sm" className="w-100 py-1 px-2">
                        Modifier
                      </Button>
                    </Link>
                  </Col>
                  <Col xs={6}>
                    <Button
                      variant="danger"
                      size="sm"
                      className="w-100 py-1 px-2"
                      onClick={() => handleDelete(product.id)}
                    >
                      Supprimer
                    </Button>
                  </Col>
                </Row>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default ManageProducts;
