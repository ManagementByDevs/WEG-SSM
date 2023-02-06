// import React from "react";
// import DemandaGerencia from "../DemandaGerencia/DemandaGerencia";

// const DemandaGerenciaModoVisualizacao = ({
//   listaDemandas,
//   onDemandaClick,
//   nextModoVisualizacao,
// }) => {
//   if (nextModoVisualizacao == "TABLE")
//     return (
//       <DemandaGrid
//         listaDemandas={listaDemandas}
//         onDemandaClick={onDemandaClick}
//         myDemandas={myDemandas}
//       />
//     );
//   return (
//     <DemandaTable
//       listaDemandas={listaDemandas}
//       onDemandaClick={onDemandaClick}
//       myDemandas={myDemandas}
//     />
//   );
// };

// const DemandaTable = ({
//   listaDemandas = [
//     {
//       id: 0,
//       titulo: "",
//       problema: "",
//       proposta: "",
//       motivoRecusa: "",
//       status: "",
//       data: "",
//       solicitante: {},
//     },
//   ],
//   onDemandaClick,
//   myDemandas,
// }) => {};

// const DemandaGrid = ({ listaDemandas, onDemandaClick }) => {
//   return (
//     <Box
//       id="sextaDemandas"
//       sx={{
//         display: "grid",
//         gap: "1rem",
//         gridTemplateColumns: "repeat(auto-fit, minmax(720px, 1fr))",
//       }}
//     >
//       {listaDemandas?.map((demanda, index) => {
//         return (
//           <DemandaGerencia
//             key={index}
//             dados={demanda}
//             tipo="demanda"
//             onClick={() => {
//                 onDemandaClick(demanda);
//             }}
//           />
//         );
//       })}
//     </Box>
//   );
// };

// export default DemandaGerenciaModoVisualizacao;
