import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./pages/Home/Home";
import Login from "./pages/Login/Login";
import CriarDemanda from "./pages/CriarDemanda/CriarDemanda";
import Notificacao from "./pages/Notificacao/Notificacao";
import Chat from "./pages/Chat/Chat";
import DetalhesDemandaPagina from "./pages/DetalhesDemandaPagina/DetalhesDemandaPagina";
import HomeGerencia from "./pages/HomeGerencia/HomeGerencia";
import Escopos from "./pages/Escopos/Escopos";
import DetalhesAta from "./pages/DetalhesAta/DetalhesAta";
import DetalhesPauta from "./pages/DetalhesPauta/DetalhesPauta";

import ToggleColorMode from "./service/TemaProvedor";
import CriarProposta from "./pages/CriarProposta/CriarProposta";
import EditarEscopo from "./pages/EditarEscopo/EditarEscopo";

const App = () => {

  return (
    <ToggleColorMode>
      <Router>
        <Routes>
          <Route path="/login" element={<Login></Login>} />
          <Route path="/" element={<Home></Home>} />
          <Route
            path="/home-gerencia"
            element={<HomeGerencia></HomeGerencia>}
          />
          <Route path="/criar-demanda" element={<CriarDemanda />} />
          <Route path="/criar-proposta" element={<CriarProposta />} />
          <Route path="/notificacao" element={<Notificacao />} />
          <Route path="/chat" element={<Chat />} />
          <Route path="/detalhes-demanda" element={<DetalhesDemandaPagina />} />
          <Route path="/editar-escopo" element={<EditarEscopo />} />
          <Route path="escopos" element={<Escopos />} />
          <Route path="detalhes-ata" element={<DetalhesAta />} />
          <Route path="detalhes-pauta" element={<DetalhesPauta />} />
        </Routes>
      </Router>
    </ToggleColorMode>
  );
};

export default App;
