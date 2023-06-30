import React, { useContext, useEffect, useRef } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";
import { Box, Tooltip } from "@mui/material";

import TextLanguageContext from "../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

/** Componente utilizado para formatação em campos de texto durante o sistema */
function CaixaTextoQuill({
  texto,
  placeholder = "",
  useScroll = false,
  setScroll = false,
  useScrollEdit = false,
  onChange,
  label = "react-quill",
}) {
  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, palavrasJuntas, localClique } = useContext(
    SpeechRecognitionContext
  );

  /** Variável para armazenar o valor inicial de um input */
  const quillRef = useRef();

  /** useEffect utilizado para setar as informações e permitir as edições caso necessário */
  useEffect(() => {
    if (quillRef.current) {
      const quill = quillRef.current.getEditor();

      quill.on("text-change", () => {
        const value = quillRef.current.getEditor().root.innerHTML;

        if (value !== texto && onChange) {
          onChange(value);
        }
      });
    }
  }, [quillRef, onChange, texto]);

  /** Caso não seja passado um placeholder, utiliza o padrão "Mais informações" */
  if (placeholder == null || placeholder == "") {
    placeholder = texts.detalhesProposta.maisInformacoes;
  }

  /** useEffect utilizado para setar o valor do input com o texto transcrito */
  useEffect(() => {
    if (localClique == label) {
      onChange(palavrasJuntas);
    }
  }, [palavrasJuntas]);

  return (
    <Box className="relative w-full h-full">
      {/* Utilizado para configuração do input (opções de estilo, posições, valores...) */}
      <ReactQuill
        className="w-full"
        ref={quillRef}
        value={texto}
        onChange={(value) => {
          onChange(value);
        }}
        modules={{
          toolbar: [
            [{ size: [] }],
            ["bold", "italic", "underline", "strike", "blockquote"],
            [
              { list: "ordered" },
              { list: "bullet" },
              { indent: "-1" },
              { indent: "+1" },
            ],
            [{ script: "sub" }, { script: "super" }],
            ["clean"],
          ],
        }}
        placeholder={placeholder}
        readOnly={false}
        style={
          useScroll
            ? { height: "5rem", overflowY: "scroll" }
            : setScroll
              ? { height: "5rem", overflowY: "scroll" }
              : {}
        }
      />
      {/* Ícone de gravar audio */}
      <Box className="absolute" sx={{ right: 6, bottom: 8 }}>
        <Tooltip
          className="hover:cursor-pointer"
          title={texts.homeGerencia.gravarAudio}
          onClick={() => {
            startRecognition(label);
          }}
        >
          {escutar && localClique == label ? (
            <MicOutlinedIcon
              sx={{
                cursor: "pointer",
                color: "primary.main",
                fontSize: "1.3rem",
              }}
            />
          ) : (
            <MicNoneOutlinedIcon
              sx={{
                cursor: "pointer",
                color: "text.secondary",
                fontSize: "1.3rem",
              }}
            />
          )}
        </Tooltip>
      </Box>
    </Box>
  );
}

export default CaixaTextoQuill;