import "./App.css";
import React, { useEffect, useMemo, useState } from "react";
import { Box } from "@mui/material";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
  Outlet,
} from "react-router-dom";

import VLibras from "@djpfs/react-vlibras";

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
import LerTexto from "./components/LerTexto/LerTexto";

import ToggleColorMode from "./service/TemaProvedor";
import TextLanguage from "./service/TextLanguage";

import FontContext from "./service/FontContext";
import TextLanguageContext from "./service/TextLanguageContext";
import ChatContext from "./service/ChatContext";
import CookieService from "./service/cookieService";
import { WebSocketService } from "./service/WebSocketService";

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

  const [chatId, setChatId] = useState(0);

  const miniChat = useMemo(
    () => ({
      usuarioId: 0,
      idChat: chatId,
      visibilidade: chatMinimizado,
      setVisibilidade: setChatMinimizado,
      setIdChat: setChatId,
    }),
    [chatMinimizado]
  );

  const [lendoTexto, setLendoTexto] = useState(false);

  /*
  Tipos possíveis de usuários:
  [SOLICITANTE, ANALISTA, GERENTE, GETOR]
  */
  return (
    <ToggleColorMode>
      <FontContext.Provider value={fontSize}>
        <TextLanguageContext.Provider value={textLanguage}>
          <ChatContext.Provider value={miniChat}>
            <WebSocketService>
              <Router>
                <LerTexto
                  setLendo={setLendoTexto}
                  lendo={lendoTexto}
                />
                <VLibras forceOnload />
                <Routes>
                  <Route
                    path="/login"
                    element={
                      <Login
                        lendo={lendoTexto}
                      />
                    }
                  />
                  <Route element={<ProtectedRoute />}>
                    <Route
                      path="/criar-demanda"
                      element={
                        <CriarDemanda
                          lendo={lendoTexto}
                        />
                      }
                    />
                    <Route
                      path="/notificacao"
                      element={
                        <Notificacao
                          lendo={lendoTexto}
                        />
                      }
                    />
                    <Route
                      path="/chat"
                      element={
                        <Chat
                          lendo={lendoTexto}
                        />
                      }
                    />
                    <Route
                      path="/chat/:id"
                      element={
                        <Chat
                          lendo={lendoTexto}
                        />
                      }
                    />
                    <Route
                      path="/detalhes-demanda"
                      element={
                        <DetalhesDemandaPagina
                          lendo={lendoTexto}
                        />
                      }
                    />
                    <Route
                      path="/editar-escopo"
                      element={
                        <EditarEscopo
                          lendo={lendoTexto}
                        />
                      }
                    />
                    <Route
                      path="/escopos"
                      element={
                        <Escopos
                          lendo={lendoTexto}
                        />
                      }
                    />
                    <Route
                      path="*"
                      element={
                        <NotFound
                          lendo={lendoTexto}
                        />
                      }
                    />
                    {/* <Route path="/test" element={<Test />} /> */}
                  </Route>
                  <Route
                    path="/editar-escopo"
                    element={
                      <EditarEscopo
                        lendo={lendoTexto}
                      />
                    }
                  />
                  <Route
                    path="/escopos"
                    element={
                      <Escopos
                        lendo={lendoTexto}
                      />
                    }
                  />
                  <Route
                    path="*"
                    element={
                      <NotFound
                        lendo={lendoTexto}
                      />
                    }
                  />
                  {/* <Route path="/test" element={<Test />} /> */}
                  <Route
                    path="/"
                    element={
                      <ProtectedRoute>
                        <DetermineHomeUser
                          lendo={lendoTexto}
                        />
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
                        <CriarProposta
                          lendo={lendoTexto}
                        />
                      </ProtectedRoute>
                    }
                  />
                  <Route
                    path="/detalhes-proposta/:id"
                    element={
                      <ProtectedRoute
                        tiposUsuarioAllowed={["ANALISTA", "GERENTE", "GESTOR"]}
                        redirectPath="/"
                      >
                        <DetalhesPropostaPagina
                          lendo={lendoTexto}
                        />
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
                        <DetalhesAta
                          lendo={lendoTexto}
                        />
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
                        <DetalhesPauta
                          lendo={lendoTexto}
                        />
                      </ProtectedRoute>
                    }
                  />
                </Routes>
              </Router>
            </WebSocketService>
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

  if (
    cookie != null &&
    cookie.exp > Math.floor(Date.now() / 1000) &&
    (tiposUsuarioAllowed.includes(userJpa.authorities[0].authority) ||
      tiposUsuarioAllowed == "")
  ) {
    return children ? children : <Outlet />;
  } else {
    return <Navigate to={redirectPath} replace />;
  }
};

const DetermineHomeUser = (props) => {
  const userJpa = CookieService.getCookie("user");

  if (userJpa.authorities[0].authority == "SOLICITANTE") {
    return (
      <Home lendo={props.lendo} />
    );
  } else {
    return (
      <HomeGerencia
        lendo={props.lendo}
      />
    );
  }
};

export default App;
