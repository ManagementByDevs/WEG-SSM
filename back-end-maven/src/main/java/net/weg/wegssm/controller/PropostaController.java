package net.weg.wegssm.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
import java.util.*;

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

                } else {

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
                                                                true, status, analista, titulo, gerente, forum, departamento, pageable
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
                                                                true, status, analista, titulo, gerente, departamento, tamanho, solicitante, pageable
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
                                                                true, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                        )
                                                );
                                            } else {
                                                return ResponseEntity.status(HttpStatus.OK).body(
                                                        propostaService.findByVisibilidadeAndStatusAndAnalistaAndTituloContainingAndGerenteAndDepartamento(
                                                                true, status, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
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
                                                                true, analista, titulo, forum, tamanho, pageable
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
    public ResponseEntity<Object> save(@RequestParam(value = "proposta") String propostaJSON) {

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

        return ResponseEntity.status(HttpStatus.CREATED).body(propostaService.save(proposta));
    }

    /**
     * Função utilizada para somente atualizar o status de uma proposta, recebendo como parâmetros o id e o novo status da proposta.
     */
    @PutMapping("/{id}/{status}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable(value = "id") Long id,
                                                  @PathVariable(value = "status") Status status) {
        Optional<Proposta> propostaOptional = propostaService.findById(id);
        if(propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada!");
        }

        Proposta proposta = propostaOptional.get();
        proposta.setStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    /**
     * Método PUT para atualizar uma proposta no banco de dados com novos dados, sejam anexos, benefícios ou tabelas de custos a serem salvos
     *
     * @param id
     * @param propostaJSON     - Proposta com os novos dados
     * @param novaPropostaJSON - Proposta com Benefícios e Tabelas de Custos a serem salvas no banco de dados
     * @param listaIdsAnexos   - Lista de IDs de anexos que já pertenciam à proposta
     * @param escopoProposta   - Escopo da proposta
     * @return ResponseEntity<Object> - Proposta atualizada ou mensagem de erro
     */
    @PutMapping("/update-novos-dados/{id}")
    public ResponseEntity<Object> updateComNovosDados(@PathVariable(value = "id") Long id,
                                                      @RequestParam(value = "proposta") String propostaJSON,
                                                      @RequestParam(value = "propostaComDadosNovos", required = false) String novaPropostaJSON,
                                                      @RequestParam(value = "listaIdsAnexos", required = false) List<String> listaIdsAnexos,
                                                      @RequestParam(value = "escopo", required = false) byte[] escopoProposta,
                                                      @RequestParam(value = "listaAnexosNovos", required = false) List<MultipartFile> files
    ) {
        Optional<Proposta> propostaOptional = propostaService.findById(id);

        if (propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar uma proposta com este id.");
        }

        PropostaUtil propostaUtil = new PropostaUtil();
        Proposta proposta = propostaUtil.convertJaCriadaJsonToModel(propostaJSON);

        proposta.setId(id);

        if (escopoProposta != null) {
            proposta.setEscopo(escopoProposta);
        }

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

//        System.out.println("Última proposta: " + proposta);

        /** Lógica para apagar objetos que foram removidos da proposta
         * São eles: CCs, Custos, Tabelas de Custos, Anexos, BusBeneficiadas
         */
        deleteAllObjectsRemoved(proposta);

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    public void deleteAllObjectsRemoved(Proposta proposta) {
        Proposta propostaAntiga = propostaService.findById(proposta.getId()).get();

        // Deletando as Tabelas de Custos removidos e seus respectivos custos e ccs
        for (TabelaCusto oldTabelaCusto : propostaAntiga.getTabelaCustos()) {
            if (!proposta.containsTabelaCusto(oldTabelaCusto)) {

                // Removendo os custos da tabela de custos
                for (Custo custo : oldTabelaCusto.getCustos()) {
                    custoService.deleteById(custo.getId());
                }

                // Removendo os CCs da tabela de custos
                for (CC cc : oldTabelaCusto.getCcs()) {
                    ccsService.deleteById(cc.getId());
                }

                tabelaCustoService.deleteById(oldTabelaCusto.getId());
            }
        }
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

        DocumentoHistorico documentoHistorico = historico.getDocumentoHistorico();
        documentoHistorico.setNome(proposta.getTitulo() + " - Versão " + (listaHistorico.size() + 1));
        historico.setDocumentoHistorico(documentoHistoricoService.save(documentoHistorico));

        if (listaHistorico != null) {
            listaHistorico.add(historicoService.save(historico));
        } else {
            listaHistorico = new ArrayList<>();
            listaHistorico.add(historicoService.save(historico));
        }

        return ResponseEntity.status(HttpStatus.OK).body(propostaService.save(proposta));
    }

    @RequestMapping("/pauta/{idProposta}")
    public ResponseEntity<Object> updatePauta(@PathVariable(value = "idProposta") Long idProposta,
                                              @RequestParam(value = "publicada") Boolean publicada,
                                              @RequestParam(value = "status") Status status) {
        Optional<Proposta> propostaOptional = propostaService.findById(idProposta);
        if(propostaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não encontrada!");
        }

        Proposta proposta = propostaOptional.get();
        proposta.setPublicada(publicada);
        proposta.setStatus(status);
        proposta.setEmPauta(true);
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
