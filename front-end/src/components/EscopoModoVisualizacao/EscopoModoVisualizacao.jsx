import React, { useContext, useState } from "react";

import { Box, Paper, Table, TableBody, TableHead, TableRow, Typography, Button } from "@mui/material";

import Escopo from "../Escopo/Escopo";
import ModalConfirmacao from "../ModalConfirmacao/ModalConfirmacao";

import DateService from "../../service/dateService";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import EscopoService from "../../service/escopoService";

// Componente para mudar o modo de visualização dos escopos (Grid, tabela ou nenhum escopo encontrado)
const EscopoModoVisualizacao = ({
    listaEscopos,
    onEscopoClick,
    nextModoVisualizacao,
    myEscopos,
}) => {
    if (listaEscopos.length == 0) {
        return <NadaEncontrado />;
    }

    if (nextModoVisualizacao == "TABLE")
        return (
            <EscopoGrid
                listaEscopos={listaEscopos}
                onEscopoClick={onEscopoClick}
                myEscopos={myEscopos}
            />
        );
    return (
        <EscopoTable
            listaEscopos={listaEscopos}
            onEscopoClick={onEscopoClick}
            myEscopos={myEscopos}
        />
    );
};

// const EscopoTable = ({
//     listaEscopos = [
//         {
//             id: 0,
//             titulo: "",
//             problema: "",
//             proposta: null,
//             frequencia: "",
//             beneficios: null,
//             anexos: null,
//             ultimaModificacao: null,
//             porcentagem: null,
//         },
//     ],
//     onEscopoClick,
//     myEscopos,
// }) => {

//     // Context para alterar o tamanho da fonte
//     const { FontConfig, setFontConfig } = useContext(FontContext);

//     // Contexto para trocar a linguagem
//     const { texts } = useContext(TextLanguageContext);

//     const [openModalConfirmacao, setOpenModalConfirmacao] = useState(false);

//     // Função que captura o click no ícone e abre o modal de confirmação de remoção do escopo
//     const onTrashCanClick = (index) => {
//         setOpenModalConfirmacao(true);
//         setEscopoSelecionado(listaEscopos[index]);
//     };

//     // Guarda a demanda selecionada para abrir o modal de motivo recusa
//     const [escopoSelecionado, setEscopoSelecionado] = useState();

//     // Função para deletar um escopo
//     const onDeleteClickEscopo = () => {
       
//     };

//     return (
//         <Paper sx={{ width: "100%" }} square>
//             <Table className="mb-8 table-fixed" sx={{ width: "100%" }}>
//                 <TableHead>
//                     <TableRow sx={{ backgroundColor: "primary.main" }}>
//                         <th className="text-white p-3 w-1/10">
//                             <Typography fontSize={FontConfig.big}>
//                                 {texts.escopoModoVisualizacao.porcentagem}
//                             </Typography>
//                         </th>
//                         <th className="text-left text-white p-3 w-3/6">
//                             <Typography fontSize={FontConfig.big}>
//                                 {texts.escopoModoVisualizacao.titulo}
//                             </Typography>
//                         </th>
//                         <th className="text-white p-3 w-1/12">
//                             <Typography fontSize={FontConfig.big}>
//                                 {texts.escopoModoVisualizacao.excluir}
//                             </Typography>
//                         </th>
//                     </TableRow>
//                 </TableHead>
//                 <TableBody>
//                     {listaEscopos.map((row, index) => (
//                         <TableRow
//                             className="cursor-pointer tabela-linha-demanda"
//                             hover
//                             key={index}
//                             sx={{
//                                 "&:last-child td, &:last-child th": { border: 0 },
//                             }}
//                             onClick={() => {
//                                 onEscopoClick(row);
//                             }}
//                         >
//                             <td className="text-center p-3" title={row.id}>
//                                 <Typography className="truncate" fontSize={FontConfig.medium}>
//                                     {row.id}
//                                 </Typography>
//                             </td>
//                             <td className="text-left p-3" title={row.titulo}>
//                                 <Typography className="truncate" fontSize={FontConfig.medium}>
//                                     {row.titulo}
//                                 </Typography>
//                             </td>
//                             <td
//                                 className="text-center p-3"
//                                 title={DateService.getTodaysDateUSFormat(row.data)}
//                             >
//                                 <Typography className="truncate" fontSize={FontConfig.default}>
//                                     {DateService.getTodaysDateUSFormat(row.data)}
//                                 </Typography>
//                             </td>
//                         </TableRow>
//                     ))}
//                 </TableBody>
//             </Table>

//             {/* Abrindo modal de confirmação de deletar escopo */}
//             <ModalConfirmacao
//                 textoModal={"descartarRascunho"}
//                 onConfirmClick={onDeleteClickEscopo}
//                 onCancelClick={() => {
//                     setOpenModalConfirmacao(false);
//                 }}
//                 textoBotao={"sim"}
//                 open={openModalConfirmacao}
//                 setOpen={setOpenModalConfirmacao}
//             />
//         </Paper>
//     );
// };

// Componente para exibir as demanda em forma de grid
const EscopoGrid = ({ listaEscopos, onEscopoClick }) => {
    return (
        <Box
            sx={{
                display: "grid",
                gap: "1rem",
                gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
            }}
        >
            {listaEscopos?.map((escopo, index) => (
                <Escopo
                    key={index}
                    escopo={escopo}
                    index={index}
                    onclick={() => {
                        // openEscopo(escopo);
                    }}
                />
            ))}
        </Box>
    );
};

// Componente para exibir nada encontrado
const NadaEncontrado = () => {

    // Contexto para trocar a linguagem
    const { texts } = useContext(TextLanguageContext);

    // Context para alterar o tamanho da fonte
    const { FontConfig, setFontConfig } = useContext(FontContext);

    return (
        <Box
            sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                flexDirection: "column",
                height: "100%",
                marginTop: "2rem",
            }}
        >
            <Typography
                fontSize={FontConfig.big}
                sx={{ color: "text.secondary", mb: 1 }}
            >
                {texts.demandaModoVisualizacao.nadaEncontrado}
            </Typography>
            <Typography
                fontSize={FontConfig.medium}
                sx={{ color: "text.secondary", mb: 1 }}
            >
                {texts.demandaModoVisualizacao.tenteNovamenteMaisTarde}
            </Typography>
        </Box>
    );
};

export default EscopoModoVisualizacao;