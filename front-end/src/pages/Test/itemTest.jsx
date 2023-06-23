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

export default function ItemTest(props) {

  const [open, setOpen] = useState(true);

  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <>
      <ListItemButton onClick={handleClick}>
        <ListItemIcon>
          <InboxIcon />
        </ListItemIcon>
        <ListItemText primary={props.opcao.tipo} />
        {open ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Divider/>
      <Collapse in={open} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <StarBorder />
            </ListItemIcon>
            <ListItemText primary="Starred" />
          </ListItemButton>
          <Divider/>
        </List>
      </Collapse>
    </>
  );
}
