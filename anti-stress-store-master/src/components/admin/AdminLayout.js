import React from "react";
import Header from "../home/Header/Header";
import HeaderBottom from "../home/Header/HeaderBottom";
import Footer from "../home/Footer/Footer";
import FooterBottom from "../home/Footer/FooterBottom";
import { Outlet, ScrollRestoration } from "react-router-dom";
import AdminSidebar from "./AdminSidebar";

const AdminLayout = () => {
  return (
    <div className="flex flex-col min-h-screen">
      <Header />
      <HeaderBottom />
      <ScrollRestoration />
      
      {/* Contenu principal */}
      <div className="flex flex-1">
        <AdminSidebar />
        <main className="flex-1 p-4">
          <Outlet />
        </main>
      </div>

      {/* Footer en dehors du contenu */}
      <Footer />
      <FooterBottom />
    </div>
  );
};

export default AdminLayout