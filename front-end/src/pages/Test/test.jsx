import React, { Component } from 'react';

class MeuComponente extends Component {
  constructor(props) {
    super(props);
    this.state = {
      conteudoHtml: `
        <ul>
          <li>Item 1</li>
          <li><strong>Item 2</strong></li>
          <li><em>Item 3</em></li>
        </ul>
        <p>Parágrafo de exemplo<br />com quebra de linha</p>
      `,
    };
  }

  removerTagsHtml = () => {
    // Remover tags <ul> e </ul>
    let novoConteudoHtml = this.state.conteudoHtml.replace(/<ul\b[^>]*>(.*?)<\/ul>/gi, '');

    // Remover tags <li> e </li>
    novoConteudoHtml = novoConteudoHtml.replace(/<li\b[^>]*>(.*?)<\/li>/gi, '');

    // Remover tags <strong> e </strong>
    novoConteudoHtml = novoConteudoHtml.replace(/<strong\b[^>]*>(.*?)<\/strong>/gi, '');

    // Remover tags <p> e </p>
    novoConteudoHtml = novoConteudoHtml.replace(/<p\b[^>]*>(.*?)<\/p>/gi, '');

    // Remover tags <br /> e <br>
    novoConteudoHtml = novoConteudoHtml.replace(/<br\b[^>]*\/?>/gi, '');

    // Remover tags <em> e </em>
    novoConteudoHtml = novoConteudoHtml.replace(/<em\b[^>]*>(.*?)<\/em>/gi, '');

    // Atualizar o estado com o novo conteúdo HTML
    this.setState({ conteudoHtml: novoConteudoHtml });
  }

  render() {
    return (
      <div>
        <div dangerouslySetInnerHTML={{ __html: this.state.conteudoHtml }}></div>
        <button onClick={this.removerTagsHtml}>Remover Tags HTML</button>
      </div>
    );
  }
}

export default MeuComponente;