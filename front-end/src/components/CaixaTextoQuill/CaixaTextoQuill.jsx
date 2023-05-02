import React, { useContext, useEffect, useRef } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

import TextLanguageContext from "../../service/TextLanguageContext";

// Componente utilizado para formatação em campos de texto durante o sistema
function CaixaTextoQuill({ texto, setTexto, placeholder = "", useScroll = false, setScroll = false, useScrollEdit = false, onChange }) {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  const quillRef = useRef();

  /** Função para armazenar o texto a cada modificação */
  function handleChange(value) {
    setTexto(prevTexto => {
      const novoTexto = value;

      if (onChange) {
        onChange(novoTexto);
      }

      return novoTexto;
    });
  }

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
      readOnly={false}
      style={useScroll ? { height: '10rem', overflowY: 'scroll' } : setScroll ? { height: '5rem', overflowY: 'scroll' } : {}}
    />
  );
}

export default CaixaTextoQuill;
