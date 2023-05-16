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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/weg_ssm/proposta")
public class PropostaController {

    private PropostaService propostaService;
    private UsuarioService usuarioService;

    private ResponsavelNegocioService responsavelNegocioService;
    private TabelaCustoService tabelaCustoService;
    private CustoService custoService;
    private CCsService ccsService;
    private AnexoService anexoService;

    private BeneficioService beneficioService;
    private DemandaService demandaService;

    private HistoricoService historicoService;
    private DocumentoHistoricoService documentoHistoricoService;

    /**
     * Método GET para listar todas as propostas
     */
    @GetMapping
    public ResponseEntity<List<Proposta>> findAll() {
        return ResponseEntity.status(200).body(propostaService.findAll());
    }

    /**
     * Método GET para listar uma proposta específica através de um id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!propostaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }

        return ResponseEntity.status(200).body(propostaService.findById(id));
    }

    /**
     * Função principal para buscar propostas, retornando uma página com as propostas seguindo o filtro utilizado
     *
     * @param codigoPPM        - Código PPM da proposta
     * @param pageable         - Objeto que contém as informações da páginação
     * @param titulo           - Título da proposta (usado na barra de pesquisa)
     * @param solicitanteJson  - Solicitante da proposta (usado no modal de filtro)
     * @param gerenteJson      - Gerente da proposta (usado no modal de filtro)
     * @param forumJson        - Fórum da proposta (usado no modal de filtro)
     * @param departamentoJson - Departamento da proposta (usado no modal de filtro)
     * @param tamanho          - Tamanho da proposta (usado no modal de filtro)
     * @param status           - Status da proposta (usado sempre quando é feita a busca)
     * @return - Retorna uma página com as propostas encontradas
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Proposta>> findPage(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) Long codigoPPM,
            @RequestParam(required = false) String titulo,
            @RequestParam(value = "solicitante", required = false) String solicitanteJson,
            @RequestParam(value = "gerente", required = false) String gerenteJson,
            @RequestParam(value = "forum", required = false) String forumJson,
            @RequestParam(value = "departamento", required = false) String departamentoJson,
            @RequestParam(required = false) String tamanho,
            @RequestParam(value = "analista", required = false) String analistaJson,
            @RequestParam(required = false) Long id,
            @RequestParam Status status,
            @RequestParam(required = false, value = "emAta") Boolean emAta,
            @RequestParam(required = false, value = "emPauta") Boolean emPauta
    ) {
        Usuario solicitante = new UsuarioUtil().convertJsonToModel(solicitanteJson);
        Usuario gerente = new UsuarioUtil().convertJsonToModel(gerenteJson);
        Usuario analista = new UsuarioUtil().convertJsonToModel(analistaJson);
        Forum forum = new ForumUtil().convertJsonToModel(forumJson);
        Departamento departamento = new DepartamentoUtil().convertJsonToModel(departamentoJson);

        if (emAta != null) {
            if (emPauta != null) {
                if (analista != null) {
                    if (codigoPPM != null) {
                        if (status != null) {
                            if (titulo != null && !titulo.isEmpty()) {
                                if (gerente != null) {
                                    if (forum != null) {
                                        if (departamento != null) {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitanteoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, titulo, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitanteAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitanteAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitanteAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitanteAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndSolicitanteAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndEmPautaAndEmAta(
                                                                    true, status, analista, codigoPPM, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (titulo != null && titulo.isEmpty()) {
                                if (gerente != null) {
                                    if (forum != null) {
                                        if (departamento != null) {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, titulo, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndEmPautaAndEmAta(
                                                                    true, analista, codigoPPM, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndEmPautaAndEmAta(
                                                                    true, status, analista, titulo, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndForumAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndGerenteAndEmPautaAndEmAta(
                                                                    true, status, analista, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndForumAndEmPautaAndEmAta(
                                                                    true, status, analista, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, analista, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, analista, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, analista, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndEmPautaAndEmAta(
                                                                    true, status, analista, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndEmPautaAndEmAta(
                                                                    true, analista, titulo, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, titulo, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, titulo, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndEmPautaAndEmAta(
                                                                    true, analista, titulo, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndForumAndEmPautaAndEmAta(
                                                                    true, analista, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, gerente, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndGerenteAndEmPautaAndEmAta(
                                                                    true, analista, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndForumAndTamanhoAndAndEmPautaAndEmAta(
                                                                    true, analista, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndForumAndEmPautaAndEmAta(
                                                                    true, analista, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, analista, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTamanhoAndEmPautaAndEmAta(
                                                                    true, analista, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, analista, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndEmPautaAndEmAta(
                                                                    true, analista, emPauta, emAta, pageable
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
                    if (codigoPPM != null) {
                        if (status != null) {
                            if (titulo != null && !titulo.isEmpty()) {
                                if (gerente != null) {
                                    if (forum != null) {
                                        if (departamento != null) {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndForumAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndGerenteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, gerente, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, forum, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, forum, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, forum, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, forum, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, forum, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, forum, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndForumAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, forum, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndForumAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, forum, emPauta, emAta, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, departamento, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, departamento, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, departamento, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndDepartamentoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, departamento, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, tamanho, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndTamanhoAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, tamanho, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndSolicitanteAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, solicitante, emPauta, emAta, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndCodigoPPMAndTituloContainingAndEmPautaAndEmAta(
                                                                    true, status, codigoPPM, titulo, emPauta, emAta, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                }
            } else {
                if (analista != null) {

                } else {

                }
            }
        } else {
            if (emPauta != null) {
                if (analista != null) {

                } else {

                }
            } else {
                if (analista != null) {
                    if (codigoPPM != null) {
                        if (status != null) {
                            if (titulo != null && !titulo.isEmpty()) {
                                if (gerente != null) {
                                    if (forum != null) {
                                        if (departamento != null) {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForum(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerente(
                                                                    true, status, analista, codigoPPM, titulo, gerente, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamento(
                                                                    true, status, analista, codigoPPM, titulo, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndForum(
                                                                    true, status, analista, codigoPPM, titulo, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamento(
                                                                    true, status, analista, codigoPPM, titulo, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContaining(
                                                                    true, status, analista, codigoPPM, titulo, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, status, analista, codigoPPM, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamento(
                                                                    true, status, analista, codigoPPM, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanho(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitante(
                                                                    true, status, analista, codigoPPM, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndForum(
                                                                    true, status, analista, codigoPPM, gerente, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(
                                                                    true, status, analista, codigoPPM, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndDepartamento(
                                                                    true, status, analista, codigoPPM, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndTamanho(
                                                                    true, status, analista, codigoPPM, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerenteAndSolicitante(
                                                                    true, status, analista, codigoPPM, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndGerente(
                                                                    true, status, analista, codigoPPM, gerente, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanho(
                                                                    true, status, analista, codigoPPM, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndDepartamento(
                                                                    true, status, analista, codigoPPM, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndTamanho(
                                                                    true, status, analista, codigoPPM, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForumAndSolicitante(
                                                                    true, status, analista, codigoPPM, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndForum(
                                                                    true, status, analista, codigoPPM, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndTamanho(
                                                                    true, status, analista, codigoPPM, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndDepartamento(
                                                                    true, status, analista, codigoPPM, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTamanho(
                                                                    true, status, analista, codigoPPM, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndSolicitante(
                                                                    true, status, analista, codigoPPM, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPM(
                                                                    true, status, analista, codigoPPM, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForum(
                                                                    true, analista, codigoPPM, titulo, gerente, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(
                                                                    true, analista, codigoPPM, titulo, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(
                                                                    true, analista, codigoPPM, titulo, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(
                                                                    true, analista, codigoPPM, titulo, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndGerente(
                                                                    true, analista, codigoPPM, titulo, gerente, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(
                                                                    true, analista, codigoPPM, titulo, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndDepartamento(
                                                                    true, analista, codigoPPM, titulo, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndTamanho(
                                                                    true, analista, codigoPPM, titulo, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForumAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndForum(
                                                                    true, analista, codigoPPM, titulo, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(
                                                                    true, analista, codigoPPM, titulo, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndDepartamento(
                                                                    true, analista, codigoPPM, titulo, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndTamanho(
                                                                    true, analista, codigoPPM, titulo, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContainingAndSolicitante(
                                                                    true, analista, codigoPPM, titulo, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTituloContaining(
                                                                    true, analista, codigoPPM, titulo, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, analista, codigoPPM, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, analista, codigoPPM, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndDepartamento(
                                                                    true, analista, codigoPPM, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndTamanho(
                                                                    true, analista, codigoPPM, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForumAndSolicitante(
                                                                    true, analista, codigoPPM, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndForum(
                                                                    true, analista, codigoPPM, gerente, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(
                                                                    true, analista, codigoPPM, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, analista, codigoPPM, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndDepartamento(
                                                                    true, analista, codigoPPM, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndTamanho(
                                                                    true, analista, codigoPPM, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerenteAndSolicitante(
                                                                    true, analista, codigoPPM, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndGerente(
                                                                    true, analista, codigoPPM, gerente, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndTamanho(
                                                                    true, analista, codigoPPM, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamentoAndSolicitante(
                                                                    true, analista, codigoPPM, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndDepartamento(
                                                                    true, analista, codigoPPM, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndTamanho(
                                                                    true, analista, codigoPPM, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForumAndSolicitante(
                                                                    true, analista, codigoPPM, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndForum(
                                                                    true, analista, codigoPPM, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndTamanho(
                                                                    true, analista, codigoPPM, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamentoAndSolicitante(
                                                                    true, analista, codigoPPM, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndDepartamento(
                                                                    true, analista, codigoPPM, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanhoAndSolicitante(
                                                                    true, analista, codigoPPM, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndTamanho(
                                                                    true, analista, codigoPPM, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPMAndSolicitante(
                                                                    true, analista, codigoPPM, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndCodigoPPM(
                                                                    true, analista, codigoPPM, pageable
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
                            if (titulo != null) {
                                if (gerente != null) {
                                    if (forum != null) {
                                        if (departamento != null) {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, status, analista, titulo, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, status, analista, titulo, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, status, analista, titulo, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(
                                                                    true, status, analista, titulo, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(
                                                                    true, status, analista, titulo, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndForum(
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(
                                                                    true, status, analista, titulo, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamento(
                                                                    true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(
                                                                    true, status, analista, titulo, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndTamanho(
                                                                    true, status, analista, titulo, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndSolicitante(
                                                                    true, status, analista, titulo, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerente(
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, titulo, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(
                                                                    true, status, analista, titulo, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(
                                                                    true, status, analista, titulo, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndDepartamento(
                                                                    true, status, analista, titulo, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(
                                                                    true, status, analista, titulo, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndTamanho(
                                                                    true, status, analista, titulo, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForumAndSolicitante(
                                                                    true, status, analista, titulo, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndForum(
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
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, status, analista, titulo, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(
                                                                    true, status, analista, titulo, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(
                                                                    true, status, analista, titulo, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndDepartamento(
                                                                    true, status, analista, titulo, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(
                                                                    true, status, analista, titulo, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndTamanho(
                                                                    true, status, analista, titulo, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndSolicitante(
                                                                    true, status, analista, titulo, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContaining(
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
                                                                    true, status, analista, codigoPPM, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, analista, titulo, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, analista, titulo, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(
                                                                    true, analista, titulo, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, analista, titulo, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(
                                                                    true, analista, titulo, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(
                                                                    true, analista, titulo, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndForum(
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(
                                                                    true, analista, titulo, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, analista, titulo, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndDepartamento(
                                                                    true, analista, titulo, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(
                                                                    true, analista, titulo, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndTamanho(
                                                                    true, analista, titulo, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerenteAndSolicitante(
                                                                    true, analista, titulo, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndGerente(
                                                                    true, analista, codigoPPM, titulo, gerente, pageable
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, titulo, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(
                                                                    true, analista, titulo, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(
                                                                    true, analista, titulo, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndDepartamento(
                                                                    true, analista, titulo, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(
                                                                    true, analista, titulo, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndTamanho(
                                                                    true, analista, codigoPPM, titulo, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForumAndSolicitante(
                                                                    true, analista, titulo, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndForum(
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
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, analista, titulo, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(
                                                                    true, analista, titulo, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(
                                                                    true, analista, titulo, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndDepartamento(
                                                                    true, analista, titulo, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(
                                                                    true, analista, titulo, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndTamanho(
                                                                    true, analista, titulo, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContainingAndSolicitante(
                                                                    true, analista, titulo, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndAnalistaAndTituloContaining(
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
                                                                    true, analista, codigoPPM, departamento, tamanho, solicitante, pageable
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
                    }
                } else {
                    if (codigoPPM != null) {
                        if (status != null) {
                            if (titulo != null && !titulo.isEmpty()) {
                                if (gerente != null) {
                                    if (forum != null) {
                                        if (departamento != null) {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, status, titulo, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(
                                                                    true, codigoPPM, status, titulo, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(
                                                                    true, codigoPPM, status, titulo, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(
                                                                    true, codigoPPM, status, titulo, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndForum(
                                                                    true, codigoPPM, status, titulo, gerente, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, status, titulo, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndDepartamento(
                                                                    true, codigoPPM, status, titulo, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndTamanho(
                                                                    true, codigoPPM, status, titulo, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerenteAndSolicitante(
                                                                    true, codigoPPM, status, titulo, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndGerente(
                                                                    true, codigoPPM, status, titulo, gerente, pageable
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, status, titulo, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndDepartamento(
                                                                    true, codigoPPM, status, titulo, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndTamanho(
                                                                    true, codigoPPM, status, titulo, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForumAndSolicitante(
                                                                    true, codigoPPM, status, titulo, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndForum(
                                                                    true, codigoPPM, status, titulo, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, status, titulo, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndDepartamento(
                                                                    true, codigoPPM, status, titulo, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, titulo, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndTamanho(
                                                                    true, codigoPPM, status, titulo, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContainingAndSolicitante(
                                                                    true, codigoPPM, status, titulo, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTituloContaining(
                                                                    true, codigoPPM, status, titulo, pageable
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, status, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, status, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndDepartamento(
                                                                    true, codigoPPM, status, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndTamanho(
                                                                    true, codigoPPM, status, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForumAndSolicitante(
                                                                    true, codigoPPM, status, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndForum(
                                                                    true, codigoPPM, status, gerente, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, status, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, status, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndDepartamento(
                                                                    true, codigoPPM, status, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndTamanho(
                                                                    true, codigoPPM, status, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerenteAndSolicitante(
                                                                    true, codigoPPM, status, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndGerente(
                                                                    true, codigoPPM, status, gerente, pageable
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, status, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, status, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndDepartamento(
                                                                    true, codigoPPM, status, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndTamanho(
                                                                    true, codigoPPM, status, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndForumAndSolicitante(
                                                                    true, codigoPPM, status, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndForum(
                                                                    true, codigoPPM, status, forum, pageable
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, status, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, status, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndDepartamento(
                                                                    true, codigoPPM, status, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, status, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndTamanho(
                                                                    true, codigoPPM, status, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatusAndSolicitante(
                                                                    true, codigoPPM, status, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndStatus(
                                                                    true, codigoPPM, status, pageable
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
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamento(
                                                                    true, codigoPPM, titulo, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndTamanho(
                                                                    true, codigoPPM, titulo, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForumAndSolicitante(
                                                                    true, codigoPPM, titulo, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndForum(
                                                                    true, codigoPPM, titulo, gerente, forum, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        }
                                    } else {
                                        if (departamento != null) {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, titulo, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, titulo, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndDepartamento(
                                                                    true, codigoPPM, titulo, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, titulo, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndTamanho(
                                                                    true, codigoPPM, titulo, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerenteAndSolicitante(
                                                                    true, codigoPPM, titulo, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndGerente(
                                                                    true, codigoPPM, titulo, gerente, pageable
                                                            )
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, titulo, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, titulo, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndDepartamento(
                                                                    true, codigoPPM, titulo, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, titulo, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndTamanho(
                                                                    true, codigoPPM, titulo, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForumAndSolicitante(
                                                                    true, codigoPPM, titulo, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndForum(
                                                                    true, codigoPPM, titulo, forum, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        }
                                    } else {
                                        if (departamento != null) {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, titulo, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, titulo, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, titulo, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndDepartamento(
                                                                    true, codigoPPM, titulo, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, titulo, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndTamanho(
                                                                    true, codigoPPM, titulo, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContainingAndSolicitante(
                                                                    true, codigoPPM, titulo, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTituloContaining(
                                                                    true, codigoPPM, titulo, pageable
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
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, gerente, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, gerente, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, gerente, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndDepartamento(
                                                                    true, codigoPPM, gerente, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, gerente, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndTamanho(
                                                                    true, codigoPPM, gerente, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndForumAndSolicitante(
                                                                    true, codigoPPM, gerente, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndForum(
                                                                    true, codigoPPM, gerente, forum, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        }
                                    } else {
                                        if (departamento != null) {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, gerente, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, gerente, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, gerente, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndDepartamento(
                                                                    true, codigoPPM, gerente, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, gerente, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndTamanho(
                                                                    true, codigoPPM, gerente, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerenteAndSolicitante(
                                                                    true, codigoPPM, gerente, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndGerente(
                                                                    true, codigoPPM, gerente, pageable
                                                            )
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
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, forum, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, forum, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndForumAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, forum, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndForumAndDepartamento(
                                                                    true, codigoPPM, forum, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndForumAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, forum, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndForumAndTamanho(
                                                                    true, codigoPPM, forum, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndForumAndSolicitante(
                                                                    true, codigoPPM, forum, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndForum(
                                                                    true, codigoPPM, forum, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        }
                                    } else {
                                        if (departamento != null) {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, departamento, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndDepartamentoAndTamanho(
                                                                    true, codigoPPM, departamento, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndDepartamentoAndSolicitante(
                                                                    true, codigoPPM, departamento, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndDepartamento(
                                                                    true, codigoPPM, departamento, pageable
                                                            )
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTamanhoAndSolicitante(
                                                                    true, codigoPPM, tamanho, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndTamanho(
                                                                    true, codigoPPM, tamanho, pageable
                                                            )
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPMAndSolicitante(
                                                                    true, codigoPPM, solicitante, pageable
                                                            )
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndCodigoPPM(
                                                                    true, codigoPPM, pageable
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
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(true, status, titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(true, status, titulo, gerente, forum, departamento, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(true, status, titulo, gerente, forum, departamento, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndDepartamento(true, status, titulo, gerente, forum, departamento, pageable)
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(true, status, titulo, gerente, forum, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndTamanho(true, status, titulo, gerente, forum, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForumAndSolicitante(true, status, titulo, gerente, forum, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndForum(true, status, titulo, gerente, forum, pageable)
                                                    );
                                                }
                                            }
                                        }
                                    } else {
                                        if (departamento != null) {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(true, status, titulo, gerente, departamento, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(true, status, titulo, gerente, departamento, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(true, status, titulo, gerente, departamento, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndDepartamento(true, status, titulo, gerente, departamento, pageable)
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(true, status, titulo, gerente, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndTamanho(true, status, titulo, gerente, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerenteAndSolicitante(true, status, titulo, gerente, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndGerente(true, status, titulo, gerente, pageable)
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
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(true, status, titulo, forum, departamento, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(true, status, titulo, forum, departamento, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(true, status, titulo, forum, departamento, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndForumAndDepartamento(true, status, titulo, forum, departamento, pageable)
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(true, status, titulo, forum, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndForumAndTamanho(true, status, titulo, forum, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndForumAndSolicitante(true, status, titulo, forum, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndForum(true, status, titulo, forum, pageable)
                                                    );
                                                }
                                            }
                                        }
                                    } else {
                                        if (departamento != null) {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(true, status, titulo, departamento, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndTamanho(true, status, titulo, departamento, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndDepartamentoAndSolicitante(true, status, titulo, departamento, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndDepartamento(true, status, titulo, departamento, pageable)
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndTamanhoAndSolicitante(true, status, titulo, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndTamanho(true, status, titulo, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContainingAndSolicitante(true, status, titulo, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndStatusAndTituloContaining(true, status, titulo, pageable)
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
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(true, titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(true, titulo, gerente, forum, departamento, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(true, titulo, gerente, forum, departamento, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndDepartamento(true, titulo, gerente, forum, departamento, pageable)
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(true, titulo, gerente, forum, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndTamanho(true, titulo, gerente, forum, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndForumAndSolicitante(true, titulo, gerente, forum, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndForum(true, titulo, gerente, forum, pageable)
                                                    );
                                                }
                                            }
                                        }
                                    } else {
                                        if (departamento != null) {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(true, titulo, gerente, departamento, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndTamanho(true, titulo, gerente, departamento, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(true, titulo, gerente, departamento, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndDepartamento(true, titulo, gerente, departamento, pageable)
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndTamanhoAndSolicitante(true, titulo, gerente, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndTamanho(true, titulo, gerente, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerenteAndSolicitante(true, titulo, gerente, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndGerente(true, titulo, gerente, pageable)
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
                                                            propostaService.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(true, titulo, forum, departamento, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndTamanho(true, titulo, forum, departamento, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndForumAndDepartamentoAndSolicitante(true, titulo, forum, departamento, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndForumAndDepartamento(true, titulo, forum, departamento, pageable)
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndForumAndTamanhoAndSolicitante(true, titulo, forum, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndForumAndTamanho(true, titulo, forum, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndForumAndSolicitante(true, titulo, forum, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndForum(true, titulo, forum, pageable)
                                                    );
                                                }
                                            }
                                        }
                                    } else {
                                        if (departamento != null) {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(true, titulo, departamento, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndDepartamentoAndTamanho(true, titulo, departamento, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndDepartamentoAndSolicitante(true, titulo, departamento, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndDepartamento(true, titulo, departamento, pageable)
                                                    );
                                                }
                                            }
                                        } else {
                                            if (tamanho != null && !tamanho.isEmpty()) {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndTamanhoAndSolicitante(true, titulo, tamanho, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndTamanho(true, titulo, tamanho, pageable)
                                                    );
                                                }
                                            } else {
                                                if (solicitante != null) {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContainingAndSolicitante(true, titulo, solicitante, pageable)
                                                    );
                                                } else {
                                                    return ResponseEntity.status(HttpStatus.OK).body(
                                                            propostaService.findByVisibilidadeAndTituloContaining(true, titulo, pageable)
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
        }

        return null;
    }

    /**
     * Método GET para listar uma proposta específica através de um código PPM
     */
    @GetMapping("/ppm/{ppm}")
    public ResponseEntity<Object> findByPpm(@PathVariable(value = "ppm") Long ppm) {
        if (!propostaService.existsByPpm(ppm)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este ppm.");
        }

        return ResponseEntity.status(200).body(propostaService.findByPpm(ppm));
    }

    /**
     * Método POST para criar uma proposta no banco de dados
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestParam(value = "proposta") String propostaJSON,
                                       @RequestParam(value = "idsAnexos", required = false) List<String> listaIdsAnexos,
                                       @RequestParam(value = "escopo", required = false) byte[] escopoProposta) {
        PropostaUtil propostaUtil = new PropostaUtil();
        Proposta proposta = propostaUtil.convertJsonToModel(propostaJSON);
        proposta.setEscopo(escopoProposta);

        Demanda demanda = demandaService.findById(proposta.getDemanda().getId()).get();
        List<Historico> historicoProposta = new ArrayList<>();
        for (Historico historico : demanda.getHistoricoDemanda()) {
            historicoProposta.add(historicoService.save(historico));
        }
        proposta.setHistoricoProposta(historicoProposta);

        proposta.setData(new Date());
        proposta.setVisibilidade(true);

        for (Beneficio beneficio : proposta.getBeneficios()) {
            beneficioService.save(beneficio);
        }

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

        ArrayList<Anexo> listaAnexos = new ArrayList<>();
        if (listaIdsAnexos != null) {
            for (String id : listaIdsAnexos) {
                listaAnexos.add(anexoService.findById(Long.parseLong(id)));
            }
        }
        proposta.setAnexo(listaAnexos);

        return ResponseEntity.status(HttpStatus.CREATED).body(propostaService.save(proposta));
    }

    /**
     * Método PUT para atualizar uma proposta no banco de dados com novos dados, sejam anexos, benefícios ou tabelas de custos a serem salvos
     *
     * @param id
     * @param propostaJSON
     * @param beneficiosJSON
     * @param tabelaCustosJSON
     * @param listaIdsAnexos
     * @return
     */
    @PutMapping("/update-novos-dados/{id}")
    public ResponseEntity<Object> updateComNovosDados(@PathVariable(value = "id") Long id,
                                                      @RequestParam(value = "proposta") String propostaJSON,
                                                      @RequestParam(value = "beneficios", required = false) List<String> beneficiosJSON,
                                                      @RequestParam(value = "tabelasCustos", required = false) List<String> tabelaCustosJSON,
                                                      @RequestParam(value = "listIdsAnexos", required = false) List<String> listaIdsAnexos
    ) {
        Optional<Proposta> propostaOptional = propostaService.findById(id);

        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar uma proposta com este id.");
        }

        PropostaUtil propostaUtil = new PropostaUtil();
        Proposta proposta = propostaUtil.convertJaCriadaJsonToModel(propostaJSON);
        proposta.setId(id);

        System.out.println("Proposta: " + proposta.toString());

//        BeneficioUtil beneficioUtil = new BeneficioUtil();
//        List<Beneficio> beneficios = new ArrayList<>();
//
//        for (String beneficioJSON : beneficiosJSON) {
//            Beneficio beneficio = beneficioUtil.convertJsonToModel(beneficioJSON);
//            beneficios.add(beneficio);
//        }
//
//        TabelaCustoUtil tabelaCustoUtil = new TabelaCustoUtil();
//        List<TabelaCusto> tabelaCustos = new ArrayList<>();
//
//        for (String tabelaCustoJSON : tabelaCustosJSON) {
//            TabelaCusto tabelaCusto = tabelaCustoUtil.convertJsonToModel(tabelaCustoJSON);
//            tabelaCustos.add(tabelaCusto);
//        }
//
//        for (Beneficio beneficio : beneficios) {
//            Beneficio newBeneficio = new Beneficio();
//            BeanUtils.copyProperties(beneficio, newBeneficio, "id");
//            proposta.getBeneficios().add(beneficioService.save(beneficio));
//        }
//
//        for (TabelaCusto tabelaCusto : tabelaCustos) {
//            List<Custo> novosCustos = new ArrayList<>();
//            List<CC> novosCCs = new ArrayList<>();
//
//            for (Custo custo : tabelaCusto.getCustos()) {
//                Custo newCusto = new Custo();
//                BeanUtils.copyProperties(custo, newCusto, "id");
//                novosCustos.add(custoService.save(newCusto));
//            }
//
//            for (CC cc : tabelaCusto.getCcs()) {
//                CC newCC = new CC();
//                BeanUtils.copyProperties(cc, newCC, "id");
//                novosCCs.add(ccsService.save(newCC));
//            }
//
//            tabelaCusto.setCustos(novosCustos);
//            tabelaCusto.setCcs(novosCCs);
//
//            TabelaCusto newTabelaCusto = new TabelaCusto();
//            BeanUtils.copyProperties(tabelaCusto, newTabelaCusto, "id");
//            proposta.getTabelaCustos().add(tabelaCustoService.save(tabelaCusto));
//        }
//
//        propostaService.save(proposta);
//
//        if (listaIdsAnexos != null) {
//            for (String idAnexo : listaIdsAnexos) {
//                proposta.getAnexo().add(anexoService.findById(Long.parseLong(idAnexo)));
//            }
//        }

        return null;
//        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Método PUT para atualizar uma proposta no banco de dados, através de um id, recebendo a propostaDTO como JSON
     *
     * @param id
     * @param propostaJaCriadaDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody PropostaJaCriadaDTO propostaJaCriadaDTO) {
        Optional<Proposta> propostaOptional = propostaService.findById(id);

        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível encontrar uma proposta com este id.");
        }

        Proposta proposta = propostaOptional.get();
        BeanUtils.copyProperties(propostaJaCriadaDTO, proposta, "id");

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    @PutMapping("/add-historico/{idProposta}")
    public ResponseEntity<Proposta> addHistorico(@PathVariable(value = "idProposta") Long idProposta,
                                                 @RequestParam("usuarioId") Long usuarioId,
                                                 @RequestParam("acao") String acao,
                                                 @RequestParam("documento") MultipartFile documento) {
        Usuario usuario = usuarioService.findById(usuarioId).get();
        Proposta proposta = propostaService.findById(idProposta).get();

        Historico historico = new Historico();
        historico.setAutor(usuario);
        historico.setData(new Date());
        historico.setAcaoRealizada(acao);

        List<Historico> listaHistorico = proposta.getHistoricoProposta();
        historico.setDocumentoMultipart(documento);

        DocumentoHistorico documentoHistorico = historico.getDocumento();
        documentoHistorico.setNome(proposta.getTitulo() + " - Versão " + (listaHistorico.size() + 1));
        historico.setDocumento(documentoHistoricoService.save(documentoHistorico));

        if (listaHistorico != null) {
            listaHistorico.add(historicoService.save(historico));
        } else {
            listaHistorico = new ArrayList<>();
            listaHistorico.add(historicoService.save(historico));
        }

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Método DELETE para modificar a visibilidade de uma proposta
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!propostaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }

        Proposta proposta = propostaService.findById(id).get();
        proposta.setVisibilidade(false);
        propostaService.save(proposta);

        return ResponseEntity.status(HttpStatus.OK).body(proposta);
    }

    /**
     * Método DELETE para remover uma proposta do banco de dados
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!propostaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }

        propostaService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Proposta deletada com sucesso.");
    }

}
