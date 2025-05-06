import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { logosite } from "../../assets/images";

const SignIn = () => {
  const navigate = useNavigate(); // hook pour la redirection

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errEmail, setErrEmail] = useState("");
  const [errPassword, setErrPassword] = useState("");
  const [successMsg, setSuccessMsg] = useState("");

  const handleEmail = (e) => {
    setEmail(e.target.value);
    setErrEmail("");
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
    setErrPassword("");
  };

  const handleSignUp = (e) => {
    e.preventDefault();

    // Validation
    if (!email) {
      setErrEmail("Enter your email");
    }

    if (!password) {
      setErrPassword("Enter your password");
    }

    if (email && password) {
      // Vérifie les identifiants
      if (email === "customer1@gmail.com" && password === "customer1") {
        navigate("/");
      } else if (email === "admin@gmail.com" && password === "admin123") {
        navigate("/admin");
      } else {
        setErrPassword("Email or password is incorrect");
      }
    }
  };

  return (
    <div className="w-full h-screen flex items-center justify-center">
      <div className="w-1/2 hidden lgl:inline-flex h-full text-white">
        <div className="w-[500px] h-full bg-[#808080] px-10 flex flex-col gap-6 justify-center">
          <Link to="/">
            <img src={logosite} alt="logoImg" className="w-900" />
          </Link>
        </div>
      </div>

      <div className="w-full lgl:w-1/2 h-full">
        <form className="w-full lgl:w-[450px] h-screen flex items-center justify-center">
          <div className="px-6 py-4 w-full h-[90%] flex flex-col justify-center overflow-y-scroll scrollbar-thin scrollbar-thumb-primeColor">
            <h1 className="font-titleFont underline underline-offset-4 decoration-[1px] font-semibold text-3xl mdl:text-4xl mb-4">
            Login
            </h1>
            <div className="flex flex-col gap-3">
              {/* Email */}
              <div className="flex flex-col gap-.5">
                <p className="font-titleFont text-base font-semibold text-gray-600">Email</p>
                <input
                  onChange={handleEmail}
                  value={email}
                  className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                  type="email"
                  placeholder="Email"
                />
                {errEmail && (
                  <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                    <span className="font-bold italic mr-1">!</span>
                    {errEmail}
                  </p>
                )}
              </div>

              {/* Password */}
              <div className="flex flex-col gap-.5">
                <p className="font-titleFont text-base font-semibold text-gray-600">Password</p>
                <input
                  onChange={handlePassword}
                  value={password}
                  className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                  type="password"
                  placeholder="Password"
                />
                {errPassword && (
                  <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                    <span className="font-bold italic mr-1">!</span>
                    {errPassword}
                  </p>
                )}
              </div>

              <button
                onClick={handleSignUp}
                className="bg-primeColor hover:bg-black text-gray-200 hover:text-white cursor-pointer w-full text-base font-medium h-10 rounded-md duration-300"
              >
                Login
              </button>
              <p className="text-sm text-center font-titleFont font-medium">
                Don't have an Account?{" "}
                <Link to="/signup">
                  <span className="hover:text-blue-600 duration-300">Sign up</span>
                </Link>
              </p>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default SignIn;
