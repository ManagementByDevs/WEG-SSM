import React, { useState, useContext } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

import TextLanguageContext from "../../service/TextLanguageContext";

function CaixaTextoQuill({ texto, setTexto }) {
  const { texts } = useContext(TextLanguageContext);

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
            [{ 'script': 'sub'}, { 'script': 'super' }],
            ["clean"],
          ],
        }}
        placeholder={texts.detalhesProposta.maisInformacoes}
      />
    );
}

export default CaixaTextoQuill;
