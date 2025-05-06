// src/components/admin/AdminSidebar.js
import React from "react";
import { Link } from "react-router-dom";

const AdminSidebar = () => {
  return (
    <div className="w-64 bg-gray-100 h-full p-4 shadow-md">
      <h2 className="text-xl font-bold mb-4">Admin</h2>
      <ul className="space-y-2">
        <li>
          <Link to="/admin" className="block hover:text-blue-600">
            📊 Tableau de bord
          </Link>
        </li>
        <li>
          <Link to="/admin/add-product" className="block hover:text-blue-600">
            ➕ Ajouter un produit
          </Link>
        </li>
        <li>
          <Link to="/admin/manage-products" className="block hover:text-blue-600">
            🛠️ Gérer les produits
          </Link>
        </li>
       
      </ul>
    </div>
  );
};

export default AdminSidebar;






