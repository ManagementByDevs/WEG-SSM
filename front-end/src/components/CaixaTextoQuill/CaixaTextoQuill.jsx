import React, { useContext, useEffect, useRef } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

import TextLanguageContext from "../../service/TextLanguageContext";

// Componente utilizado para formatação em campos de texto durante o sistema
function CaixaTextoQuill({ texto, setTexto, placeholder = "", useScroll = false, setScroll = false, onChange }) {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  const quillRef = useRef();

  // Função para armazenar o texto a cada modificação
  function handleChange(value) {
    setTexto(value);

    if(onChange) {
      onChange(value);
    }
  }

  useEffect(() => {
    if (quillRef.current) {
      const quill = quillRef.current.getEditor();
      quill.on("text-change", () => {
        if (onChange) {
          onChange(quillRef.current.getEditor().root.innerHTML);
        }
      });
    }
  }, [quillRef, onChange]);

  if (placeholder == null || placeholder == "") {
    placeholder = texts.detalhesProposta.maisInformacoes;
  }

  return (
    <ReactQuill
      ref={quillRef}
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
    />
  );
}

export default CaixaTextoQuill;
