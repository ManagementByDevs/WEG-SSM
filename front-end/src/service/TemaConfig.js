import { ptBR } from '@mui/material/locale';

const getDesignTokens = (mode) => ({
    palette: {
        mode,
        ...(mode == 'dark')
            ? {
                primary: {
                    main: '#00579D',
                },
                secondary: {
                    main: '#3D3F45',
                },
                tertiary: {
                    main: '#FFFFFF',
                },
                link: {
                    main: '#FFFFFF',
                },
                divider: { main: '#3D3F45' },
                text: {
                    primary: '#FFFFFF',
                    secondary: '#BDBEC0',
                    white: '#FFFFFF'
                },
                background: {
                    default: '#22252C',
                    paper: '#22252C'
                },
                component: {
                    main: '#3D3F45'
                },
                input: {
                    main: '#22252C'
                },
                icon: {
                    main: '#FFFFFF'
                },
                visualizado: {
                    true: '#22252C',
                    false: '#2E2E2E'
                },
                chat: {
                    outro: "#2E2E2E",
                    eu: "#2382BA"
                },
                hover: {
                    main: '#2E2E2E'
                }
            }
            : {
                primary: {
                    main: '#00579D',
                },
                secondary: {
                    main: '#FFFFFF',
                },
                tertiary: {
                    main: '#000000',
                },
                link: {
                    main: '#00579D',
                },
                divider: { main: 'rgba(0, 0, 0, 0.3)' },
                text: {
                    primary: '#000000',
                    secondary: '#535353',
                    white: '#FFFFFF'
                },
                background: {
                    default: '#FFFFFF',
                    paper: '#FFFFFF'
                },
                component: {
                    main: '#EFEFEF'
                },
                input: {
                    main: '#F8F8F8'
                },
                icon: {
                    main: '#00579D'
                },
                visualizado: {
                    true: '#E4E4E4',
                    false: '#FFFFFF'
                },
                chat: {
                    outro: "#E4E4E4",
                    eu: "#6AACDA"
                },
                hover: {
                    main: '#EBEBEB',
                }
            }
    },
    typography: {
        fontFamily: "Inter, sans-serif",
        button: {
            textTransform: 'none',
        }
    },
    ptBR
});

export default getDesignTokens;