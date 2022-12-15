import "./App.css";
import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
  Outlet,
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

const App = () => {

  return (
    <ToggleColorMode>
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route element={<ProtectedRoute />}>
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
          </Route>
          <Route
            path="/"
            element={
              <ProtectedRoute>
                <DetermineHomeUser />
              </ProtectedRoute>
            }
          />
        </Routes>
      </Router>
    </ToggleColorMode>
  );
};

const ProtectedRoute = ({
  tipoUsuario = "",
  children,
  redirectPath = "/login",
}) => {
  const [user, setUser] = useState(
    localStorage.getItem("user")
      ? JSON.parse(localStorage.getItem("user"))
      : null
  );

  useEffect(() => {
    console.log("user:", user);
  }, [user]);

  if (!user || (tipoUsuario && !tipoUsuario.includes(user.tipoUsuario))) {
    return <Navigate to={redirectPath} replace />;
  }

  return children ? children : <Outlet />;
};

const DetermineHomeUser = () => {
  const [user, setUser] = useState(
    localStorage.getItem("user")
      ? JSON.parse(localStorage.getItem("user"))
      : null
  );

  if (user.tipoUsuario === "SOLICITANTE") {
    return <Home />;
  } else {
    return <HomeGerencia />;
  }
};

export default App;
