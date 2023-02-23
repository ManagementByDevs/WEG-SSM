import { createContext } from 'react';

const ChatContext = createContext({
    usuarioId: 0,
    visibilidade: false,
    setVisibilidade: () => {},
});

export default ChatContext;