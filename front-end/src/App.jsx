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

import ToggleColorMode from "./service/TemaProvedor";
import TextLanguage from "./service/TextLanguage";

import FontContext from "./service/FontContext";
import TextLanguageContext from "./service/TextLanguageContext";
import ChatContext from "./service/ChatContext";

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
                  <Route path="/chat" element={<Chat />} />
                  <Route
                    path="/detalhes-demanda"
                    element={<DetalhesDemandaPagina />}
                  />
                  <Route path="/editar-escopo" element={<EditarEscopo />} />
                  <Route path="/escopos" element={<Escopos />} />
                  <Route path="*" element={<NotFound />} />
                </Route>
                <Route
                  path="/"
                  element={
                    <ProtectedRoute>
                      {DetermineHomeUser()}
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

  const cookie = CookieService.getCookie();
  if (cookie != null) {
    UsuarioService.getUsuarioByEmail(cookie.sub)
      .then((user) => {
        return children ? children : <Outlet />;
      })
      .catch((error) => {
        console.error("Failed to load user data: ", error);
      });
  } else {
    return <Navigate to={redirectPath} replace />;
  }
};

const DetermineHomeUser = () => {

  const user = returnUsuario();
  console.log(user);
  if (user != null) {
    if (user.tipoUsuario == "SOLICITANTE") {
      return <Home />
    } else {
      return <HomeGerencia />
    }
  } else {
    return null;
  }
};

const returnUsuario = async () => {
  const cookie = CookieService.getCookie();
  if (cookie != null) {
    return await UsuarioService.getUsuarioByEmail(cookie.sub);
  } else {
    return null;
  }
}

export default App;
