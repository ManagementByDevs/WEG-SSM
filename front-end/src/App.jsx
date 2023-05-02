import "./App.css";
import React, { useMemo, useState, useEffect } from "react";
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
import Test from "./pages/Test/test";

import ToggleColorMode from "./service/TemaProvedor";
import TextLanguage from "./service/TextLanguage";

import FontContext from "./service/FontContext";
import TextLanguageContext from "./service/TextLanguageContext";
import ChatContext from "./service/ChatContext";
import { WebSocketService } from "./service/WebSocketService";

import UsuarioService from "./service/usuarioService";
import CookieService from "./service/cookieService";

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

  const [chatMinimizado, setChatMinimizado] = useState(false);

  const miniChat = useMemo(
    () => ({
      usuarioId: 0,
      visibilidade: chatMinimizado,
      setVisibilidade: setChatMinimizado,
    }),
    [chatMinimizado]
  );

  /*
  Tipos possíveis de usuários:
  [SOLICITANTE, ANALISTA, GERENTE, GETOR]
  */
  return (
    <ToggleColorMode>
      <FontContext.Provider value={fontSize}>
        <TextLanguageContext.Provider value={textLanguage}>
          <ChatContext.Provider value={miniChat}>
            <Router>
              <Routes>
                <Route path="/login" element={<Login />} />
                <Route element={<ProtectedRoute />}>
                  <Route path="/criar-demanda" element={<CriarDemanda />} />
                  <Route path="/notificacao" element={<Notificacao />} />
                  <Route path="/chat" element={<WebSocketService><Chat /></WebSocketService>} />
                  <Route path="/chat/:id" element={<WebSocketService><Chat /></WebSocketService>} />
                  <Route
                    path="/detalhes-demanda"
                    element={<DetalhesDemandaPagina />}
                  />
                  <Route path="/editar-escopo" element={<EditarEscopo />} />
                  <Route path="/escopos" element={<Escopos />} />
                  <Route path="*" element={<NotFound />} />
                  <Route path="/test" element={<Test />} />
                </Route>
                <Route path="/editar-escopo" element={<EditarEscopo />} />
                <Route path="/escopos" element={<Escopos />} />
                <Route path="*" element={<NotFound />} />
                <Route path="/test" element={<Test />} />
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
                      redirectPath="/home-gerencia"
                    >
                      <CriarProposta />
                    </ProtectedRoute>
                  }
                />
                <Route
                  path="/detalhes-proposta/:id"
                  element={
                    <ProtectedRoute
                      tiposUsuarioAllowed={["ANALISTA", "GERENTE", "GESTOR"]}
                      redirectPath="/home-gerencia"
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
                      redirectPath="/home-gerencia"
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
                      redirectPath="/home-gerencia"
                    >
                      <DetalhesPauta />
                    </ProtectedRoute>
                  }
                />
              </Routes>
            </Router>
          </ChatContext.Provider>
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

  const cookie = CookieService.getCookie("jwt");
  const userJpa = CookieService.getCookie("user");

  if (cookie != null && cookie.exp > Math.floor(Date.now() / 1000) &&
    (tiposUsuarioAllowed.includes(userJpa.authorities[0].authority) || tiposUsuarioAllowed == "")) {
    return children ? children : <Outlet />;
  } else {
    return <Navigate to={redirectPath} replace />;
  }
};

const DetermineHomeUser = () => {
  const userJpa = CookieService.getCookie("user");

  if (userJpa.authorities[0].authority == "SOLICITANTE") {
    return <Home />;
  } else {
    return <HomeGerencia />;
  }
};

export default App;
