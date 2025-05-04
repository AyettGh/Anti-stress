// src/components/admin/AdminLogin.js
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const AdminLogin = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Logique simple de validation
    if (email === "admin@example.com" && password === "admin123") {
      navigate("/admin"); // Rediriger vers la page admin après connexion réussie
    } else {
      setError("Identifiants incorrects !");
    }
  };

  return (
    <div className="admin-login-container">
      <h2>Login Admin</h2>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="input-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        {error && <div className="error-message">{error}</div>}

        <button type="submit">Se connecter</button>
      </form>
    </div>
  );
};

export default AdminLogin;
