import React, { useRef, useState, useEffect } from "react";

import JoditEditor from "jodit-react";

const TextEditor = (props) => {
  const editor = useRef(null);

  const config = {
    readonly: false, // all options from https://xdsoft.net/jodit/doc/
    height: '700',
    placeholder: 'Digite aqui o escopo da proposta...',
  };

  const handleUpdate = (newContent) => {
    props.setEscopo(newContent);
  };

  return (
    <div className="App">
      <JoditEditor
        ref={editor}
        value={props.escopo}
        config={config}
        onBlur={handleUpdate}
      />
    </div>
  );
};

export default TextEditor;
