package net.weg.wegssm.model.entities;

public class Anexo {
    private byte arquivoAnexo;

    public Anexo(){ }

    public Anexo(byte arquivoAnexo) {
        this.arquivoAnexo = arquivoAnexo;
    }

    public byte getArquivoAnexo() {
        return arquivoAnexo;
    }

    public void setArquivoAnexo(byte arquivoAnexo) {
        this.arquivoAnexo = arquivoAnexo;
    }

    public void excluirAnexo() { }

    @Override
    public String toString() {
        return "Anexo{" +
                "arquivoAnexo=" + arquivoAnexo +
                '}';
    }
}
