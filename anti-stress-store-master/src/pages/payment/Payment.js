import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate for navigation
import Breadcrumbs from "../../components/pageProps/Breadcrumbs";

const Payment = () => {
  const [paymentSuccess, setPaymentSuccess] = useState(false);
  const [error, setError] = useState(""); // State for error messages
  const navigate = useNavigate(); // Initialize useNavigate for navigation

  const handlePaymentConfirmation = () => {
    // Reset error messages
    setError("");

    // Get the input values
    const cardholderName = document.getElementById("cardholder-name").value;
    const cardNumber = document.getElementById("card-number").value;
    const expiryMonth = document.getElementById("expiry-month").value;
    const expiryYear = document.getElementById("expiry-year").value;
    const cvv = document.getElementById("cvv").value;

    // Check if any required field is empty
    if (!cardholderName || !cardNumber || !expiryMonth || !expiryYear || !cvv) {
      setError("All fields must be filled out.");
      return;
    }

    // Validate card number (basic validation for 16 digits)
    const cardNumberRegex = /^[0-9]{16}$/;
    if (!cardNumberRegex.test(cardNumber)) {
      setError("Card number must be 16 digits.");
      return;
    }

    // Validate CVV (must be 3 digits)
    const cvvRegex = /^[0-9]{3}$/;
    if (!cvvRegex.test(cvv)) {
      setError("CVV must be a 3-digit number.");
      return;
    }

    // If all validations pass, show success message and redirect to home
    setPaymentSuccess(true);

    // Redirect after 2 seconds (for a smooth transition)
    setTimeout(() => {
      navigate("/"); // Redirect to home page
    }, 2000);
  };

  return (
    <div className="max-w-container mx-auto px-4">
      <Breadcrumbs title="Payment Gateway" />
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6 py-8">
        {/* Left Column: Payment Details */}
        <div className="border p-6 rounded-md shadow-md bg-white space-y-4">
          <p className="text-sm text-gray-500">{new Date().toLocaleString()}</p>
          <h2 className="text-lg font-semibold text-[#33475b]">Payment Details</h2>
          <div>
            <label className="font-medium">Payment Method:</label>
            <div className="flex items-center gap-3 mt-1">
              <img src="https://upload.wikimedia.org/wikipedia/commons/4/41/Visa_Logo.png" alt="Visa" className="w-10 h-auto" />
              <img src="https://upload.wikimedia.org/wikipedia/commons/0/04/Mastercard-logo.png" alt="MasterCard" className="w-10 h-auto" />
            </div>
          </div>
          <div className="space-y-3">
            <input
              id="cardholder-name"
              type="text"
              placeholder="Cardholder Name"
              className="w-full border px-3 py-2 rounded-md"
            />
            <input
              id="card-number"
              type="text"
              placeholder="Card Number"
              className="w-full border px-3 py-2 rounded-md"
            />
            <div className="flex gap-4">
              <select id="expiry-month" className="border px-2 py-2 rounded-md w-1/2">
                <option value="">Month</option>
                <option value="01">01</option>
                <option value="02">02</option>
              </select>
              <select id="expiry-year" className="border px-2 py-2 rounded-md w-1/2">
                <option value="">Year</option>
                <option value="2025">2025</option>
                <option value="2024">2024</option>
                <option value="2023">2023</option>
              </select>
            </div>
            <input
              id="cvv"
              type="text"
              placeholder="CVV"
              className="w-full border px-3 py-2 rounded-md"
            />
            <label className="text-sm flex items-center gap-2">
              <input type="checkbox" />
              I accept the{" "}
              <a href="#" className="text-blue-600 underline">
                terms and conditions
              </a>
            </label>
          </div>
        </div>

        {/* Right Column: Order, Merchant, and Client Info */}
        <div className="space-y-4">
          <div className="border p-4 rounded-md shadow-sm bg-white">
            <h3 className="text-sm font-semibold text-[#33475b] mb-2">Order Details</h3>
            <p><strong>Reference:</strong> MPHRZB</p>
            <p><strong>Amount:</strong> 26 MAD</p>
          </div>
          <div className="border p-4 rounded-md shadow-sm bg-white">
            <h3 className="text-sm font-semibold text-[#33475b] mb-2">Merchant Details</h3>
            <p><strong>Merchant Name:</strong> ONCF (600000298)</p>
          </div>
          <div className="border p-4 rounded-md shadow-sm bg-white">
            <h3 className="text-sm font-semibold text-[#33475b] mb-2">Client Information</h3>
            <p><strong>Name:</strong> customer1 </p>
            <p><strong>Address:</strong> 504 </p>
            <p><strong>Phone:</strong> —</p>
            <p><strong>Email:</strong> customer1@gmail.com</p>
          </div>

          {/* Error message */}
          {error && (
            <div className="bg-red-200 text-red-800 p-4 rounded-md mt-4">
              <p className="font-semibold">{error}</p>
            </div>
          )}

          {/* Success message */}
          {paymentSuccess && (
            <div className="bg-green-200 text-green-800 p-4 rounded-md mt-4">
              <p className="font-semibold">Payment Successful! Redirecting to Home...</p>
            </div>
          )}

          {/* Buttons */}
          <div className="flex gap-4">
            <button
              className="bg-green-600 text-white px-6 py-2 rounded-md hover:bg-green-700"
              onClick={handlePaymentConfirmation}
            >
              Confirm Payment
            </button>
            <button className="bg-red-500 text-white px-6 py-2 rounded-md hover:bg-red-600">
              Cancel
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Payment;
