import React, { useState, useContext, useEffect } from "react";
import { TableRow, Box, TextareaAutosize } from "@mui/material";

import FontConfig from "../../service/FontConfig";

import ColorModeContext from "../../service/TemaContext";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

const LinhaTabelaCCs = (props) => {
  const [corFundoTextArea, setCorFundoTextArea] = useState("#FFFF");
  const { mode } = useContext(ColorModeContext);

  useEffect(() => {
    if (mode === "dark") {
      setCorFundoTextArea("#212121");
    } else {
      setCorFundoTextArea("#FFFF");
    }
  }, [mode]);

  <TableRow>
    <td align="center" className="pb-5 relative">
      <Box className="flex w-full justify-end absolute" sx={{ width: "98%" }}>
        <DeleteOutlineOutlinedIcon
          fontSize="medium"
          className="mt-1 delay-120 hover:scale-110 duration-300"
          titleAccess="Excluir linha"
          sx={{ color: "icon.main", cursor: "pointer" }}
        />
      </Box>
      <TextareaAutosize
        style={{
          width: "95%",
          resize: "none",
          textAlign: "center",
          backgroundColor: corFundoTextArea,
          marginTop: "2rem",
        }}
        fontSize={FontConfig.medium}
        className="flex outline-none border-solid border px-1 py-1.5 drop-shadow-sm rounded"
        placeholder="Digite os CCs..."
        value={props.dados.ccs[props.index].codigo}
        // onChange={(e) => {
        //   props.setCustos(
        //     props.dados.ccs.map((cc, index) => {
        //       if (index === props.index) {
        //         return {
        //           ...cc,
        //           codigo: e.target.value,
        //         };
        //       }
        //       return cc;
        //     })
        //   );
        // }}
      />
    </td>
  </TableRow>;
};

export default LinhaTabelaCCs;
