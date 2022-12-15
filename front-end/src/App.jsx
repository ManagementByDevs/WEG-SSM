import "./App.css";
import React, { useState, useMemo } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";

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
import NotFoundPage from "./pages/NotFoundPage/NotFoundPage";

import ToggleColorMode from "./service/TemaProvedor";
import CriarProposta from "./pages/CriarProposta/CriarProposta";
import EditarEscopo from "./pages/EditarEscopo/EditarEscopo";

import UserContext from "./service/UserContext";

const App = () => {
  const [user, setUser] = useState(null);

  const userValueContext = useMemo(
    () => ({
      user,
      setUser: () => {
        setUser();
      },
    }),
    [user]
  );

  return (
    <ToggleColorMode>
      <UserContext.Provider value={userValueContext}>
        <Router>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route
              path="/"
              element={
                <ProtectedRoute>
                  <Home />
                </ProtectedRoute>
              }
            />
            <Route path="/home-gerencia" element={<HomeGerencia />} />
            <Route path="/criar-demanda" element={<CriarDemanda />} />
            <Route path="/criar-proposta" element={<CriarProposta />} />
            <Route path="/notificacao" element={<Notificacao />} />
            <Route path="/chat" element={<Chat />} />
            <Route
              path="/detalhes-demanda"
              element={<DetalhesDemandaPagina />}
            />
            <Route path="/editar-escopo" element={<EditarEscopo />} />
            <Route path="escopos" element={<Escopos />} />
            <Route path="detalhes-ata" element={<DetalhesAta />} />
            <Route path="detalhes-pauta" element={<DetalhesPauta />} />
            <Route path="*" element={<NotFoundPage />} />
          </Routes>
        </Router>
      </UserContext.Provider>
    </ToggleColorMode>
  );
};

const ProtectedRoute = ({ user, children }) => {
  if (!user) {
    return <Navigate to="/login" />;
  }

  return children;
};

export default App;
