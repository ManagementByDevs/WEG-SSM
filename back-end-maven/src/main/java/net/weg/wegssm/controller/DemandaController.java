package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.*;
import net.weg.wegssm.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Classe controller da demanda
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm/demanda")
public class DemandaController {

    /**
     * Service da Demanda
     */
    private DemandaService demandaService;

    /**
     * Service do Usuário
     */
    private UsuarioService usuarioService;

    /**
     * Service do Histórico
     */
    private HistoricoService historicoService;

    /**
     * Service dos Benefícios
     */
    private BeneficioService beneficioService;

    /**
     * Método GET para buscar todas as demandas
     *
     * @return - Retorno da lista de demandas
     */
    @GetMapping
    public ResponseEntity<List<Demanda>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.findAll());
    }

    /**
     * Método GET para buscar uma demanda específica através do id
     *
     * @param pageable    - Objeto que contém as informações da paginação
     * @param id          - ID da demanda
     * @param usuarioJson - Solicitante que está buscando a demanda
     * @param status      - Status da demanda
     * @return - Retorno da paginação filtrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Page<Demanda>> findById(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                  @PathVariable(value = "id") Long id,
                                                  @RequestParam(value = "usuario", required = false) String usuarioJson,
                                                  @RequestParam(value = "status", required = false) Status status) {
        UsuarioUtil usuarioUtil = new UsuarioUtil();
        Usuario usuario = usuarioUtil.convertJsonToModel(usuarioJson);

        if (usuario != null) {
            if (status != null) {
                return ResponseEntity.status(HttpStatus.OK).body(demandaService.findByIdAndSolicitanteAndStatus(id, usuario, status, pageable));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(demandaService.findByIdAndSolicitante(id, usuario, pageable));
            }
        } else {
            if (status != null) {
                return ResponseEntity.status(HttpStatus.OK).body(demandaService.findByIdAndStatus(id, status, pageable));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(demandaService.findById(id, pageable));
            }
        }
    }

    /**
     * Função principal para buscar demandas, retornando uma página com as demandas seguindo o filtro utilizado
     *
     * @param pageable         - Objeto que contém as informações da páginação
     * @param id               - ID da demanda / número sequencial
     * @param titulo           - Título da demanda (usado na barra de pesquisa)
     * @param solicitanteJson  - Solicitante da demanda (usado no modal de filtro)
     * @param gerenteJson      - Gerente da demanda (usado no modal de filtro)
     * @param forumJson        - Fórum da demanda (usado no modal de filtro)
     * @param departamentoJson - Departamento da demanda (usado no modal de filtro)
     * @param tamanho          - Tamanho da demanda (usado no modal de filtro)
     * @param status           - Status da demanda (usado sempre quando é feita a busca)
     * @return - Retorna uma página com as demandas encontradas
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Demanda>> findPage(@PageableDefault(size = 20, sort = "score", direction = Sort.Direction.DESC) Pageable pageable,
                                                  @RequestParam(value = "id", required = false) Long id,
                                                  @RequestParam(value = "titulo", required = false) String titulo,
                                                  @RequestParam(value = "solicitante", required = false) String solicitanteJson,
                                                  @RequestParam(value = "gerente", required = false) String gerenteJson,
                                                  @RequestParam(value = "forum", required = false) String forumJson,
                                                  @RequestParam(value = "departamento", required = false) String departamentoJson,
                                                  @RequestParam(value = "tamanho", required = false) String tamanho,
                                                  @RequestParam(value = "analista", required = false) String analistaJson,
                                                  @RequestParam(value = "status", required = false) Status status
    ) {
        Usuario solicitante = new UsuarioUtil().convertJsonToModel(solicitanteJson);
        Usuario gerente = new UsuarioUtil().convertJsonToModel(gerenteJson);
        Usuario analista = new UsuarioUtil().convertJsonToModel(analistaJson);
        Forum forum = new ForumUtil().convertJsonToModel(forumJson);
        Departamento departamento = new DepartamentoUtil().convertJsonToModel(departamentoJson);

        System.out.println(tamanho);
        if (id != null) {
            if (analista != null) {
                if (titulo != null && !titulo.isEmpty()) {
                    if (gerente != null) {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, gerente, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(id, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(id, analista, titulo, gerente, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(id, analista, titulo, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(id, analista, titulo, gerente, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(id, analista, titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndStatus(id, analista, titulo, gerente, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(id, analista, titulo, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, gerente, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(id, analista, titulo, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndStatus(id, analista, titulo, gerente, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(id, analista, titulo, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndStatus(id, analista, titulo, gerente, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(id, analista, titulo, gerente, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndStatus(id, analista, titulo, gerente, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(id, analista, titulo, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, gerente, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(id, analista, titulo, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndStatus(id, analista, titulo, gerente, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(id, analista, titulo, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndStatus(id, analista, titulo, gerente, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(id, analista, titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndStatus(id, analista, titulo, gerente, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(id, analista, titulo, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, gerente, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(id, analista, titulo, gerente, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndStatus(id, analista, titulo, gerente, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(id, analista, titulo, gerente, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndStatus(id, analista, titulo, gerente, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(id, analista, titulo, gerente, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerenteAndStatus(id, analista, titulo, gerente, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndGerente(id, analista, titulo, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(id, analista, titulo, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndStatus(id, analista, titulo, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(id, analista, titulo, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndStatus(id, analista, titulo, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(id, analista, titulo, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndStatus(id, analista, titulo, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(id, analista, titulo, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(id, analista, titulo, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndStatus(id, analista, titulo, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(id, analista, titulo, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndStatus(id, analista, titulo, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndSolicitante(id, analista, titulo, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForumAndStatus(id, analista, titulo, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndForum(id, analista, titulo, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(id, analista, titulo, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndStatus(id, analista, titulo, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(id, analista, titulo, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndStatus(id, analista, titulo, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(id, analista, titulo, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndStatus(id, analista, titulo, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndDepartamento(id, analista, titulo, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(id, analista, titulo, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndStatus(id, analista, titulo, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndTamanho(id, analista, titulo, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndStatus(id, analista, titulo, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndSolicitante(id, analista, titulo, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCaseAndStatus(id, analista, titulo, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingIgnoreCase(id, analista, titulo, pageable)
                                            );
                                        }
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
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, gerente, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(id, analista, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(id, analista, gerente, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(id, analista, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(id, analista, gerente, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(id, analista, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndDepartamentoAndStatus(id, analista, gerente, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndDepartamento(id, analista, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(id, analista, gerente, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndTamanhoAndSolicitante(id, analista, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndTamanhoAndStatus(id, analista, gerente, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndTamanho(id, analista, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndSolicitanteAndStatus(id, analista, gerente, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndSolicitante(id, analista, gerente, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForumAndStatus(id, analista, gerente, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndForum(id, analista, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, gerente, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndSolicitante(id, analista, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndDepartamentoAndTamanhoAndStatus(id, analista, gerente, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndDepartamentoAndTamanho(id, analista, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndDepartamentoAndSolicitanteAndStatus(id, analista, gerente, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndDepartamentoAndSolicitante(id, analista, gerente, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndDepartamentoAndStatus(id, analista, gerente, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndDepartamento(id, analista, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndTamanhoAndSolicitanteAndStatus(id, analista, gerente, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndTamanhoAndSolicitante(id, analista, gerente, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndTamanhoAndStatus(id, analista, gerente, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndTamanho(id, analista, gerente, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndSolicitanteAndStatus(id, analista, gerente, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndSolicitante(id, analista, gerente, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerenteAndStatus(id, analista, gerente, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndGerente(id, analista, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndDepartamentoAndTamanhoAndSolicitante(id, analista, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndDepartamentoAndTamanhoAndStatus(id, analista, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndDepartamentoAndTamanho(id, analista, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndDepartamentoAndSolicitanteAndStatus(id, analista, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndDepartamentoAndSolicitante(id, analista, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndDepartamentoAndStatus(id, analista, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndDepartamento(id, analista, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndTamanhoAndSolicitanteAndStatus(id, analista, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndTamanhoAndSolicitante(id, analista, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndTamanhoAndStatus(id, analista, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndTamanho(id, analista, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndSolicitanteAndStatus(id, analista, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndSolicitante(id, analista, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForumAndStatus(id, analista, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndForum(id, analista, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndDepartamentoAndTamanhoAndSolicitante(id, analista, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndDepartamentoAndTamanhoAndStatus(id, analista, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndDepartamentoAndTamanho(id, analista, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndDepartamentoAndSolicitanteAndStatus(id, analista, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndDepartamentoAndSolicitante(id, analista, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndDepartamentoAndStatus(id, analista, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndDepartamento(id, analista, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTamanhoAndSolicitanteAndStatus(id, analista, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTamanhoAndSolicitante(id, analista, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTamanhoAndStatus(id, analista, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTamanho(id, analista, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndSolicitanteAndStatus(id, analista, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndSolicitante(id, analista, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndStatus(id, analista, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalista(id, analista, pageable)
                                            );
                                        }
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
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, titulo, gerente, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(id, titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(id, titulo, gerente, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(id, titulo, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(id, titulo, gerente, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(id, titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndStatus(id, titulo, gerente, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(id, titulo, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(id, titulo, gerente, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(id, titulo, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndStatus(id, titulo, gerente, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(id, titulo, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitanteAndStatus(id, titulo, gerente, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(id, titulo, gerente, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForumAndStatus(id, titulo, gerente, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndForum(id, titulo, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, titulo, gerente, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(id, titulo, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndStatus(id, titulo, gerente, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(id, titulo, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitanteAndStatus(id, titulo, gerente, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(id, titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndStatus(id, titulo, gerente, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(id, titulo, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitanteAndStatus(id, titulo, gerente, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(id, titulo, gerente, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndStatus(id, titulo, gerente, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndTamanho(id, titulo, gerente, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndSolicitanteAndStatus(id, titulo, gerente, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(id, titulo, gerente, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerenteAndStatus(id, titulo, gerente, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndGerente(id, titulo, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, titulo, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(id, titulo, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndStatus(id, titulo, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(id, titulo, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitanteAndStatus(id, titulo, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(id, titulo, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndStatus(id, titulo, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndDepartamento(id, titulo, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitanteAndStatus(id, titulo, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(id, titulo, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndTamanhoAndStatus(id, titulo, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndTamanho(id, titulo, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndSolicitanteAndStatus(id, titulo, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndSolicitante(id, titulo, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForumAndStatus(id, titulo, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndForum(id, titulo, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, titulo, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(id, titulo, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndStatus(id, titulo, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(id, titulo, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitanteAndStatus(id, titulo, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(id, titulo, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndDepartamentoAndStatus(id, titulo, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndDepartamento(id, titulo, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndTamanhoAndSolicitanteAndStatus(id, titulo, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(id, titulo, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndTamanhoAndStatus(id, titulo, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndTamanho(id, titulo, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndSolicitanteAndStatus(id, titulo, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndSolicitante(id, titulo, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCaseAndStatus(id, titulo, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingIgnoreCase(id, titulo, pageable)
                                            );
                                        }
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
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, gerente, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(id, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(id, gerente, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndDepartamentoAndTamanho(id, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(id, gerente, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndDepartamentoAndSolicitante(id, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndDepartamentoAndStatus(id, gerente, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndDepartamento(id, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(id, gerente, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndTamanhoAndSolicitante(id, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndTamanhoAndStatus(id, gerente, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndTamanho(id, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndSolicitanteAndStatus(id, gerente, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndSolicitante(id, gerente, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForumAndStatus(id, gerente, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndForum(id, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, gerente, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndDepartamentoAndTamanhoAndSolicitante(id, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndDepartamentoAndTamanhoAndStatus(id, gerente, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndDepartamentoAndTamanho(id, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndDepartamentoAndSolicitanteAndStatus(id, gerente, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndDepartamentoAndSolicitante(id, gerente, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndDepartamentoAndStatus(id, gerente, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndDepartamento(id, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndTamanhoAndSolicitanteAndStatus(id, gerente, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndTamanhoAndSolicitante(id, gerente, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndTamanhoAndStatus(id, gerente, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndTamanho(id, gerente, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndSolicitanteAndStatus(id, gerente, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndSolicitante(id, gerente, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerenteAndStatus(id, gerente, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndGerente(id, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndDepartamentoAndTamanhoAndSolicitante(id, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndDepartamentoAndTamanhoAndStatus(id, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndDepartamentoAndTamanho(id, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndDepartamentoAndSolicitanteAndStatus(id, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndDepartamentoAndSolicitante(id, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndDepartamentoAndStatus(id, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndDepartamento(id, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndTamanhoAndSolicitanteAndStatus(id, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndTamanhoAndSolicitante(id, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndTamanhoAndStatus(id, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndTamanho(id, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndSolicitanteAndStatus(id, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndSolicitante(id, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForumAndStatus(id, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndForum(id, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndDepartamentoAndTamanhoAndSolicitante(id, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndDepartamentoAndTamanhoAndStatus(id, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndDepartamentoAndTamanho(id, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndDepartamentoAndSolicitanteAndStatus(id, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndDepartamentoAndSolicitante(id, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndDepartamentoAndStatus(id, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndDepartamento(id, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTamanhoAndSolicitanteAndStatus(id, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTamanhoAndSolicitante(id, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTamanhoAndStatus(id, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTamanho(id, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndSolicitanteAndStatus(id, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndSolicitante(id, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndStatus(id, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findById(id, pageable)
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
                if (titulo != null && !titulo.isEmpty()) {
                    if (gerente != null) {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(
                                                            analista, titulo, solicitante, gerente, forum, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndDepartamentoAndTamanho(
                                                            analista, titulo, solicitante, gerente, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(
                                                            analista, titulo, gerente, forum, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(
                                                            analista, titulo, gerente, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndDepartamentoAndStatus(
                                                            analista, titulo, solicitante, gerente, forum, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndDepartamento(
                                                            analista, titulo, solicitante, gerente, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndStatus(
                                                            analista, titulo, gerente, forum, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(
                                                            analista, titulo, gerente, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndTamanhoAndStatus(
                                                            analista, titulo, solicitante, gerente, forum, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndTamanho(
                                                            analista, titulo, solicitante, gerente, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndStatus(
                                                            analista, titulo, gerente, forum, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(
                                                            analista, titulo, gerente, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForumAndStatus(
                                                            analista, titulo, solicitante, gerente, forum, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndForum(
                                                            analista, titulo, solicitante, gerente, forum, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForumAndStatus(
                                                            analista, titulo, gerente, forum, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndForum(
                                                            analista, titulo, gerente, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndDepartamentoAndTamanhoAndStatus(
                                                            analista, titulo, solicitante, gerente, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndDepartamentoAndTamanho(
                                                            analista, titulo, solicitante, gerente, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndStatus(
                                                            analista, titulo, gerente, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(
                                                            analista, titulo, gerente, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndDepartamentoAndStatus(
                                                            analista, titulo, solicitante, gerente, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndDepartamento(
                                                            analista, titulo, solicitante, gerente, departamento, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndStatus(
                                                            analista, titulo, gerente, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(
                                                            analista, titulo, gerente, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndTamanhoAndStatus(
                                                            analista, titulo, solicitante, gerente, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndTamanho(
                                                            analista, titulo, solicitante, gerente, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndStatus(
                                                            analista, titulo, gerente, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndTamanho(
                                                            analista, titulo, gerente, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerenteAndStatus(
                                                            analista, titulo, solicitante, gerente, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndGerente(
                                                            analista, titulo, solicitante, gerente, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerenteAndStatus(
                                                            analista, titulo, gerente, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndGerente(
                                                            analista, titulo, gerente, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndDepartamentoAndTamanhoAndStatus(
                                                            analista, titulo, solicitante, forum, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndDepartamentoAndTamanho(
                                                            analista, titulo, solicitante, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndStatus(
                                                            analista, titulo, forum, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(
                                                            analista, titulo, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndDepartamentoAndStatus(
                                                            analista, titulo, solicitante, forum, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndDepartamento(
                                                            analista, titulo, solicitante, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndStatus(
                                                            analista, titulo, forum, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndDepartamento(
                                                            analista, titulo, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndTamanhoAndStatus(
                                                            analista, titulo, solicitante, forum, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndTamanho(
                                                            analista, titulo, solicitante, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanhoAndStatus(
                                                            analista, titulo, forum, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndTamanho(
                                                            analista, titulo, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForumAndStatus(
                                                            analista, titulo, solicitante, forum, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndForum(
                                                            analista, titulo, solicitante, forum, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndForumAndStatus(
                                                            analista, titulo, forum, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndForum(
                                                            analista, titulo, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndDepartamentoAndTamanhoAndStatus(
                                                            analista, titulo, solicitante, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndDepartamentoAndTamanho(
                                                            analista, titulo, solicitante, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndStatus(
                                                            analista, titulo, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(
                                                            analista, titulo, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndDepartamentoAndStatus(
                                                            analista, titulo, solicitante, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndDepartamento(
                                                            analista, titulo, solicitante, departamento, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndDepartamentoAndStatus(
                                                            analista, titulo, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndDepartamento(
                                                            analista, titulo, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndTamanhoAndStatus(
                                                            analista, titulo, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndTamanho(
                                                            analista, titulo, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndTamanhoAndStatus(
                                                            analista, titulo, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndTamanho(
                                                            analista, titulo, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitanteAndStatus(
                                                            analista, titulo, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndSolicitante(
                                                            analista, titulo, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCaseAndStatus(
                                                            analista, titulo, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTituloContainingIgnoreCase(
                                                            analista, titulo, pageable
                                                    )
                                            );
                                        }
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
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndTamanhoAndStatus(
                                                            analista, gerente, forum, departamento, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndTamanho(
                                                            analista, gerente, forum, departamento, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(
                                                            analista, gerente, forum, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndDepartamentoAndTamanho(
                                                            analista, gerente, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(
                                                            analista, gerente, forum, departamento, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                            analista, gerente, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndDepartamentoAndStatus(
                                                            analista, gerente, forum, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndDepartamento(
                                                            analista, gerente, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndSolicitanteAndTamanhoAndStatus(
                                                            analista, gerente, forum, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndSolicitanteAndTamanho(
                                                            analista, gerente, forum, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndTamanhoAndStatus(
                                                            analista, gerente, forum, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndTamanho(
                                                            analista, gerente, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndSolicitanteAndStatus(
                                                            analista, gerente, forum, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndSolicitante(
                                                            analista, gerente, forum, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForumAndStatus(
                                                            analista, gerente, forum, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndForum(
                                                            analista, gerente, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndDepartamentoAndSolicitanteAndTamanhoAndStatus(
                                                            analista, gerente, departamento, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndDepartamentoAndSolicitanteAndTamanho(
                                                            analista, gerente, departamento, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndDepartamentoAndTamanhoAndStatus(
                                                            analista, gerente, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndDepartamentoAndTamanho(
                                                            analista, gerente, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndDepartamentoAndSolicitanteAndStatus(
                                                            analista, gerente, departamento, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndDepartamentoAndSolicitante(
                                                            analista, gerente, departamento, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndDepartamentoAndStatus(
                                                            analista, gerente, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndDepartamento(
                                                            analista, gerente, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndSolicitanteAndTamanhoAndStatus(
                                                            analista, gerente, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndSolicitanteAndTamanho(
                                                            analista, gerente, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndTamanhoAndStatus(
                                                            analista, gerente, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndTamanho(
                                                            analista, gerente, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndSolicitanteAndStatus(
                                                            analista, gerente, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndSolicitante(
                                                            analista, gerente, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerenteAndStatus(
                                                            analista, gerente, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndGerente(
                                                            analista, gerente, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndDepartamentoAndSolicitanteAndTamanhoAndStatus(
                                                            analista, forum, departamento, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndDepartamentoAndSolicitanteAndTamanho(
                                                            analista, forum, departamento, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndDepartamentoAndTamanhoAndStatus(
                                                            analista, forum, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndDepartamentoAndTamanho(
                                                            analista, forum, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndDepartamentoAndSolicitanteAndStatus(
                                                            analista, forum, departamento, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndDepartamentoAndSolicitante(
                                                            analista, forum, departamento, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndDepartamentoAndStatus(
                                                            analista, forum, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndDepartamento(
                                                            analista, forum, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndSolicitanteAndTamanhoAndStatus(
                                                            analista, forum, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndSolicitanteAndTamanho(
                                                            analista, forum, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndTamanhoAndStatus(
                                                            analista, forum, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndTamanho(
                                                            analista, forum, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndSolicitanteAndStatus(
                                                            analista, forum, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndSolicitante(
                                                            analista, forum, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForumAndStatus(
                                                            analista, forum, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndForum(
                                                            analista, forum, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndDepartamentoAndSolicitanteAndTamanhoAndStatus(
                                                            analista, departamento, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndDepartamentoAndSolicitanteAndTamanho(
                                                            analista, departamento, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndDepartamentoAndTamanhoAndStatus(
                                                            analista, departamento, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndDepartamentoAndTamanho(
                                                            analista, departamento, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndDepartamentoAndSolicitanteAndStatus(
                                                            analista, departamento, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndDepartamentoAndSolicitante(
                                                            analista, departamento, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndDepartamentoAndStatus(
                                                            analista, departamento, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndDepartamento(
                                                            analista, departamento, pageable
                                                    )
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndSolicitanteAndTamanhoAndStatus(
                                                            analista, solicitante, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndSolicitanteAndTamanho(
                                                            analista, solicitante, tamanho, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTamanhoAndStatus(
                                                            analista, tamanho, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndTamanho(
                                                            analista, tamanho, pageable
                                                    )
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndSolicitanteAndStatus(
                                                            analista, solicitante, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndSolicitante(
                                                            analista, solicitante, pageable
                                                    )
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalistaAndStatus(
                                                            analista, status, pageable
                                                    )
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findPageByAnalista(
                                                            analista, pageable
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
                if (titulo != null && !titulo.isEmpty()) {
                    if (gerente != null) {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(status, titulo, gerente, forum, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndTamanho(titulo, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamentoAndSolicitante(titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndForumAndDepartamento(titulo, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndForumAndTamanhoAndSolicitante(titulo, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(status, titulo, gerente, forum, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndForumAndTamanho(titulo, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndForumAndSolicitante(titulo, gerente, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndForum(status, titulo, gerente, forum, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndForum(titulo, gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanhoAndSolicitante(titulo, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(status, titulo, gerente, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndTamanho(titulo, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndDepartamentoAndSolicitante(titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndDepartamento(titulo, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(status, titulo, gerente, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndTamanhoAndSolicitante(titulo, gerente, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndTamanho(status, titulo, gerente, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndTamanho(titulo, gerente, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerenteAndSolicitante(status, titulo, gerente, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerenteAndSolicitante(titulo, gerente, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndGerente(status, titulo, gerente, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndGerente(titulo, gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanhoAndSolicitante(titulo, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(status, titulo, forum, departamento, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndForumAndDepartamentoAndTamanho(titulo, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(status, titulo, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndForumAndDepartamentoAndSolicitante(titulo, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndForumAndDepartamento(status, titulo, forum, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndForumAndDepartamento(titulo, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(status, titulo, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndForumAndTamanhoAndSolicitante(titulo, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndForumAndTamanho(status, titulo, forum, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndForumAndTamanho(titulo, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndForumAndSolicitante(status, titulo, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndForumAndSolicitante(titulo, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndForum(status, titulo, forum, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndForum(titulo, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(status, titulo, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndDepartamentoAndTamanhoAndSolicitante(titulo, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndDepartamentoAndTamanho(status, titulo, departamento, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndDepartamentoAndTamanho(titulo, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(status, titulo, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndDepartamentoAndSolicitante(titulo, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndDepartamento(status, titulo, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndDepartamento(titulo, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndTamanhoAndSolicitante(status, titulo, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndTamanhoAndSolicitante(titulo, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndTamanho(status, titulo, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndTamanho(titulo, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCaseAndSolicitante(status, titulo, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCaseAndSolicitante(titulo, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTituloContainingIgnoreCase(status, titulo, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTituloContainingIgnoreCase(titulo, pageable)
                                            );
                                        }
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
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndForumAndDepartamentoAndTamanho(status, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndForumAndDepartamentoAndTamanho(gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndForumAndDepartamentoAndSolicitante(status, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndForumAndDepartamentoAndSolicitante(gerente, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndForumAndDepartamento(status, gerente, forum, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndForumAndDepartamento(gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndForumAndTamanhoAndSolicitante(status, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndForumAndTamanhoAndSolicitante(gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndForumAndTamanho(status, gerente, forum, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndForumAndTamanho(gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndForumAndSolicitante(status, gerente, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndForumAndSolicitante(gerente, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndForum(status, gerente, forum, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndForum(gerente, forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndDepartamentoAndTamanhoAndSolicitante(gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndDepartamentoAndTamanho(status, gerente, departamento, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndDepartamentoAndTamanho(gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndDepartamentoAndSolicitante(status, gerente, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndDepartamentoAndSolicitante(gerente, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndDepartamento(status, gerente, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndDepartamento(gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndTamanhoAndSolicitante(status, gerente, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndTamanhoAndSolicitante(gerente, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndTamanho(status, gerente, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndTamanho(gerente, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerenteAndSolicitante(status, gerente, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerenteAndSolicitante(gerente, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndGerente(status, gerente, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByGerente(gerente, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (forum != null) {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(status, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByForumAndDepartamentoAndTamanhoAndSolicitante(forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndForumAndDepartamentoAndTamanho(status, forum, departamento, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByForumAndDepartamentoAndTamanho(forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndForumAndDepartamentoAndSolicitante(status, forum, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByForumAndDepartamentoAndSolicitante(forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndForumAndDepartamento(status, forum, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByForumAndDepartamento(forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndForumAndTamanhoAndSolicitante(status, forum, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByForumAndTamanhoAndSolicitante(forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndForumAndTamanho(status, forum, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByForumAndTamanho(forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndForumAndSolicitante(status, forum, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByForumAndSolicitante(forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndForum(status, forum, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByForum(forum, pageable)
                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if (departamento != null) {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndDepartamentoAndTamanhoAndSolicitante(status, departamento, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByDepartamentoAndTamanhoAndSolicitante(departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndDepartamentoAndTamanho(status, departamento, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByDepartamentoAndTamanho(departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndDepartamentoAndSolicitante(status, departamento, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByDepartamentoAndSolicitante(departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndDepartamento(status, departamento, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByDepartamento(departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTamanhoAndSolicitante(status, tamanho, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTamanhoAndSolicitante(tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndTamanho(status, tamanho, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByTamanho(tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatusAndSolicitante(status, solicitante, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findBySolicitante(solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByStatus(status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findAll(pageable)
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
     * Método POST para criar uma demanda adicionado um ou vários anexos
     *
     * @param demandaJSON - Objeto da demanda a ser criada
     * @return - Retorno da demanda criada
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestParam("demanda") String demandaJSON) {
        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModel(demandaJSON);

        Usuario solicitante = usuarioService.findById(demanda.getSolicitante().getId()).get();
        Usuario gerente = usuarioService.findByDepartamentoAndTipoUsuario(solicitante.getDepartamento(), TipoUsuario.GERENTE);

        demanda.setData(new Date());
        demanda.setDepartamento(solicitante.getDepartamento());
        demanda.setGerente(gerente);
        demanda.setScore(calcularScore(demanda));

        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Função para calcular e retornar o score de uma demanda
     *
     * @param demanda - Demanda usada para o cálculo de seu Score
     * @return - Score calculado da demanda recebida
     */
    private Double calcularScore(Demanda demanda) {
        Double valorBeneficiosReais = 0.0;
        Double valorBeneficiosPotenciais = 0.0;

        if (demanda.getBeneficios().size() > 0) {
            for (Beneficio beneficio : demanda.getBeneficios()) {
                beneficio = beneficioService.findById(beneficio.getId()).get();

                if (beneficio.getTipoBeneficio() != null) {
                    if (beneficio.getTipoBeneficio().equals(TipoBeneficio.REAL)) {
                        valorBeneficiosReais += beneficio.getValor_mensal();
                    }

                    if (beneficio.getTipoBeneficio().equals(TipoBeneficio.POTENCIAL)) {
                        valorBeneficiosPotenciais += beneficio.getValor_mensal();
                    }
                }
            }
        }

        Integer valorTamanhoDemanda;
        if (demanda.getTamanho() == null) {
            valorTamanhoDemanda = 1000000000;
        } else if (demanda.getTamanho().equals("Muito Pequeno")) {
            valorTamanhoDemanda = 40;
        } else if (demanda.getTamanho().equals("Pequeno")) {
            valorTamanhoDemanda = 300;
        } else if (demanda.getTamanho().equals("Médio")) {
            valorTamanhoDemanda = 1000;
        } else if (demanda.getTamanho().equals("Grande")) {
            valorTamanhoDemanda = 3000;
        } else {
            valorTamanhoDemanda = 5000;
        }

        Long agingMilisegundos = Math.abs(new Date().getTime() - demanda.getData().getTime());
        Long agingFinal = TimeUnit.DAYS.convert(agingMilisegundos, TimeUnit.MILLISECONDS);

        Usuario solicitante = usuarioService.findById(demanda.getSolicitante().getId()).get();
        Integer valorPrioridade;
        if (solicitante.getTipoUsuario().equals(TipoUsuario.SOLICITANTE)) {
            valorPrioridade = 1;
        } else if (solicitante.getTipoUsuario().equals(TipoUsuario.ANALISTA)) {
            valorPrioridade = 2;
        } else if (solicitante.getTipoUsuario().equals(TipoUsuario.GERENTE)) {
            valorPrioridade = 4;
        } else {
            valorPrioridade = 16;
        }

        return (((2 * valorBeneficiosReais) + (1 * valorBeneficiosPotenciais) + agingFinal) / valorTamanhoDemanda) * valorPrioridade;
    }

    /**
     * Método PUT para editar uma demanda já existente
     *
     * @param demandaJSON - Objeto da demanda a ser editada
     * @return - Retorno da demanda editada
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestParam("demanda") String demandaJSON) {
        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);
        demanda.setScore(calcularScore(demanda));

        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Função utilizada para somente atualizar o status de uma demanda, recebendo como parâmetros o id e o novo status da demanda.
     * Usada na aceitação/recusa do gerente e na criação/edição de propostas para acompanhamento do status da demanda
     *
     * @param id     - ID da demanda
     * @param status - Novo status da demanda
     * @return - Retorno da demanda editada
     */
    @PutMapping("/status/{id}/{status}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable(value = "id") Long id,
                                                  @PathVariable(value = "status") Status status) {
        Demanda demanda = demandaService.findById(id).get();
        demanda.setStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Função para adicionar um novo objeto Historico em uma demanda, salvando sua edição
     *
     * @param idDemanda     - ID da demanda a receber o histórico
     * @param historicoJson - Objeto com os dados principais do histórico em formato String
     * @param documento     - Arquivo para ser transferido em DocumentoHistorico e salvo no histórico
     * @return - ResponseEntity com a demanda atualizada
     */
    @PutMapping("/add-historico/{idDemanda}")
    public ResponseEntity<Object> addHistorico(@PathVariable(value = "idDemanda") Long idDemanda,
                                               @RequestParam(value = "historico") String historicoJson,
                                               @RequestParam(value = "documento") MultipartFile documento) {
        Optional<Demanda> demandaOptional = demandaService.findById(idDemanda);
        if (demandaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demanda não encontrada!");
        }
        Demanda demanda = demandaOptional.get();

        // Conversão do históricoJson
        HistoricoUtil util = new HistoricoUtil();
        Historico historico = util.convertJsonToModel(historicoJson);

        // Conversão e atribuição do DocumentoHistorico
        historico.setDocumentoMultipart(documento);

        // Salvamento do nome do DocumentoHistorico, baseando seu número de versão no número de históricos total da demanda
        List<Historico> listaHistorico = demanda.getHistoricoDemanda();
        DocumentoHistorico documentoHistorico = historico.getDocumentoHistorico();
        documentoHistorico.setNome(demanda.getTitulo() + " - Versão " + (listaHistorico.size() + 1));

        // Adição e atribuição do novo histórico na lista de históricos da demanda
        listaHistorico.add(historicoService.save(historico));
        demanda.setHistoricoDemanda(listaHistorico);

        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Função para excluir uma demanda a partir do seu ID, sendo passado como variável
     *
     * @param id - ID da demanda a ser excluída
     * @return - ResponseEntity com uma String indicando o resultado da exclusão
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long id) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        demandaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Demanda deletada com sucesso.");
    }

}
