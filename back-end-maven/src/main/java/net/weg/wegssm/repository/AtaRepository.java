package net.weg.wegssm.repository;

import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Proposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe repository para a ata
 */
@Repository
public interface AtaRepository extends JpaRepository<Ata, Long> {

    /**
     * Método para listar a ata pelo número sequencial
     *
     * @param numeroSequencial Número Sequencial da Ata buscada
     * @return Optional contendo a ata com o número sequencial enviado
     */
    Page<Ata> findByNumeroSequencial(String numeroSequencial, Pageable pageable);

    /**
     * Método que verifica se existe uma ata com o número sequencial passado por parâmetro
     *
     * @param numeroSequencial Número Sequencial buscado
     * @return Boolean retornando se existe uma Ata com o número sequencial enviado
     */
    Boolean existsByNumeroSequencial(String numeroSequencial);

    /**
     * Método que retorna uma ata que contenha a proposta passada por parâmetro
     *
     * @param proposta Proposta enviada para busca da Ata
     * @return Ata contendo a proposta enviada
     */
    Ata findByPropostasContaining(Proposta proposta);

    /**
     * Função para buscar atas de acordo com a sua publicação, além de retornar suas propostas presentes através do "LEFT JOIN"
     *
     * @param publicadaDg Boolean para definir se as atas pesquisadas serão publicadas na DG ou não
     * @return Lista com as Atas buscadas
     */
    @Query("SELECT a FROM Ata a LEFT JOIN FETCH a.propostas WHERE a.publicadaDg != publicada_dg")
    List<Ata> findByPublicadaDgNot(@Param("publicada_dg") Boolean publicadaDg);

    /**
     * Função para buscar uma ata pelo número sequencial e se está publicada pela dg
     *
     * @param numeroSequencial - Número sequencial da ata
     * @param publicadaDg      - Se está publicada pela dg
     * @param pageable         - Componente de paginação
     * @return
     */
    Page<Ata> findByNumeroSequencialAndPublicadaDg(String numeroSequencial, Boolean publicadaDg, Pageable pageable);

    /**
     * Função para buscar uma ata que estão publicadas pela dg
     *
     * @param publicadaDg - Se está publicada pela dg
     * @param pageable    - Componente de paginação
     * @return - Lista de atas
     */
    Page<Ata> findByPublicadaDg(Boolean publicadaDg, Pageable pageable);

    /**
     * Função para buscar uma ata pelo número sequencial e se está publicada pela dg e pela comissão
     *
     * @param numeroSequencial - Número sequencial da ata
     * @param publicadaDg      - Se está publicada pela dg
     * @param publicada        - Se está publicada pela comissão
     * @param pageable         - Componente de paginação
     * @return
     */
    Page<Ata> findByNumeroSequencialAndPublicadaDgAndPublicada(String numeroSequencial, Boolean publicadaDg, Boolean publicada, Pageable pageable);

    /**
     * Função para buscar uma ata pelo número sequencial e se está publicada pela comissão
     *
     * @param numeroSequencial - Número sequencial da ata
     * @param publicada        - Se está publicada pela comissão
     * @param pageable         - Componente de paginação
     * @return - Lista de atas
     */
    Page<Ata> findByNumeroSequencialAndPublicada(String numeroSequencial, Boolean publicada, Pageable pageable);

    /**
     * Função para buscar uma ata que está publicada pela dg e pela comissão
     *
     * @param publicadaDg - Se está publicada pela dg
     * @param publicada   - Se está publicada pela comissão
     * @param pageable    - Componente de paginação
     * @return - Lista de atas
     */
    Page<Ata> findByPublicadaDgAndPublicada(Boolean publicadaDg, Boolean publicada, Pageable pageable);

    /**
     * Função para buscar uma ata que está publicada pela comissão
     *
     * @param publicada - Se está publicada pela comissão
     * @param pageable  - Componente de paginação
     * @return - Lista de atas
     */
    Page<Ata> findByPublicada(Boolean publicada, Pageable pageable);
}

