import React, { useContext } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

import TextLanguageContext from "../../service/TextLanguageContext";

// Componente utilizado para formatação em campos de texto durante o sistema
function CaixaTextoQuill({ texto, setTexto, placeholder = "", useScroll = false, setScroll = false }) {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Função para armazenar o texto a cada modificação
  function handleChange(value) {
    setTexto(value);
  }

  if (placeholder == null || placeholder == "") {
    placeholder = texts.detalhesProposta.maisInformacoes;
  }

  function handleBlur() {
    console.log("ddd")
  }

  return (
    <ReactQuill
      value={texto}
      onChange={handleChange}
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
      style={useScroll ? { height: '10rem', overflowY: 'scroll' } : setScroll ? { height: '5rem', overflowY: 'scroll' } : {}}
      onBlur={handleBlur}
    />
  );
}

export default CaixaTextoQuill;
