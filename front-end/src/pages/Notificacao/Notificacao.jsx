import React, { useState, useEffect } from "react";
import {
  Box,
  Typography,
  Button,
  Divider,
  Table,
  TableBody,
  TableHead,
  TableRow,
  Paper,
  Checkbox,
  IconButton,
  Tooltip,
} from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import FontConfig from "../../service/FontConfig";
import NotificacaoComponente from "../../components/NotificacaoComponente/NotificacaoComponente";

import ModalFiltro from "../../components/ModalFiltro/ModalFiltro";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MarkEmailReadOutlinedIcon from "@mui/icons-material/MarkEmailReadOutlined";
import MarkEmailUnreadOutlinedIcon from "@mui/icons-material/MarkEmailUnreadOutlined";
import HighlightOffOutlinedIcon from "@mui/icons-material/HighlightOffOutlined";
import ErrorOutlineOutlinedIcon from "@mui/icons-material/ErrorOutlineOutlined";

const Notificacao = () => {
  const [abrirFiltro, setOpenFiltro] = useState(false);

  const abrirModalFiltro = () => {
    setOpenFiltro(true);
  };

  const createRow = (title, date, visualizado, tipo_icone) => {
    return { checked: false, title, date, visualizado, tipo_icone };
  };

  const [rows, setRows] = useState([
    createRow(
      "Frozen yoghurt Lorem ipsum dolor sit, amet consectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit quam amet enim odio sint. Iure beatae alias assumenda neque quod!",
      "10/10/20 - 18:23",
      false,
      1
    ),
    createRow("Eclair", "10/10/20 - 18:23", false, 2),
    createRow("Ice cream sandwich", "10/10/20 - 18:23", true, 1),
    createRow("Cupcake", "10/10/20 - 18:23", true, 1),
    createRow("Gingerbread", "10/10/20 - 18:23", true, 2),
  ]);

  const onSelectRowClick = (event, index) => {
    let aux = [...rows];
    aux[index].checked = event.target.checked;

    setRows(aux);
  };

  const onSelectAllClick = (isSelect) => {
    let aux = [...rows];
    aux.forEach((row) => {
      row.checked = isSelect;
    });
    setRows(aux);
  };

  useEffect(() => {
    console.log("rows", rows);
  }, [rows]);

  const onReadOrUnreadClick = () => {
    let listWithCheckeds = [...rows.filter((row) => row.checked)];
    let bool = listWithCheckeds.every((row) => row.visualizado);

    let newList = rows.map((row) => {
      if (row.checked) {
        row.visualizado = !bool;
        row.checked = false;
      }
      return row;
    });
    setRows(newList);
  };

  const deleteRow = () => {
    let aux = rows.filter((row) => {
      return !row.checked;
    });
    setRows(aux);
  };

  return (
    <FundoComHeader>
      <Box className="p-2">
        <Caminho />
        <Box className="w-full flex flex-col items-center">
          <Box className="w-full flex justify-center m-2">
            <Typography
              fontSize={FontConfig.title}
              color={"icon.main"}
              sx={{ fontWeight: "600", cursor: "default" }}
            >
              Notificações
            </Typography>
          </Box>
          <Box className="w-10/12">
            <Divider sx={{ borderColor: "tertiary.main" }} />
          </Box>
          <Box className="w-full flex justify-center">
            <Box
              className="w-10/12 h-10 flex justify-between "
              color={"icon.main"}
              sx={{ margin: "5px" }}
            >
              {rows.find((row) => row.checked) ? (
                <Box className="w-1/12 flex justify-center">
                  <Tooltip title="Deletar">
                    <IconButton onClick={deleteRow}>
                      <DeleteOutlineOutlinedIcon
                        sx={{ color: "primary.main" }}
                      />
                    </IconButton>
                  </Tooltip>
                  <Tooltip title="Marcar como lido">
                    <IconButton onClick={onReadOrUnreadClick}>
                      {rows.every((row) => row.visualizado) ? (
                        <MarkEmailUnreadOutlinedIcon
                          sx={{ color: "primary.main" }}
                        />
                      ) : (
                        <MarkEmailReadOutlinedIcon
                          sx={{ color: "primary.main" }}
                        />
                      )}
                    </IconButton>
                  </Tooltip>
                </Box>
              ) : (
                <Box />
              )}
              <Button
                onClick={abrirModalFiltro}
                sx={{
                  backgroundColor: "primary.main",
                  color: "text.white",
                  fontSize: FontConfig.default,
                }}
                variant="contained"
                disableElevation
              >
                <Typography fontSize={FontConfig.medium}>Filtrar</Typography>
                <FilterAltOutlinedIcon />
              </Button>
            </Box>
            {abrirFiltro && (
              <ModalFiltro
                open={abrirFiltro}
                setOpen={setOpenFiltro}
                filtroDemanda={false}
              />
            )}
          </Box>
          <Box className="w-10/12 flex justify-center">
            <Paper sx={{ width: "100%" }} square>
              <Table sx={{ width: "100%" }}>
                <TableHead>
                  <TableRow sx={{ backgroundColor: "primary.main" }}>
                    <th className="w-1/12">
                      <Checkbox
                        sx={{
                          color: "white",
                          "&.Mui-checked": {
                            color: "white",
                          },
                        }}
                        checked={rows.every((row) => row.checked)}
                        onChange={(e) => {
                          onSelectAllClick(e.target.checked);
                        }}
                      />
                    </th>
                    <th className="text-white">Título</th>
                    <th className="text-white">Tipo</th>
                    <th className="w-1/10 text-white">Data</th>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rows.map((row, index) => (
                    <TableRow
                      className="drop-shadow-lg"
                      selected={!row.visualizado}
                      hover
                      key={index}
                      sx={{
                        "&:last-child td, &:last-child th": { border: 0 },
                      }}
                      onClick={(e) => {
                        console.log("oi");
                      }}
                    >
                      <td className="text-center">
                        <Checkbox
                          checked={row.checked}
                          onChange={(e) => {
                            onSelectRowClick(e, index);
                          }}
                        />
                      </td>
                      <td className="text-left">
                        <Typography fontSize={FontConfig.medium}>
                          {row.title}
                        </Typography>
                      </td>
                      <td className="text-center">
                        {row.tipo_icone == 1 ? (
                          <HighlightOffOutlinedIcon
                            color="primary"
                            sx={{ fontSize: "30px", marginX: "0.5rem" }}
                          />
                        ) : row.tipo_icone == 2 ? (
                          <ErrorOutlineOutlinedIcon
                            color="primary"
                            sx={{ fontSize: "30px", marginX: "0.5rem" }}
                          />
                        ) : row.tipo_icone == 3 ? (
                          <ErrorOutlineOutlinedIcon
                            color="primary"
                            sx={{ fontSize: "30px", marginX: "0.5rem" }}
                          />
                        ) : (
                          <ErrorOutlineOutlinedIcon
                            color="primary"
                            sx={{ fontSize: "30px", marginX: "0.5rem" }}
                          />
                        )}
                      </td>
                      <td className="text-center">
                        <Typography fontSize={FontConfig.default}>
                          {row.date}
                        </Typography>
                      </td>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </Paper>
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Notificacao;
