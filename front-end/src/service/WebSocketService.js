import { useEffect } from "react";
import { createContext } from "react";
import { useState } from "react";
import SockJS from "sockjs-client";
import * as Stomp from "stompjs";

export const WebSocketContext = createContext();

export const WebSocketService = ({ children }) => {
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const conectar = () => {
      const socket = new SockJS("http://localhost:8443/ws");
      const stomp = Stomp.over(socket);
      // stomp.debug = null;

      stomp.connect(
        {},
        () => {
          setStompClient(stomp);
        },
        (error) => {
          setTimeout(() => {
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
    if (stompClient) {
      stompClient.send(destino, {}, mensagem);
    }
  };

  const inscrever = (caminho, acao) => {
    if (stompClient) {
      // console.log("Conexões: ", stompClient.subscriptions);
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
