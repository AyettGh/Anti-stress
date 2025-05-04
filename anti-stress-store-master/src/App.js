// src/App.js

import {
  createBrowserRouter,
  RouterProvider,
  createRoutesFromElements,
  Route,
  ScrollRestoration,
  Outlet,  // <-- Ajout de l'import de Outlet
} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

import Footer from "./components/home/Footer/Footer";
import FooterBottom from "./components/home/Footer/FooterBottom";
import Header from "./components/home/Header/Header";
import HeaderBottom from "./components/home/Header/HeaderBottom";
import SpecialCase from "./components/SpecialCase/SpecialCase";
import SpecialOffers from "./components/home/SpecialOffers/SpecialOffers";
import About from "./pages/About/About";
import SignIn from "./pages/Account/SignIn";
import SignUp from "./pages/Account/SignUp";
import Cart from "./pages/Cart/Cart";
import Contact from "./pages/Contact/Contact";
import Home from "./pages/Home/Home";
import Chat from "./pages/Chat/chat";
import Payment from "./pages/payment/Payment";
import ProductDetails from "./pages/ProductDetails/ProductDetails";
import Shop from "./pages/Shop/Shop";

// Import des composants Admin
import AdminLayout from './components/admin/AdminLayout';  // Import de AdminLayout
import AdminDashboard from './components/admin/AdminDashboard';
import AddProduct from './components/admin/AddProduct';
import ManageProducts from './components/admin/ManageProducts'; // ✅
import EditProduct from './components/admin/EditProduct';


const Layout = () => {
  return (
    <div>
      <Header />
      <HeaderBottom />
      <SpecialCase />
      <ScrollRestoration />
      <Outlet />  {/* Utilisation du composant Outlet ici */}
      <Footer />
      <FooterBottom />
    </div>
  );
};

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      {/* Routes principales (client) */}
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="shop" element={<Shop />} />
        <Route path="about" element={<About />} />
        <Route path="contact" element={<Contact />} />
        <Route path="chat" element={<Chat />} />
        <Route path="special" element={<SpecialCase />} />
        <Route path="specialOffers" element={<SpecialOffers />} />
        <Route path="product/:_id" element={<ProductDetails />} />
        <Route path="cart" element={<Cart />} />
        <Route path="paymentgateway" element={<Payment />} />
      </Route>

      {/* Pages d'authentification */}
      <Route path="/signup" element={<SignUp />} />
      <Route path="/signin" element={<SignIn />} />

      {/* Routes administratives (admin) */}
      
      <Route path="/admin" element={<AdminLayout />}>
  <Route index element={<AdminDashboard />} />
  <Route path="add-product" element={<AddProduct />} />
  <Route path="manage-products" element={<ManageProducts />} />  {/* ✅ ajout ici */}
  <Route path="edit-product/:id" element={<EditProduct />} />     {/* Si tu veux ajouter Edit aussi */}
</Route>


        
      
    </Route>
  )
);

function App() {
  return (
    <div className="font-bodyFont">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
