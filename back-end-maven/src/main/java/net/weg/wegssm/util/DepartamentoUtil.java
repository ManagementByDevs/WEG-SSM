package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.model.entities.Departamento;
import net.weg.wegssm.model.entities.Forum;

public class DepartamentoUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Departamento convertJsonToModel(String departamentoJson) {
        try {
            return this.objectMapper.readValue(departamentoJson, Departamento.class);
        } catch (Exception e) {
            return null;
        }
    }
}
