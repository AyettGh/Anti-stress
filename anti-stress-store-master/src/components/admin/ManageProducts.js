import React, { useEffect, useState } from "react";
import { Table, Button, Container, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";
import { FaEdit, FaTrashAlt } from "react-icons/fa"; // FontAwesome


const ManageProducts = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8089/ecommerce/allproduct")
      .then((res) => res.json())
      .then(async (data) => {
        // Appeler /productimage/{id} pour chaque produit
        const productsWithImages = await Promise.all(
          data.map(async (product) => {
            const imgRes = await fetch(`http://localhost:8089/ecommerce/productimage/${product.id}`);
            const base64 = await imgRes.text();
            return { ...product, imageUrl: `data:image/jpeg;base64,${base64}` };
          })
        );
        setProducts(productsWithImages);
      })
      .catch((err) => setError(err.message));
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
              <img src={product.imageUrl} alt={product.name} width="50" />
              </td>
              <td>{product.name}</td>
              <td>{product.price} D</td>
              <td>
                  <Row className="g-1 justify-content-center">
                    <Col xs="auto">
                      <Link to={`/admin/edit-product/${product.id}`}>
                        <Button variant="outline-success" size="sm" className="d-flex align-items-center">
                          <FaEdit />
                        </Button>
                      </Link>
                    </Col>
                    <Col xs="auto">
                      <Button
                        variant="outline-danger"
                        size="sm"
                        className="d-flex align-items-center"
                        onClick={() => handleDelete(product.id)}
                      >
                        <FaTrashAlt />
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

