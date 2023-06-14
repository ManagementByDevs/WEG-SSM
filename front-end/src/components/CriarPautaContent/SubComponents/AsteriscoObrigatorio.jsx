import React, { useContext } from "react";

import FontContext from "../../../service/FontContext";
import { Typography } from "@mui/material";

const AsteriscoObrigatorio = () => {
  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  return (
    <Typography
      component="span"
      fontSize={FontConfig.medium}
      fontWeight={700}
      className="text-red-600"
    >
      *
    </Typography>
  );
};

export default AsteriscoObrigatorio;
