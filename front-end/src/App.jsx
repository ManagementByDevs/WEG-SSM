import "./App.css";
import React, { useMemo, useState } from "react";
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
import CriarProposta from "./pages/CriarProposta/CriarProposta";
import EditarEscopo from "./pages/EditarEscopo/EditarEscopo";
import NotFound from "./pages/NotFound/NotFound";
import DetalhesPropostaPagina from "./pages/DetalhesPropostaPagina/DetalhesPropostaPagina";

import ToggleColorMode from "./service/TemaProvedor";
import TextLanguage from "./service/TextLanguage";

import FontContext from "./service/FontContext";
import TextLanguageContext from "./service/TextLanguageContext";

const App = () => {
  const [FontConfig, setFontConfig] = useState({
    verySmall: "10px",
    small: "12px",
    default: "14px",
    medium: "16px",
    big: "18px",
    veryBig: "20px",
    smallTitle: "30px",
    title: "36px",
  });

  const [Texts, setTexts] = useState(TextLanguage("pt"));

  const fontSize = useMemo(
    () => ({
      FontConfig,
      setFontConfig,
    }),
    [FontConfig]
  );

  const textLanguage = useMemo(
    () => ({
      texts: Texts,
      setTexts: setTexts,
    }),
    [Texts]
  );

  /*
  Tipos possíveis de usuários:
  [SOLICITANTE, ANALISTA, GERENTE, GETOR]
  */
  return (
    <ToggleColorMode>
      <FontContext.Provider value={fontSize}>
        <TextLanguageContext.Provider value={textLanguage}>
          <Router>
            <Routes>
              <Route path="/login" element={<Login />} />
              <Route element={<ProtectedRoute />}>
                <Route path="/criar-demanda" element={<CriarDemanda />} />
                <Route path="/notificacao" element={<Notificacao />} />
                <Route path="/chat" element={<Chat />} />
                <Route
                  path="/detalhes-demanda"
                  element={<DetalhesDemandaPagina />}
                />
                <Route path="/editar-escopo" element={<EditarEscopo />} />
                <Route path="escopos" element={<Escopos />} />
                <Route path="*" element={<NotFound />} />
              </Route>
              <Route
                path="/"
                element={
                  <ProtectedRoute>
                    <DetermineHomeUser />
                  </ProtectedRoute>
                }
              />
              <Route
                path="/criar-proposta"
                element={
                  <ProtectedRoute
                    tiposUsuarioAllowed={["ANALISTA", "GERENTE", "GESTOR"]}
                    redirectPath="/"
                  >
                    <CriarProposta />
                  </ProtectedRoute>
                }
              />
              <Route
                path="/detalhes-proposta"
                element={
                  <ProtectedRoute
                    tiposUsuarioAllowed={["ANALISTA", "GERENTE", "GESTOR"]}
                    redirectPath="/"
                  >
                    <DetalhesPropostaPagina />
                  </ProtectedRoute>
                }
              />
              <Route
                path="detalhes-ata"
                element={
                  <ProtectedRoute
                    tiposUsuarioAllowed={["ANALISTA", "GERENTE", "GESTOR"]}
                    redirectPath="/"
                  >
                    <DetalhesAta />
                  </ProtectedRoute>
                }
              />
              <Route
                path="detalhes-pauta"
                element={
                  <ProtectedRoute
                    tiposUsuarioAllowed={["ANALISTA", "GERENTE", "GESTOR"]}
                    redirectPath="/"
                  >
                    <DetalhesPauta />
                  </ProtectedRoute>
                }
              />
            </Routes>
          </Router>
        </TextLanguageContext.Provider>
      </FontContext.Provider>
    </ToggleColorMode>
  );
};

const ProtectedRoute = ({
  tiposUsuarioAllowed = "",
  children,
  redirectPath = "/login",
}) => {
  // Quando formos retirar o user do localstorage,
  // lembrar de deletar no Login.jsx a linha de adicionar o user no localstorage na função login;
  // Lembrar de deletar no UserModal.jsx a linha de deletar o user do localstorage na função sair;
  const [user, setUser] = useState(
    localStorage.getItem("user")
      ? JSON.parse(localStorage.getItem("user"))
      : null
  );

  // Caso não tenha usuário logado ou o usuário não possua um tipo que possa acessar tal
  // página, redireciona para o redirectPath (Valor padrão é login)
  if (
    !user ||
    (tiposUsuarioAllowed && !tiposUsuarioAllowed.includes(user.tipoUsuario))
  ) {
    return <Navigate to={redirectPath} replace />;
  }

  // Caso o componente esteja sendo usado como Layout Route, não possuirá um children, retornando o Outlet
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
