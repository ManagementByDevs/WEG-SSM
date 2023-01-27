import React, { useState, useEffect, useContext } from "react";
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

import "./notificacaoStyle.css";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";

import FontConfig from "../../service/FontConfig";
import NotificacaoService from "../../service/notificacaoService";

import ModalFiltro from "../../components/ModalFiltro/ModalFiltro";
import FilterAltOutlinedIcon from "@mui/icons-material/FilterAltOutlined";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MarkEmailReadOutlinedIcon from "@mui/icons-material/MarkEmailReadOutlined";
import MarkEmailUnreadOutlinedIcon from "@mui/icons-material/MarkEmailUnreadOutlined";
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import ErrorOutlineOutlinedIcon from "@mui/icons-material/ErrorOutlineOutlined";
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';

import FontContext from "../../service/FontContext";

const Notificacao = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  // Modal de filtro
  const [abrirFiltro, setOpenFiltro] = useState(false);
  // Modal de confirmação de exclusão individual
  const [openModalConfirmDelete, setOpenModalConfirmDelete] = useState(false);
  // Modal de confirmação de exclusão múltipla
  const [openModalConfirmMultiDelete, setOpenModalConfirmMultiDelete] =
    useState(false);

  // UseState para saber qual notificação deletar ao usar o botão de delete individual
  const [indexDelete, setIndexDelete] = useState(null);

  // Cria uma linha da tabela retornando um objeto
  const createRow = (title, date, visualizado, tipo_icone) => {
    return { checked: false, title, date, visualizado, tipo_icone };
  };

  // Linhas da tabela
  const [rows, setRows] = useState([
    createRow(
      "Frozen yoghurt Lorem ipsum dolor sit, amet consectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit qusectetur adipisicing elit. Odit earum sapiente optio atque totam unde, reiciendis soluta qui velit quam amet enim odio sint. Iure beatae alias assumenda neque quod!",
      "10/10/20 - 18:23",
      false,
      1
    ),
    createRow("Eclair", "10/10/20 - 18:23", false, 2),
    createRow("Ice cream sandwich", "10/10/20 - 18:23", true, 3),
    createRow("Cupcake", "10/10/20 - 18:23", true, 4),
    createRow("Gingerbread", "10/10/20 - 18:23", true, 2),
  ]);

  // Handler que atualiza o estado do modal de filtro
  const abrirModalFiltro = () => {
    setOpenFiltro(true);
  };

  // Atualiza o estado da linha ao clicar no checkbox
  const onSelectRowClick = (event, index) => {
    let aux = [...rows];
    aux[index].checked = event.target.checked;

    setRows(aux);
  };

  // Atualiza o estado de todas as linhas ao clicar no checkbox de seleção de todas as linhas da tabela
  const onSelectAllClick = (isSelect) => {
    let aux = [...rows];
    aux.forEach((row) => {
      row.checked = isSelect;
    });
    setRows(aux);
  };

  // Atualiza o estado de visualizado de todas as linhas selecionadas
  const onMultiReadOrUnreadClick = () => {
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

  // Deleta todas as linhas selecionadas
  const onMultiDeleteRowClick = () => {
    let aux = rows.filter((row) => {
      return !row.checked;
    });
    setRows(aux);
  };

  // Atualiza o estado de visualizado da linha selecionada
  const onReadOrUnreadClick = (index) => {
    let aux = [...rows];
    aux[index].visualizado = !aux[index].visualizado;
    setRows(aux);
  };

  // Deleta linha selecionada
  const onDeleteClick = () => {
    let aux = [...rows];
    aux.splice(indexDelete, 1);
    setRows(aux);
  };

  useEffect(() => {
    let user = JSON.parse(localStorage.getItem("user"));
    console.log("user: ", user);
    NotificacaoService.getByUserId(parseInt(user.id)).then((data) => {
      console.log("data: ", data)
    });
  }, [])

  return (
    <FundoComHeader>
      <ModalConfirmacao
        open={openModalConfirmDelete}
        setOpen={setOpenModalConfirmDelete}
        textoModal={"confirmarExclusao"}
        onConfirmClick={onDeleteClick}
        onCancelClick={() => {}}
        textoBotao={"sim"}
      />

      <ModalConfirmacao
        open={openModalConfirmMultiDelete}
        setOpen={setOpenModalConfirmMultiDelete}
        textoModal={"confirmarExclusao"}
        onConfirmClick={onMultiDeleteRowClick}
        onCancelClick={() => {}}
        textoBotao={"sim"}
      />

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
                    <IconButton
                      onClick={() => {
                        setOpenModalConfirmMultiDelete(true);
                      }}
                    >
                      <DeleteOutlineOutlinedIcon
                        sx={{ color: "primary.main" }}
                      />
                    </IconButton>
                  </Tooltip>
                  <Tooltip title="Marcar como lido">
                    <IconButton onClick={onMultiReadOrUnreadClick}>
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
                        checked={
                          rows.every((row) => row.checked) && rows.length > 0
                        }
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
                      className="drop-shadow-lg noticacao-table-row"
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
                          <CheckCircleOutlineIcon
                            color="primary"
                            sx={{ fontSize: "30px", marginX: "0.5rem" }}
                          />
                        ) : row.tipo_icone == 2 ? (
                          <ErrorOutlineOutlinedIcon
                            color="primary"
                            sx={{ fontSize: "30px", marginX: "0.5rem" }}
                          />
                        ) : row.tipo_icone == 3 ? (
                          <HelpOutlineIcon
                            color="primary"
                            sx={{ fontSize: "30px", marginX: "0.5rem" }}
                          />
                        ) : (
                          <ChatBubbleOutlineIcon
                            color="primary"
                            sx={{ fontSize: "30px", marginX: "0.5rem" }}
                          />
                        )}
                      </td>
                      <td className="text-center">
                        <Typography
                          className="notificacao-table-row-td"
                          fontSize={FontConfig.default}
                        >
                          {row.date}
                        </Typography>
                        <Typography className="notificacao-table-row-td-action">
                          {row.visualizado ? (
                            <Tooltip title="Marcar como não lido">
                              <MarkEmailUnreadOutlinedIcon
                                onClick={() => onReadOrUnreadClick(index)}
                                className="cursor-pointer"
                                sx={{ color: "primary.main" }}
                              />
                            </Tooltip>
                          ) : (
                            <Tooltip title="Marcar como lido">
                              <MarkEmailReadOutlinedIcon
                                onClick={() => onReadOrUnreadClick(index)}
                                className="cursor-pointer"
                                sx={{ color: "primary.main" }}
                              />
                            </Tooltip>
                          )}
                          <Tooltip
                            title="Deletar"
                            className="cursor-pointer ml-4"
                          >
                            <DeleteOutlineOutlinedIcon
                              onClick={() => {
                                setIndexDelete(index);
                                setOpenModalConfirmDelete(true);
                              }}
                              sx={{ color: "primary.main" }}
                            />
                          </Tooltip>
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
