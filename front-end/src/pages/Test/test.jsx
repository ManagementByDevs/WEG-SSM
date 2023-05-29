// // ********************************************** Gravar audio **********************************************

// import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
// import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

// const [
//   feedbackErroNavegadorIncompativel,
//   setFeedbackErroNavegadorIncompativel,
// ] = useState(false);
// const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
//   useState(false);

// const recognitionRef = useRef(null);

// const [escutar, setEscutar] = useState(false);

// const ouvirAudio = () => {
//   // Verifica se a API é suportada pelo navegador
//   if ("webkitSpeechRecognition" in window) {
//     const recognition = new window.webkitSpeechRecognition();
//     recognition.continuous = true;
//     switch (texts.linguagem) {
//       case "pt":
//         recognition.lang = "pt-BR";
//         break;
//       case "en":
//         recognition.lang = "en-US";
//         break;
//       case "es":
//         recognition.lang = "es-ES";
//         break;
//       case "ch":
//         recognition.lang = "cmn-Hans-CN";
//         break;
//       default:
//         recognition.lang = "pt-BR";
//         break;
//     }

//     recognition.onstart = () => {
//       // console.log("Reconhecimento de fala iniciado. Fale algo...");
//     };

//     recognition.onresult = (event) => {
//       const transcript =
//         event.results[event.results.length - 1][0].transcript;
//       setValorPesquisa(transcript);
//     };

//     recognition.onerror = (event) => {
//       setFeedbackErroReconhecimentoVoz(true);
//       setEscutar(false);
//     };

//     recognitionRef.current = recognition;
//     recognition.start();
//   } else {
//     setFeedbackErroNavegadorIncompativel(true);
//     setEscutar(false);
//   }
// };

// const stopRecognition = () => {
//   if (recognitionRef.current) {
//     recognitionRef.current.stop();
//     // console.log("Reconhecimento de fala interrompido.");
//   }
// };

// const startRecognition = () => {
//   setEscutar(!escutar);
// };

// useEffect(() => {
//   if (escutar) {
//     ouvirAudio();
//   } else {
//     stopRecognition();
//   }
// }, [escutar]);

// {
//   /* Feedback Erro reconhecimento de voz */
// }
// <Feedback
//   open={feedbackErroReconhecimentoVoz}
//   handleClose={() => {
//     setFeedbackErroReconhecimentoVoz(false);
//   }}
//   status={"erro"}
//   mensagem={texts.homeGerencia.feedback.feedback12}
// />
// {
//   /* Feedback Não navegador incompativel */
// }
// <Feedback
//   open={feedbackErroNavegadorIncompativel}
//   handleClose={() => {
//     setFeedbackErroNavegadorIncompativel(false);
//   }}
//   status={"erro"}
//   mensagem={texts.homeGerencia.feedback.feedback13}
// />

// <Tooltip
//   className="hover:cursor-pointer"
//   title={texts.homeGerencia.gravarAudio}
//   onClick={() => {
//     startRecognition();
//   }}
// >
//   {escutar ? (
//     <MicOutlinedIcon sx={{ color: "primary.main", fontSize: "1.3rem" }} />
//   ) : (
//     <MicNoneOutlinedIcon
//       sx={{ cursor: "pointer", color: "text.secondary", fontSize: "1.3rem" }}
//     />
//   )}
// </Tooltip>

// // ********************************************** Fim Gravar audio **********************************************