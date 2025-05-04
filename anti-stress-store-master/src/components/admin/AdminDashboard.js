// src/components/admin/AdminDashboard.js

import React from 'react';
import { Line, Bar, Pie } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  ArcElement, // Nécessaire pour les graphiques à secteurs (Pie Chart)
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

// Enregistrement des composants nécessaires dans Chart.js
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  ArcElement, // Assurez-vous que ArcElement est enregistré pour Pie charts
  Title,
  Tooltip,
  Legend
);

const AdminDashboard = () => {
  // Données pour les graphiques

  // Graphique en ligne
  const lineData = {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
    datasets: [
      {
        label: 'Ventes mensuelles',
        data: [33, 53, 85, 41, 44, 65],
        borderColor: 'rgba(75,192,192,1)',
        backgroundColor: 'rgba(75,192,192,0.2)',
        tension: 0.4,
      },
    ],
  };

  // Graphique en barres
  const barData = {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
    datasets: [
      {
        label: 'Utilisateurs actifs',
        data: [20, 45, 30, 60, 80, 100],
        backgroundColor: 'rgba(255,99,132,0.2)',
        borderColor: 'rgba(255,99,132,1)',
        borderWidth: 1,
      },
    ],
  };

  // Graphique à secteurs (Pie Chart)
  const pieData = {
    labels: ['Produit A', 'Produit B', 'Produit C'],
    datasets: [
      {
        data: [300, 50, 100],
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
        hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
      },
    ],
  };

  return (
    <div className="admin-dashboard">
      <h1 className="text-2xl font-bold mb-6">Tableau de bord Admin</h1>
      
      {/* Graphique en ligne */}
      <div className="mb-8" style={{ width: '700px', height: '400px' }}>
        <h2 className="text-xl mb-4">Ventes Mensuelles</h2>
        <Line data={lineData} options={{ responsive: true, maintainAspectRatio: false }} height={100} width={200} />
      </div>

      {/* Graphique en barres */}
      <div className="mb-8" style={{ width: '700px', height: '400px' }}>
        <h2 className="text-xl mb-4">Utilisateurs Actifs</h2>
        <Bar data={barData} options={{ responsive: true, maintainAspectRatio: false }} height={100} width={200} />
      </div>

      {/* Graphique à secteurs */}
      <div style={{ width: '800px', height: '400px' }}>
        <h2 className="text-xl mb-4">Répartition des Produits</h2>
        <Pie data={pieData} options={{ responsive: true, maintainAspectRatio: false }} height={100} width={200} />
      </div>
    </div>
  );
};

export default AdminDashboard;
