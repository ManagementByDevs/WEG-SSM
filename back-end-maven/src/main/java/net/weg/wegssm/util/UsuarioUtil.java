package net.weg.wegssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.dto.UsuarioDTO;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Usuario;

import javax.validation.Valid;

/**
 * Classe Util para o usuario
 */
public class UsuarioUtil {

    /**
     * Objeto usado para converter o JSON para DTO e o DTO para Model
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Função para converter um JSON em um Usuario
     *
     * @param usuarioJson - JSON do usuario
     * @return - Usuario convertido
     */
    public Usuario convertJsonToModel(String usuarioJson) {
        try {
            return this.objectMapper.readValue(usuarioJson, Usuario.class);
        } catch (Exception e) {
            return null;
        }
    }

}
