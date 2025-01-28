import React from "react";

const Navbar = () => {
  return (
    <nav className="bg-black p-4">
      <div className="container mx-auto flex justify-between items-center">
        <a href="/"><h1 className="text-white text-xl font-bold">Sistema de Gerenciamento de Veículos</h1></a>
      </div>
    </nav>
  );
};

export default Navbar;
