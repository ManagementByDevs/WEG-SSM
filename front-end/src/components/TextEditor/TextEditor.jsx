import React, { useRef, useState, useEffect } from "react";

import JoditEditor from "jodit-react";

const TextEditor = () => {
  const editor = useRef(null);
  const [content, setContent] = useState("...");

  const config = {
    readonly: false, // all options from https://xdsoft.net/jodit/doc/
    height: "100%",
  };

  const handleUpdate = (newContent) => {
    setContent(newContent);
  };

  return (
    <div className="App">
      <JoditEditor
        ref={editor}
        value={content}
        config={config}
        onBlur={handleUpdate}
      />
    </div>
  );
};

export default TextEditor;
