import React, { useContext, useState } from "react";
import {
  Drawer,
  Button,
  ListItem,
  ListSubheader,
  ListItemIcon,
  ListItemText,
  ListItemButton,
  Collapse,
  List,
  Divider,
  Box,
  Typography,
} from "@mui/material";

import InboxIcon from "@mui/icons-material/MoveToInbox";
import DraftsIcon from "@mui/icons-material/Drafts";
import SendIcon from "@mui/icons-material/Send";
import ExpandLess from "@mui/icons-material/ExpandLess";
import ExpandMore from "@mui/icons-material/ExpandMore";
import StarBorder from "@mui/icons-material/StarBorder";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import ItemTest from "./itemTest";

export default function TemporaryDrawer() {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  const [open, setOpen] = React.useState(true);

  const [state, setState] = useState({
    right: false,
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (
      event.type === "keydown" &&
      (event.key === "Tab" || event.key === "Shift")
    ) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  const opcoesFiltrar = [
    {
      id: 1 ,tipo: "Score",
    },
    {
      id: 2, tipo: "Data",
    },
    {
      id: 3, tipo: "Nome",
    },
  ];

  const list = (anchor) => (
    <List
      sx={{ width: "12rem", height: "80%", bgcolor: "background.paper" }}
      component="nav"
      aria-labelledby="nested-list-subheader"
    >
      <Box className="w-full flex items-center justify-center">
        <Typography
          fontSize={FontConfig.smallTitle}
          sx={{ color: "primary.main", fontWeight: 600 }}
        >
          Ordenar
        </Typography>
      </Box>
      <Box className="h-full">
        {opcoesFiltrar.map((opcao, index) => (
          <>
            <ItemTest opcao={opcao} key={index} />
          </>
        ))}
      </Box>
    </List>
  );

  return (
    <div>
      <React.Fragment key="right">
        <Button onClick={toggleDrawer("right", true)}>{"right"}</Button>
        <Drawer
          anchor={"right"}
          open={state["right"]}
          onClose={toggleDrawer("right", false)}
        >
          {list("right")}
        </Drawer>
      </React.Fragment>
    </div>
  );
}
