import { useEffect } from "react";
import { createContext } from "react";
import { useState } from "react";
import SockJS from "sockjs-client";
import * as Stomp from "stompjs";

export const WebSocketContext = createContext();

export const WebSocketService = ({children}) => {
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const conectar = () => {
      const socket = new SockJS("http://localhost:8443/ws");
      const stomp = Stomp.over(socket);
      stomp.debug = null;
      
      stomp.connect(
        {},
        () => {
          setStompClient(stomp);
        },
        (error) => {
          console.log("Erro ao conectar: ", error);
          setTimeout(() => {
            console.log("Tentando reconectar...");
            conectar();
          }, 5000);
        }
      );
    };
    conectar();
  }, []);

  const desconectar = () => {
    if (stompClient) {
      stompClient.disconnect();
    }
  };

  const enviar = (destino, mensagem) => {
    console.log("destino do enviar: ", destino)
    if (stompClient) {
      stompClient.send(destino, {}, JSON.stringify(mensagem));
    } else {
      console.log("Conexão não estabelecida!");
    }
  };

  const inscrever = (caminho, acao) => {
    if (stompClient) {
      return stompClient.subscribe(caminho, acao);
    }
  };

  return (
    <WebSocketContext.Provider
      value={{ stompClient, desconectar, enviar, inscrever }}
    >
        {children}
    </WebSocketContext.Provider>
  );
};
