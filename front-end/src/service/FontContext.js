import { createContext } from 'react';

const FontContext = createContext({
    FontConfig: {
        verySmall: "10px",
        small: "12px",
        default: "14px",
        medium: "16px",
        big: "18px",
        veryBig: "20px",
        smallTitle: "30px",
        title: "36px"
    },
    setFontConfig: () => { }
});

export default FontContext;