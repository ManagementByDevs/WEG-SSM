package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.*;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.*;
import net.weg.wegssm.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Classe controller para todas as ações envolvendo propostas
 */
@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/proposta")
public class PropostaController {

    /** Service de Proposta */
    private PropostaService propostaService;

    /** Service de Usuário */
    private UsuarioService usuarioService;

    /** Service de Responsável de Negócio */
    private ResponsavelNegocioService responsavelNegocioService;

    /** Service de Tabelas de Custos */
    private TabelaCustoService tabelaCustoService;

    /** Service de Custos */
    private CustoService custoService;

    /** Service de CCs */
    private CCsService ccsService;

    /** Service de Anexos */
    private AnexoService anexoService;

    /** Service de Benefícios */
    private BeneficioService beneficioService;

    /** Service de Demandas */
    private DemandaService demandaService;

    /** Service de Histórico */
    private HistoricoService historicoService;

    /** Service de Documentos de Histórico */
    private DocumentoHistoricoService documentoHistoricoService;

    /**
     * Função para buscar todas as propostas existentes
     * @return ResponseEntity com uma lista com todas as propostas salvas
     */
    @GetMapping
    public ResponseEntity<List<Proposta>> findAll() {
        return ResponseEntity.status(200).body(propostaService.findAll());
    }

    /**
     * Função para buscar uma proposta pelo seu ID, passado como variável
     * @param id ID da proposta
     * @return ResponseEntity com a proposta encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!propostaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }

        return ResponseEntity.status(200).body(propostaService.findById(id));
    }

    /**
     * Função para buscar uma proposta pelo ID de uma demanda, passado como variável
     * @param id ID da demanda
     * @return ResponseEntity com a proposta encontrada
     */
    @GetMapping("/demanda/{id}")
    public ResponseEntity<Object> findByDemandaId(@PathVariable(value = "id") Long id) {
        if (!propostaService.existsByDemandaId(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com esta demanda.");
        }

        return ResponseEntity.status(200).body(propostaService.findByDemandaId(id));
    }

    /**
     * Função para buscar uma proposta pelo seu código PPM
     *
     * @param ppm Código PPM da proposta a ser buscada
     * @return ResponseEntity com a proposta buscada pelo código PPM
     */
    @GetMapping("/ppm/{ppm}")
    public ResponseEntity<Page<Proposta>> findByPpm(@PageableDefault(size = 20, sort = "score", direction = Sort.Direction.DESC) Pageable pageable,
                                                    @PathVariable(value = "ppm") Long ppm) {
        return ResponseEntity.status(200).body(propostaService.findByPpm(ppm, pageable));
    }

    /** Função principal para a pesquisa de propostas, usando diversos parâmetros possíveis para a busca
     * @param pageable Pageable usado para a ordenação das propostas e separação em páginas
     * @param titulo Título da proposta
     * @param solicitanteJson Solicitante de proposta em formato String
     * @param gerenteJson Gerente da proposta em formato String
     * @param forumJson Forum da proposta em formato String
     * @param departamentoJson Departamento da proposta em formato String
     * @param tamanho Tamanho da proposta
     * @param analistaJson Analista da proposta em formato String
     * @param status Status da proposta
     * @param presenteEm Parâmetro "PresenteEm" da proposta
     * @return ResponseEntity contendo uma página de propostas filtradas com os parâmetros que receber
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Proposta>> findPage(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String titulo,
            @RequestParam(value = "solicitante", required = false) String solicitanteJson,
            @RequestParam(value = "gerente", required = false) String gerenteJson,
            @RequestParam(value = "forum", required = false) String forumJson,
            @RequestParam(value = "departamento", required = false) String departamentoJson,
            @RequestParam(required = false) String tamanho,
            @RequestParam(value = "analista", required = false) String analistaJson,
            @RequestParam(required = false, value = "status") Status status,
            @RequestParam(required = false, value = "presenteEm") String presenteEm,
            @RequestParam(required = false, value = "codigoPPM") Long codigoPPM
    ) {
        Usuario solicitante = new UsuarioUtil().convertJsonToModel(solicitanteJson);
        Usuario gerente = new UsuarioUtil().convertJsonToModel(gerenteJson);
        Usuario analista = new UsuarioUtil().convertJsonToModel(analistaJson);
        Forum forum = new ForumUtil().convertJsonToModel(forumJson);
        Departamento departamento = new DepartamentoUtil().convertJsonToModel(departamentoJson);

        if(codigoPPM != null) {
            return findByPpm(pageable, codigoPPM);
        }

        if (presenteEm != null && !presenteEm.isEmpty()) {
            if (analista != null) {
                if (status != null) {
                    if (titulo != null && !titulo.isEmpty()) {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, analista, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(
                                                            true, status, analista, titulo, gerente, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(
                                                            true, status, analista, titulo, gerente, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(
                                                            true, status, analista, titulo, gerente, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, analista, titulo, gerente, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(
                                                            true, status, analista, titulo, gerente, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(
                                                            true, status, analista, titulo, gerente, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(
                                                            true, status, analista, titulo, gerente, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, analista, titulo, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(
                                                            true, status, analista, titulo, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(
                                                            true, status, analista, titulo, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndPresenteEm(
                                                            true, status, analista, titulo, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, analista, titulo, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(
                                                            true, status, analista, titulo, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(
                                                            true, status, analista, titulo, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(
                                                            true, status, analista, titulo, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndPresenteEm(
                                                            true, status, analista, titulo, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, analista, gerente, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, gerente, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndPresenteEm(
                                                            true, status, analista, gerente, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, gerente, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndPresenteEm(
                                                            true, status, analista, gerente, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndPresenteEm(
                                                            true, status, analista, gerente, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndPresenteEm(
                                                            true, status, analista, gerente, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, gerente, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, analista, gerente, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, gerente, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndPresenteEm(
                                                            true, status, analista, gerente, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, gerente, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndPresenteEm(
                                                            true, status, analista, gerente, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndPresenteEm(
                                                            true, status, analista, gerente, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndPresenteEm(
                                                            true, status, analista, gerente, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, analista, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndPresenteEm(
                                                            true, status, analista, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndPresenteEm(
                                                            true, status, analista, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndPresenteEm(
                                                            true, status, analista, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndPresenteEm(
                                                            true, status, analista, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, analista, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndPresenteEm(
                                                            true, status, analista, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, analista, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndPresenteEm(
                                                            true, status, analista, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndPresenteEm(
                                                            true, status, analista, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndPresenteEm(
                                                            true, status, analista, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (titulo != null && !titulo.isEmpty()) {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, analista, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(
                                                            true, analista, titulo, gerente, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(
                                                            true, analista, titulo, gerente, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, gerente, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(
                                                            true, analista, titulo, gerente, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, analista, titulo, gerente, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, gerente, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(
                                                            true, analista, titulo, gerente, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, gerente, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(
                                                            true, analista, titulo, gerente, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, gerente, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(
                                                            true, analista, titulo, gerente, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, analista, titulo, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(
                                                            true, analista, titulo, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(
                                                            true, analista, titulo, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndPresenteEm(
                                                            true, analista, titulo, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, analista, titulo, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(
                                                            true, analista, titulo, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(
                                                            true, analista, titulo, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(
                                                            true, analista, titulo, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndPresenteEm(
                                                            true, analista, titulo, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, analista, gerente, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, analista, gerente, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndPresenteEm(
                                                            true, analista, gerente, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, gerente, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndPresenteEm(
                                                            true, analista, gerente, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndPresenteEm(
                                                            true, analista, gerente, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndPresenteEm(
                                                            true, analista, gerente, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, gerente, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, analista, gerente, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, analista, gerente, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndPresenteEm(
                                                            true, analista, gerente, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, gerente, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndPresenteEm(
                                                            true, analista, gerente, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndPresenteEm(
                                                            true, analista, gerente, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndPresenteEm(
                                                            true, analista, gerente, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, analista, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, analista, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndPresenteEm(
                                                            true, analista, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndPresenteEm(
                                                            true, analista, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndPresenteEm(
                                                            true, analista, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndPresenteEm(
                                                            true, analista, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, analista, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, analista, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndPresenteEm(
                                                            true, analista, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, analista, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTamanhoAndPresenteEm(
                                                            true, analista, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndSolicitanteAndPresenteEm(
                                                            true, analista, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndPresenteEm(
                                                            true, analista, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (status != null) {
                    if (titulo != null && !titulo.isEmpty()) {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(
                                                            true, status, titulo, gerente, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(
                                                            true, status, titulo, gerente, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, gerente, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(
                                                            true, status, titulo, gerente, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, titulo, gerente, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, gerente, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(
                                                            true, status, titulo, gerente, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, gerente, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(
                                                            true, status, titulo, gerente, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, gerente, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(
                                                            true, status, titulo, gerente, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, titulo, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(
                                                            true, status, titulo, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(
                                                            true, status, titulo, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndPresenteEm(
                                                            true, status, titulo, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, titulo, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(
                                                            true, status, titulo, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(
                                                            true, status, titulo, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(
                                                            true, status, titulo, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndPresenteEm(
                                                            true, status, titulo, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, gerente, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, gerente, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndPresenteEm(
                                                            true, status, gerente, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, gerente, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndPresenteEm(
                                                            true, status, gerente, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitanteAndPresenteEm(
                                                            true, status, gerente, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndPresenteEm(
                                                            true, status, gerente, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, gerente, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, gerente, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, gerente, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndPresenteEm(
                                                            true, status, gerente, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, gerente, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndPresenteEm(
                                                            true, status, gerente, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndSolicitanteAndPresenteEm(
                                                            true, status, gerente, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndPresenteEm(
                                                            true, status, gerente, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndDepartamentoAndPresenteEm(
                                                            true, status, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndTamanhoAndPresenteEm(
                                                            true, status, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndSolicitanteAndPresenteEm(
                                                            true, status, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndPresenteEm(
                                                            true, status, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, status, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, status, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndDepartamentoAndPresenteEm(
                                                            true, status, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, status, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTamanhoAndPresenteEm(
                                                            true, status, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndSolicitanteAndPresenteEm(
                                                            true, status, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndPresenteEm(
                                                            true, status, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (titulo != null && !titulo.isEmpty()) {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, titulo, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, titulo, gerente, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, titulo, gerente, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndPresenteEm(
                                                            true, titulo, gerente, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, titulo, gerente, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndPresenteEm(
                                                            true, titulo, gerente, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndPresenteEm(
                                                            true, titulo, gerente, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndPresenteEm(
                                                            true, titulo, gerente, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, titulo, gerente, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, titulo, gerente, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, titulo, gerente, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndPresenteEm(
                                                            true, titulo, gerente, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, titulo, gerente, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndPresenteEm(
                                                            true, titulo, gerente, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndPresenteEm(
                                                            true, titulo, gerente, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndPresenteEm(
                                                            true, titulo, gerente, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, titulo, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, titulo, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, titulo, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndPresenteEm(
                                                            true, titulo, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, titulo, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndPresenteEm(
                                                            true, titulo, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndPresenteEm(
                                                            true, titulo, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndPresenteEm(
                                                            true, titulo, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, titulo, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, titulo, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, titulo, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndPresenteEm(
                                                            true, titulo, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, titulo, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndPresenteEm(
                                                            true, titulo, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndSolicitanteAndPresenteEm(
                                                            true, titulo, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndPresenteEm(
                                                            true, titulo, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, gerente, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, gerente, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, gerente, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndPresenteEm(
                                                            true, gerente, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, gerente, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndTamanhoAndPresenteEm(
                                                            true, gerente, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndSolicitanteAndPresenteEm(
                                                            true, gerente, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndPresenteEm(
                                                            true, gerente, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, gerente, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, gerente, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, gerente, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndDepartamentoAndPresenteEm(
                                                            true, gerente, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, gerente, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndTamanhoAndPresenteEm(
                                                            true, gerente, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndSolicitanteAndPresenteEm(
                                                            true, gerente, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndPresenteEm(
                                                            true, gerente, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, forum, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, forum, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, forum, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndDepartamentoAndPresenteEm(
                                                            true, forum, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, forum, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndTamanhoAndPresenteEm(
                                                            true, forum, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndSolicitanteAndPresenteEm(
                                                            true, forum, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndPresenteEm(
                                                            true, forum, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, departamento, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndDepartamentoAndTamanhoAndPresenteEm(
                                                            true, departamento, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndDepartamentoAndSolicitanteAndPresenteEm(
                                                            true, departamento, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndDepartamentoAndPresenteEm(
                                                            true, departamento, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTamanhoAndSolicitanteAndPresenteEm(
                                                            true, tamanho, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTamanhoAndPresenteEm(
                                                            true, tamanho, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndSolicitanteAndPresenteEm(
                                                            true, solicitante, presenteEm, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndPresenteEm(
                                                            true, presenteEm, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (analista != null) {
                if (status != null) {
                    if (titulo != null) {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(
                                                            true, status, analista, titulo, gerente, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                            true, status, analista, titulo, gerente, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(
                                                            true, status, analista, titulo, gerente, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(
                                                            true, status, analista, titulo, gerente, forum, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(
                                                            true, status, analista, titulo, gerente, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(
                                                            true, status, analista, titulo, gerente, forum, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(
                                                            true, status, analista, titulo, gerente, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, status, analista, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(
                                                            true, status, analista, titulo, gerente, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(
                                                            true, status, analista, titulo, gerente, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(
                                                            true, status, analista, titulo, gerente, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(
                                                            true, status, analista, titulo, gerente, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(
                                                            true, status, analista, titulo, gerente, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(
                                                            true, status, analista, titulo, gerente, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndGerente(
                                                            true, status, analista, titulo, gerente, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, status, analista, titulo, forum, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(
                                                            true, status, analista, titulo, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(
                                                            true, status, analista, titulo, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(
                                                            true, status, analista, titulo, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(
                                                            true, status, analista, titulo, forum, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(
                                                            true, status, analista, titulo, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(
                                                            true, status, analista, titulo, forum, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndForum(
                                                            true, status, analista, titulo, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, status, analista, titulo, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(
                                                            true, status, analista, titulo, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(
                                                            true, status, analista, titulo, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(
                                                            true, status, analista, titulo, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(
                                                            true, status, analista, titulo, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(
                                                            true, status, analista, titulo, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(
                                                            true, status, analista, titulo, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingIgnoreCase(
                                                            true, status, analista, titulo, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, status, analista, gerente, forum, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(
                                                            true, status, analista, gerente, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                            true, status, analista, gerente, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamento(
                                                            true, status, analista, gerente, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(
                                                            true, status, analista, gerente, forum, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanho(
                                                            true, status, analista, gerente, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitante(
                                                            true, status, analista, gerente, forum, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForum(
                                                            true, status, analista, gerente, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, status, analista, gerente, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanho(
                                                            true, status, analista, gerente, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitante(
                                                            true, status, analista, gerente, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamento(
                                                            true, status, analista, gerente, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitante(
                                                            true, status, analista, gerente, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanho(
                                                            true, status, analista, gerente, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitante(
                                                            true, status, analista, gerente, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerente(
                                                            true, status, analista, gerente, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, status, analista, forum, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanho(
                                                            true, status, analista, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitante(
                                                            true, status, analista, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamento(
                                                            true, status, analista, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitante(
                                                            true, status, analista, forum, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanho(
                                                            true, status, analista, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitante(
                                                            true, status, analista, forum, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndForum(
                                                            true, status, analista, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, status, analista, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanho(
                                                            true, status, analista, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitante(
                                                            true, status, analista, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamento(
                                                            true, status, analista, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitante(
                                                            true, status, analista, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndTamanho(
                                                            true, status, analista, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndSolicitante(
                                                            true, status, analista, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndAnalista(
                                                            true, status, analista, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (titulo != null) {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(
                                                            true, analista, titulo, gerente, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                            true, analista, titulo, gerente, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(
                                                            true, analista, titulo, gerente, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(
                                                            true, analista, titulo, gerente, forum, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(
                                                            true, analista, titulo, gerente, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(
                                                            true, analista, titulo, gerente, forum, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(
                                                            true, analista, titulo, gerente, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, analista, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(
                                                            true, analista, titulo, gerente, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(
                                                            true, analista, titulo, gerente, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(
                                                            true, analista, titulo, gerente, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(
                                                            true, analista, titulo, gerente, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(
                                                            true, analista, titulo, gerente, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(
                                                            true, analista, titulo, gerente, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndGerente(
                                                            true, analista, titulo, gerente, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, analista, titulo, forum, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(
                                                            true, analista, titulo, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(
                                                            true, analista, titulo, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(
                                                            true, analista, titulo, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(
                                                            true, analista, titulo, forum, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(
                                                            true, analista, titulo, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(
                                                            true, analista, titulo, forum, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndForum(
                                                            true, analista, titulo, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, analista, titulo, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(
                                                            true, analista, titulo, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(
                                                            true, analista, titulo, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(
                                                            true, analista, titulo, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(
                                                            true, analista, titulo, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(
                                                            true, analista, titulo, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(
                                                            true, analista, titulo, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTituloContainingIgnoreCase(
                                                            true, analista, titulo, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, analista, gerente, forum, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(
                                                            true, analista, gerente, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                            true, analista, gerente, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamento(
                                                            true, analista, gerente, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(
                                                            true, analista, gerente, forum, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanho(
                                                            true, analista, gerente, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitante(
                                                            true, analista, gerente, forum, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForum(
                                                            true, analista, gerente, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, analista, gerente, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanho(
                                                            true, analista, gerente, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitante(
                                                            true, analista, gerente, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamento(
                                                            true, analista, gerente, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitante(
                                                            true, analista, gerente, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndTamanho(
                                                            true, analista, gerente, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerenteAndSolicitante(
                                                            true, analista, gerente, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndGerente(
                                                            true, analista, gerente, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, analista, forum, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanho(
                                                            true, analista, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitante(
                                                            true, analista, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamento(
                                                            true, analista, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitante(
                                                            true, analista, forum, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndTamanho(
                                                            true, analista, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForumAndSolicitante(
                                                            true, analista, forum, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndForum(
                                                            true, analista, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(
                                                            true, analista, departamento, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanho(
                                                            true, analista, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitante(
                                                            true, analista, departamento, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndDepartamento(
                                                            true, analista, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTamanhoAndSolicitante(
                                                            true, analista, tamanho, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndTamanho(
                                                            true, analista, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalistaAndSolicitante(
                                                            true, analista, solicitante, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndAnalista(
                                                            true, analista, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                if (status != null) {
                    if (titulo != null && !titulo.isEmpty()) {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(true, status, titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(true, status, titulo, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(true, status, titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(true, status, titulo, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(true, status, titulo, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(true, status, titulo, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(true, status, titulo, gerente, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndForum(true, status, titulo, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(true, status, titulo, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(true, status, titulo, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(true, status, titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(true, status, titulo, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(true, status, titulo, gerente, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanho(true, status, titulo, gerente, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(true, status, titulo, gerente, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndGerente(true, status, titulo, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(true, status, titulo, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(true, status, titulo, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(true, status, titulo, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndDepartamento(true, status, titulo, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(true, status, titulo, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndTamanho(true, status, titulo, forum, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForumAndSolicitante(true, status, titulo, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndForum(true, status, titulo, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(true, status, titulo, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(true, status, titulo, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(true, status, titulo, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndDepartamento(true, status, titulo, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(true, status, titulo, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndTamanho(true, status, titulo, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCaseAndSolicitante(true, status, titulo, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTituloContainingIgnoreCase(true, status, titulo, pageable)
                                            );
                                        }

                                    }
                                }
                            }
                        }
                    } else {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(true, status, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(true, status, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(true, status, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndDepartamento(true, status, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(true, status, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndTamanho(true, status, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForumAndSolicitante(true, status, gerente, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndForum(true, status, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(true, status, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndTamanho(true, status, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndDepartamentoAndSolicitante(true, status, gerente, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndDepartamento(true, status, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndTamanhoAndSolicitante(true, status, gerente, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndTamanho(true, status, gerente, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerenteAndSolicitante(true, status, gerente, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndGerente(true, status, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(true, status, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndDepartamentoAndTamanho(true, status, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndDepartamentoAndSolicitante(true, status, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndDepartamento(true, status, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndTamanhoAndSolicitante(true, status, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndTamanho(true, status, forum, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForumAndSolicitante(true, status, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndForum(true, status, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndDepartamentoAndTamanhoAndSolicitante(true, status, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndDepartamentoAndTamanho(true, status, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndDepartamentoAndSolicitante(true, status, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndDepartamento(true, status, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTamanhoAndSolicitante(true, status, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndTamanho(true, status, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatusAndSolicitante(true, status, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndStatus(true, status, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (titulo != null && !titulo.isEmpty()) {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(true, titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(true, titulo, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(true, titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(true, titulo, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(true, titulo, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(true, titulo, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(true, titulo, gerente, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndForum(true, titulo, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(true, titulo, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(true, titulo, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(true, titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(true, titulo, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(true, titulo, gerente, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndTamanho(true, titulo, gerente, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(true, titulo, gerente, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndGerente(true, titulo, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(true, titulo, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(true, titulo, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(true, titulo, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndDepartamento(true, titulo, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(true, titulo, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndTamanho(true, titulo, forum, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForumAndSolicitante(true, titulo, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndForum(true, titulo, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(true, titulo, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(true, titulo, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(true, titulo, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndDepartamento(true, titulo, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(true, titulo, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndTamanho(true, titulo, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCaseAndSolicitante(true, titulo, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTituloContainingIgnoreCase(true, titulo, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (gerente != null) {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(true, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndTamanho(true, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndDepartamentoAndSolicitante(true, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndDepartamento(true, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndTamanhoAndSolicitante(true, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndTamanho(true, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForumAndSolicitante(true, gerente, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndForum(true, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndDepartamentoAndTamanhoAndSolicitante(true, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndDepartamentoAndTamanho(true, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndDepartamentoAndSolicitante(true, gerente, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndDepartamento(true, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndTamanhoAndSolicitante(true, gerente, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndTamanho(true, gerente, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerenteAndSolicitante(true, gerente, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndGerente(true, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (forum != null) {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndDepartamentoAndTamanhoAndSolicitante(true, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndDepartamentoAndTamanho(true, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndDepartamentoAndSolicitante(true, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndDepartamento(true, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndTamanhoAndSolicitante(true, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndTamanho(true, forum, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForumAndSolicitante(true, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndForum(true, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (departamento != null) {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndDepartamentoAndTamanhoAndSolicitante(true, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndDepartamentoAndTamanho(true, departamento, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndDepartamentoAndSolicitante(true, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndDepartamento(true, departamento, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (tamanho != null && !tamanho.isEmpty()) {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTamanhoAndSolicitante(true, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndTamanho(true, tamanho, pageable)
                                            );
                                        }
                                    } else {
                                        if (solicitante != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidadeAndSolicitante(true, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    propostaService.findByVisibilidade(true, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Função de criação de uma proposta
     *
     * @param propostaJSON Objeto da proposta em formato string
     * @return ResponseEntity com a proposta recém-criada
     */
    @PostMapping
    public ResponseEntity<Proposta> save(@RequestParam(value = "proposta") String propostaJSON) {

        PropostaUtil propostaUtil = new PropostaUtil();
        Proposta proposta = propostaUtil.convertJsonToModel(propostaJSON);

        Demanda demanda = demandaService.findById(proposta.getDemanda().getId()).get();
        List<Historico> historicoProposta = new ArrayList<>();
        for (Historico historico : demanda.getHistoricoDemanda()) {
            historicoProposta.add(historicoService.save(historico));
        }
        proposta.setHistoricoProposta(historicoProposta);

        proposta.setData(new Date());
        proposta.setVisibilidade(true);

        for (ResponsavelNegocio responsavelNegocio : proposta.getResponsavelNegocio()) {
            responsavelNegocioService.save(responsavelNegocio);
        }

        for (TabelaCusto tabelaCusto : proposta.getTabelaCustos()) {
            for (Custo custo : tabelaCusto.getCustos()) {
                custoService.save(custo);
            }
            for (CC cc : tabelaCusto.getCcs()) {
                ccsService.save(cc);
            }
            tabelaCustoService.save(tabelaCusto);
        }
        proposta.setScore(calcularScore(proposta));

        return ResponseEntity.status(HttpStatus.CREATED).body(propostaService.save(proposta));
    }

    /**
     * Função para calcular e retornar o score de uma proposta
     * @param proposta Proposta usada para o cálculo de seu Score
     * @return Score calculado da proposta recebida
     */
    private Double calcularScore(Proposta proposta) {
        Double valorBeneficiosReais = 0.0;
        Double valorBeneficiosPotenciais = 0.0;

        for (Beneficio beneficio : proposta.getBeneficios()) {
            beneficio = beneficioService.findById(beneficio.getId()).get();

            if(beneficio.getTipoBeneficio().equals(TipoBeneficio.REAL)) {
                valorBeneficiosReais += beneficio.getValor_mensal();
            }

            if(beneficio.getTipoBeneficio().equals(TipoBeneficio.POTENCIAL)) {
                valorBeneficiosPotenciais += beneficio.getValor_mensal();
            }
        }

        Integer valorTamanhoDemanda;
        if(proposta.getTamanho() == null) {
            valorTamanhoDemanda = 1000000000;
        } else if (proposta.getTamanho().equals("Muito Pequeno")) {
            valorTamanhoDemanda = 40;
        } else if(proposta.getTamanho().equals("Pequeno")) {
            valorTamanhoDemanda = 300;
        } else if(proposta.getTamanho().equals("Médio")) {
            valorTamanhoDemanda = 1000;
        } else if(proposta.getTamanho().equals("Grande")) {
            valorTamanhoDemanda = 3000;
        } else {
            valorTamanhoDemanda = 5000;
        }

        Long agingMilisegundos = Math.abs(new Date().getTime() - proposta.getData().getTime());
        Long agingFinal = TimeUnit.DAYS.convert(agingMilisegundos, TimeUnit.MILLISECONDS);

        Usuario solicitante = usuarioService.findById(proposta.getSolicitante().getId()).get();
        Integer valorPrioridade;
        if(solicitante.getTipoUsuario().equals(TipoUsuario.SOLICITANTE)) {
            valorPrioridade = 1;
        } else if(solicitante.getTipoUsuario().equals(TipoUsuario.ANALISTA)) {
            valorPrioridade = 2;
        } else if(solicitante.getTipoUsuario().equals(TipoUsuario.GERENTE)) {
            valorPrioridade = 4;
        } else {
            valorPrioridade = 16;
        }

        return (((2 * valorBeneficiosReais) + (1 * valorBeneficiosPotenciais) + agingFinal) / valorTamanhoDemanda) * valorPrioridade;
    }

    /**
     * Função para somente atualizar o status de uma proposta, recebendo seu ID e o novo status como parâmetros
     *
     * @param id     ID da proposta a ser atualizada
     * @param status Status novo que a proposta irá receber
     * @param motivo Motivo da recusa de uma proposta, caso ela seja recusada
     * @return ResponseEntity com a proposta atualizada
     */
    @PutMapping("/status/{id}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable(value = "id") Long id,
                                                  @RequestParam(value = "status") Status status,
                                                  @RequestParam(value = "motivo", required = false) String motivo) {
        Optional<Proposta> propostaOptional = propostaService.findById(id);
        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada!");
        }

        Proposta proposta = propostaOptional.get();
        proposta.setStatus(status);

        if(status == Status.CANCELLED) {
            proposta.setMotivoRecusa(motivo);
            proposta.getDemanda().setStatus(Status.CANCELLED);
            proposta.getDemanda().setMotivoRecusa(motivo);
        }
        if(status == Status.DONE) {
            proposta.getDemanda().setStatus(Status.DONE);
        }

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Método PUT para atualizar uma proposta no banco de dados com novos dados, sejam anexos, benefícios ou tabelas de custos a serem salvos
     *
     * @param id               - ID da proposta a ser atualizada
     * @param propostaJSON     - Proposta com os novos dados
     * @param novaPropostaJSON - Proposta com Benefícios e Tabelas de Custos a serem salvas no banco de dados
     * @param listaIdsAnexos   - Lista de IDs de anexos que já pertenciam à proposta
     * @return ResponseEntity<Object> - Proposta atualizada ou mensagem de erro
     */
    @PutMapping("/update-novos-dados/{id}")
    public ResponseEntity<Object> updateComNovosDados(@PathVariable(value = "id") Long id,
                                                      @RequestParam(value = "proposta") String propostaJSON,
                                                      @RequestParam(value = "propostaComDadosNovos", required = false) String novaPropostaJSON,
                                                      @RequestParam(value = "listaIdsAnexos", required = false) List<String> listaIdsAnexos,
                                                      @RequestParam(value = "listaAnexosNovos", required = false) List<MultipartFile> files
    ) {
        Optional<Proposta> propostaOptional = propostaService.findById(id);

        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar uma proposta com este id.");
        }

        PropostaUtil propostaUtil = new PropostaUtil();
        Proposta proposta = propostaUtil.convertJaCriadaJsonToModel(propostaJSON);

        proposta.setId(id);

        deleteTabelaCustosRows(propostaOptional.get(), proposta);

        // Faz a atualização dos dados da proposta que devem ser adicionados (benefícios, tabelas...)
        Proposta propostaNovosDados = new Proposta();
        if (novaPropostaJSON != null) {
            propostaNovosDados = propostaUtil.convertJaCriadaJsonToModel(novaPropostaJSON);

            ArrayList<Beneficio> beneficios = new ArrayList<>(); // Array aux que vai receber os novos beneficios
            for (Beneficio beneficio : propostaNovosDados.getBeneficios()) {
                beneficio.setId(null); // Setando como null pq o id vem pra cá como negativo
                beneficios.add(beneficioService.save(beneficio)); // Salva e adiciona o benefício na lista
            }

            propostaNovosDados.setBeneficios(beneficios); // Seta os novos beneficios na proposta aux

            ArrayList<TabelaCusto> novasTabelasCustos = new ArrayList<>(); // Array aux que vai receber as novas tabelas de custos

            for (TabelaCusto tabelaCusto : propostaNovosDados.getTabelaCustos()) {
                List<Custo> novosCustos = new ArrayList<>();
                List<CC> novosCCs = new ArrayList<>();

                for (Custo custo : tabelaCusto.getCustos()) {
                    custo.setId(null);
                    novosCustos.add(custoService.save(custo));
                }

                for (CC cc : tabelaCusto.getCcs()) {
                    cc.setId(null);
                    novosCCs.add(ccsService.save(cc));
                }

                tabelaCusto.setCustos(novosCustos);
                tabelaCusto.setCcs(novosCCs);
                tabelaCusto.setId(null);

                novasTabelasCustos.add(tabelaCustoService.save(tabelaCusto));
            }

            propostaNovosDados.setTabelaCustos(novasTabelasCustos); // Seta as novas tabelas de custos na proposta aux
        }

        // Salvando linhas de tabelas custos que foram adicionadas na edição
        for (TabelaCusto tabelaCusto : proposta.getTabelaCustos()) {
            for (Custo custo : tabelaCusto.getCustos()) {
                if (custo.getId() < 0) { // É um novo custo
                    Custo novoCusto = custoService.save(custo);
                    custo.setId(novoCusto.getId());
                }
            }

            for (CC cc : tabelaCusto.getCcs()) {
                if (cc.getId() < 0) { // É um novo CC
                    CC novoCC = ccsService.save(cc);
                    cc.setId(novoCC.getId());
                }
            }

            tabelaCustoService.save(tabelaCusto);
        }

        // Salvando os novos dados dos benefícios
        for (Beneficio beneficio : proposta.getBeneficios()) {
            beneficioService.save(beneficio);
        }

        // Colocando dados da PropostaAux para a Proposta a ser atualizada
        if (listaIdsAnexos != null) {
            for (String idAnexo : listaIdsAnexos) {
                proposta.getAnexo().add(anexoService.findById(Long.parseLong(idAnexo)));
            }
        }

        // Salvando os novos anexos adicionados na proposta
        if (files != null) {
            Proposta propostaAux = new Proposta();
            propostaAux.setAnexos(files);

            // Salvando os anexos no banco de dados
            for (Anexo anexo : propostaAux.getAnexo()) {
                anexo.setId((anexoService.save(anexo)).getId());
            }

            proposta.getAnexo().addAll(propostaAux.getAnexo());
        }

        if (novaPropostaJSON != null) {
            proposta.getBeneficios().addAll(propostaNovosDados.getBeneficios());
            proposta.getTabelaCustos().addAll(propostaNovosDados.getTabelaCustos());
        }

        deleteAllObjectsRemoved(proposta);
        proposta.setScore(calcularScore(proposta));

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Lógica para apagar objetos que foram removidos da proposta
     * São eles: CCs, Custos, Tabelas de Custos, Anexos
     *
     * @param proposta Proposta que está sendo editada, nova proposta, nova versão
     */
    public void deleteAllObjectsRemoved(Proposta proposta) {
        Proposta propostaAntiga = propostaService.findById(proposta.getId()).get();

        // Iterando as tabelas de custos da proposta antiga
        for (TabelaCusto oldTabelaCusto : propostaAntiga.getTabelaCustos()) {
            // Deletando as Tabelas de Custos removidos e seus respectivos custos e ccs
            if (!proposta.containsTabelaCusto(oldTabelaCusto)) {

                // Apagando os custos da tabela de custos
                for (Custo custo : oldTabelaCusto.getCustos()) {
                    custoService.deleteById(custo.getId());
                }

                // Apagando os CCs da tabela de custos
                for (CC cc : oldTabelaCusto.getCcs()) {
                    ccsService.deleteById(cc.getId());
                }

                tabelaCustoService.deleteById(oldTabelaCusto.getId());
            }
        }

        // Apagando os anexos que foram removidos
        for (Anexo anexo : propostaAntiga.getAnexo()) {
            if (!proposta.containsAnexo(anexo)) {
                anexoService.deleteById(anexo.getId());
            }
        }
    }

    /**
     * Lógica para apagar os custos e ccs que foram removidos da tabela de custo mas não a tabela em si
     *
     * @param propostaAntiga Proposta antes de ser editada
     * @param proposta       Proposta depois de ser editada
     */
    public void deleteTabelaCustosRows(Proposta propostaAntiga, Proposta proposta) {
        // Iterando as tabelas de custos da proposta antiga
        for (TabelaCusto oldTabelaCusto : propostaAntiga.getTabelaCustos()) {
            // Apaga os custos e ccs que foram removidos da tabela de custo, mas não a tabela de custo em si
            if (proposta.containsTabelaCusto(oldTabelaCusto)) {
                // Apagando os custos que foram removidos
                for (Custo oldCusto : oldTabelaCusto.getCustos()) {
                    if (!proposta.containsCustoInTabelaCusto(oldCusto, proposta.getTabelaCustos())) {
                        custoService.deleteById(oldCusto.getId());
                    }
                }

                // Apagando os CCs que foram removidos
                for (CC oldCc : oldTabelaCusto.getCcs()) {
                    if (!proposta.containsCcInTabelaCusto(oldCc, proposta.getTabelaCustos())) {
                        ccsService.deleteById(oldCc.getId());
                    }
                }
            }
        }
    }

    /**
     * Função para atualizar os campos de uma proposta
     *
     * @param id                  ID da proposta a ser atualizada
     * @param propostaJaCriadaDTO DTO de uma proposta com todos os seus campos atualizados
     * @return ResponseEntity com a proposta atualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody PropostaJaCriadaDTO propostaJaCriadaDTO) {
        Optional<Proposta> propostaOptional = propostaService.findById(id);

        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar uma proposta com este id.");
        }

        Proposta proposta = propostaOptional.get();
        BeanUtils.copyProperties(propostaJaCriadaDTO, proposta, "id");
        proposta.setScore(calcularScore(proposta));

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Função para adicionar um objeto de Historico em uma proposta
     *
     * @param idProposta ID da proposta a ser adicionado o histórico
     * @param usuarioId  ID do usuário que fez a mudança
     * @param acao       String da ação feita na proposta
     * @param documento  Documento PDF da proposta para salvamento de seu estado atual
     * @return ResponseEntity com a proposta editada
     */
    @PutMapping("/add-historico/{idProposta}")
    public ResponseEntity<Object> addHistorico(@PathVariable(value = "idProposta") Long idProposta,
                                               @RequestParam("usuarioId") Long usuarioId,
                                               @RequestParam("acao") String acao,
                                               @RequestParam("documento") MultipartFile documento) {
        Optional<Proposta> propostaOptional = propostaService.findById(idProposta);
        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada!");
        }

        Optional<Usuario> usuarioOptional = usuarioService.findById(usuarioId);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }

        Proposta proposta = propostaOptional.get();
        Usuario usuario = usuarioOptional.get();

        Historico historico = new Historico();
        historico.setAutor(usuario);
        historico.setData(new Date());
        historico.setAcaoRealizada(acao);

        List<Historico> listaHistorico = proposta.getHistoricoProposta();
        historico.setDocumentoMultipart(documento);

        DocumentoHistorico documentoHistorico = historico.getDocumentoHistorico();
        documentoHistorico.setNome(proposta.getTitulo() + " - Versão " + (listaHistorico.size() + 1));
        historico.setDocumentoHistorico(documentoHistoricoService.save(documentoHistorico));

        if (listaHistorico == null) {
            listaHistorico = new ArrayList<>();
        }
        listaHistorico.add(historicoService.save(historico));

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Função para resetar o atributo "presenteEm" na proposta para a string "Nada". Usada quando uma proposta é retirada de alguma pauta
     * ou vai para edição / business_case
     *
     * @param idProposta ID da proposta a ser editada
     * @return ResponseEntity com a proposta editada
     */
    @RequestMapping("/presente/{idProposta}")
    public ResponseEntity<Object> removerPresenca(@PathVariable(value = "idProposta") Long idProposta) {
        Optional<Proposta> propostaOptional = propostaService.findById(idProposta);
        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada!");
        }

        Proposta proposta = propostaOptional.get();
        proposta.setPresenteEm("Solta");
        proposta.setPublicada(null);
        proposta.setParecerComissao(null);
        proposta.setParecerDG(null);
        proposta.setParecerInformacao(null);
        proposta.setParecerInformacaoDG(null);
        proposta.setStatus(Status.ASSESSMENT_APROVACAO);
        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Função para atualizar a proposta ao ser adicionada a uma pauta, atualizando os atributos "publicada", "presenteEm" e "status"
     *
     * @param idProposta ID da proposta a ser atualizada
     * @param publicada  Valor do atributo "publicada" a ser atribuído à proposta
     * @return ResponseEntity com a proposta atualizada
     */
    @RequestMapping("/pauta/{idProposta}")
    public ResponseEntity<Object> updatePauta(@PathVariable(value = "idProposta") Long idProposta,
                                              @RequestParam(value = "publicada") Boolean publicada) {
        Optional<Proposta> propostaOptional = propostaService.findById(idProposta);
        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada!");
        }

        Proposta proposta = propostaOptional.get();
        proposta.setPublicada(publicada);
        proposta.setStatus(Status.ASSESSMENT_COMISSAO);
        proposta.setPresenteEm("Pauta");
        proposta.setParecerComissao(null);
        proposta.setParecerInformacao(null);

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Função para atualizar uma proposta quando ela for adicionada a uma ata ou reprovada, atualizando o parecer da comissão, status e "presenteEm"
     *
     * @param idProposta        ID da proposta a ser atualizada
     * @param parecerGerencia   Parecer da Gerencia da aceitação da proposta
     * @param parecerInformacao Texto do Parecer da Gerencia
     * @return ResponseEntity com a proposta atualizada
     */
    @RequestMapping("/ata/{idProposta}")
    public ResponseEntity<Object> updateCriacaoAta(@PathVariable(value = "idProposta") Long idProposta,
                                                   @RequestParam(value = "parecerComissao") ParecerGerencia parecerGerencia,
                                                   @RequestParam(value = "parecerInformacao") String parecerInformacao) {
        Optional<Proposta> propostaOptional = propostaService.findById(idProposta);
        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada!");
        }

        Proposta proposta = propostaOptional.get();
        proposta.setParecerComissao(parecerGerencia);
        proposta.setParecerInformacao(parecerInformacao);

        switch (parecerGerencia) {
            case APROVADO -> {
                proposta.setStatus(Status.ASSESSMENT_DG);
                proposta.setPresenteEm("Ata");
            }
            case REPROVADO -> {
                proposta.setStatus(Status.CANCELLED);
                proposta.setPresenteEm("Solta");
            }
            case BUSINESS_CASE -> {
                proposta.setStatus(Status.BUSINESS_CASE);
                proposta.setPresenteEm("Solta");
            }
            case MAIS_INFORMACOES -> {
                proposta.setStatus(Status.ASSESSMENT_EDICAO);
                proposta.setPresenteEm("Solta");
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Função para atualizar uma proposta quando uma ata for apreciada pela DG, atualizando o parecer da DG, status e "presenteEm"
     *
     * @param idProposta        ID da proposta a ser atualizada
     * @param parecerGerencia   Parecer da DG da aceitação da proposta
     * @param parecerInformacao Texto do Parecer da DG
     * @return ResponseEntity com a proposta atualizada
     */
    @RequestMapping("/dg/{idProposta}")
    public ResponseEntity<Object> updateDg(@PathVariable(value = "idProposta") Long idProposta,
                                           @RequestParam(value = "parecerComissao") ParecerGerencia parecerGerencia,
                                           @RequestParam(value = "parecerInformacao") String parecerInformacao) {
        Optional<Proposta> propostaOptional = propostaService.findById(idProposta);
        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada!");
        }

        Proposta proposta = propostaOptional.get();
        proposta.setParecerDG(parecerGerencia);
        proposta.setParecerInformacaoDG(parecerInformacao);

        switch (parecerGerencia) {
            case APROVADO -> {
                proposta.setStatus(Status.DONE);
                proposta.setPresenteEm("Ata");
            }
            case REPROVADO -> {
                proposta.setStatus(Status.CANCELLED);
                proposta.setPresenteEm("Solta");
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }


    /**
     * Função para "deletar" uma proposta pelo seu ID, definindo o atributo "visibilidade" para falso
     *
     * @param id ID da proposta a ser atualizada
     * @return ResponseEntity com a proposta atualizada
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {

        Optional<Proposta> propostaOptional = propostaService.findById(id);
        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }

        Proposta proposta = propostaOptional.get();
        proposta.setVisibilidade(false);
        propostaService.save(proposta);

        return ResponseEntity.status(HttpStatus.OK).body(proposta);
    }

}
