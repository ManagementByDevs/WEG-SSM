import { createContext } from "react";

const ChatContext = createContext({
  visibilidade: false,
  idChat: 0,
  setVisibilidade: () => {},
  setIdChat: () => {},
});

export default ChatContext;
