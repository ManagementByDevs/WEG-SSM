import "./App.css";
import React, { useMemo, useState, useContext } from "react";
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
import SpeechSynthesisContext from "./service/SpeechSynthesisContext";
import { WebSocketService } from "./service/WebSocketService";
import { SpeechRecognitionService } from "./service/SpeechRecognitionService";

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

  const [chatMinimizado, setChatMinimizado] = useState(false);

  const [chatId, setChatId] = useState(0);

  const [lendoTexto, setLendoTexto] = useState(false);

  /** Função que irá setar o texto que será "lido" pela a API */
  const lerTexto = (escrita) => {
    if (lendoTexto) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (lendoTexto && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

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

  const speechSynthesis = useMemo(
    () => ({ lendoTexto, setLendoTexto, lerTexto }),
    [lendoTexto]
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
            <WebSocketService>
              <SpeechRecognitionService>
                <SpeechSynthesisContext.Provider value={speechSynthesis}>
                  <Router>
                    <LerTexto />
                    <VLibras forceOnload />
                    <Routes>
                      <Route path="/login" element={<Login />} />
                      <Route element={<ProtectedRoute />}>
                        <Route
                          path="/criar-demanda"
                          element={<CriarDemanda />}
                        />
                        <Route path="/notificacao" element={<Notificacao />} />
                        <Route path="/chat" element={<Chat />} />
                        <Route path="/chat/:id" element={<Chat />} />
                        <Route
                          path="/detalhes-demanda"
                          element={<DetalhesDemandaPagina />}
                        />
                        <Route
                          path="/editar-escopo"
                          element={<EditarEscopo />}
                        />
                        <Route path="/escopos" element={<Escopos />} />
                        <Route path="*" element={<NotFound />} />
                        {/* <Route path="/test" element={<Test />} /> */}
                      </Route>
                      <Route path="/editar-escopo" element={<EditarEscopo />} />
                      <Route path="/escopos" element={<Escopos />} />
                      <Route path="*" element={<NotFound />} />
                      {/* <Route path="/test" element={<Test />} /> */}
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
                            tiposUsuarioAllowed={[
                              "ANALISTA",
                              "GERENTE",
                              "GESTOR",
                            ]}
                            redirectPath="/"
                          >
                            <CriarProposta />
                          </ProtectedRoute>
                        }
                      />
                      <Route
                        path="/detalhes-proposta/:id"
                        element={
                          <ProtectedRoute
                            tiposUsuarioAllowed={[
                              "ANALISTA",
                              "GERENTE",
                              "GESTOR",
                            ]}
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
                            tiposUsuarioAllowed={[
                              "ANALISTA",
                              "GERENTE",
                              "GESTOR",
                            ]}
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
                            tiposUsuarioAllowed={[
                              "ANALISTA",
                              "GERENTE",
                              "GESTOR",
                            ]}
                            redirectPath="/"
                          >
                            <DetalhesPauta />
                          </ProtectedRoute>
                        }
                      />
                    </Routes>
                  </Router>
                </SpeechSynthesisContext.Provider>
              </SpeechRecognitionService>
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

const DetermineHomeUser = () => {
  const userJpa = CookieService.getCookie("user");

  if (userJpa.authorities[0].authority == "SOLICITANTE") {
    return <Home />;
  } else {
    return <HomeGerencia />;
  }
};

export default App;
