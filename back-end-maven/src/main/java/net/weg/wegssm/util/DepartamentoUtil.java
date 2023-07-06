package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.Forum;

/**
 * Classe Util para o departamento
 */
public class DepartamentoUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um Departamento
     *
     * @param departamentoJson - JSON do departamento
     * @return - Departamento convertido
     */
    public Departamento convertJsonToModel(String departamentoJson) {
        try {
            return this.objectMapper.readValue(departamentoJson, Departamento.class);
        } catch (Exception e) {
            return null;
        }
    }

}
