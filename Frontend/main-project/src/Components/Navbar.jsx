import React from "react";
import Logo from '../Assets/Logo.svg'

const Navbar = () => {
  return (
    <nav className="bg-black p-4">
      <div className="container mx-auto flex justify-between items-center">
        <a href="/" className="flex items-center"><h1 className="text-white text-xl font-thin font-bold">Sistema de Gerenciamento de Veículos</h1> <img src={Logo} className="w-10 h-10 ml-2" /></a>
      </div>
    </nav>
  );
};

export default Navbar;
