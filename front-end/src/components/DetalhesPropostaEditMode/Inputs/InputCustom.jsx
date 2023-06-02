import React, { useContext, useState } from "react";

import { Input, InputAdornment, Tooltip } from "@mui/material";

import FontContext from "../../../service/FontContext";
import TextLanguageContext from "../../../service/TextLanguageContext";

const InputCustom = ({
  defaultText = "",
  saveProposal = () => {},
  microfone = null,
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  const [text, setText] = useState(defaultText);

  return (
    <Input
      size="small"
      value={text}
      onChange={(e) => setText(e.target.value)}
      onBlur={() => saveProposal({ target: { value: text } })}
      type="text"
      fullWidth
      sx={{ color: "primary.main", fontSize: FontConfig.smallTitle }}
      multiline
      endAdornment={microfone}
    />
  );
};

export default InputCustom;
