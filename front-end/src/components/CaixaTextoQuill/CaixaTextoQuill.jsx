import React, { useContext } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

import TextLanguageContext from "../../service/TextLanguageContext";

// Componente utilizado para formatação em campos de texto durante o sistema
function CaixaTextoQuill({ texto, setTexto }) {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Função para armazenar o texto a cada modificação
  function handleChange(value) {
    setTexto(value);
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
          [{ 'script': 'sub' }, { 'script': 'super' }],
          ["clean"],
        ],
      }}
      placeholder={texts.detalhesProposta.maisInformacoes}
    />
  );
}

export default CaixaTextoQuill;