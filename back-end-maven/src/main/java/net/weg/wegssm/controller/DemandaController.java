package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.BeneficioService;
import net.weg.wegssm.model.service.DemandaService;
import net.weg.wegssm.model.service.DepartamentoService;
import net.weg.wegssm.model.service.UsuarioService;
import net.weg.wegssm.util.DemandaUtil;
import net.weg.wegssm.util.DepartamentoUtil;
import net.weg.wegssm.util.ForumUtil;
import net.weg.wegssm.util.UsuarioUtil;
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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/demanda")
public class DemandaController {

    private DemandaService demandaService;
    private UsuarioService usuarioService;
    private BeneficioService beneficioService;

    /**
     * Método GET para buscar todas as demandas
     */
    @GetMapping
    public ResponseEntity<List<Demanda>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.findAll());
    }

    /**
     * Função principal para buscar demandas, retornando uma página com as demandas seguindo o filtro utilizado
     *
     * @param pageable         - Objeto que contém as informações da páginação
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
    public ResponseEntity<Page<Demanda>> findPage(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
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

        if (analista != null) {
            if (titulo != null && !titulo.isEmpty()) {
                if (gerente != null) {
                    if (forum != null) {
                        if (departamento != null) {
                            if (tamanho != null && !tamanho.isEmpty()) {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(
                                                        analista, titulo, solicitante, gerente, forum, departamento, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndDepartamentoAndTamanho(
                                                        analista, titulo, solicitante, gerente, forum, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(
                                                        analista, titulo, gerente, forum, departamento, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(
                                                        analista, titulo, gerente, forum, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndDepartamentoAndStatus(
                                                        analista, titulo, solicitante, gerente, forum, departamento, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndDepartamento(
                                                        analista, titulo, solicitante, gerente, forum, departamento, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndStatus(
                                                        analista, titulo, gerente, forum, departamento, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(
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
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndTamanhoAndStatus(
                                                        analista, titulo, solicitante, gerente, forum, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndTamanho(
                                                        analista, titulo, solicitante, gerente, forum, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndStatus(
                                                        analista, titulo, gerente, forum, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(
                                                        analista, titulo, gerente, forum, tamanho, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForumAndStatus(
                                                        analista, titulo, solicitante, gerente, forum, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndForum(
                                                        analista, titulo, solicitante, gerente, forum, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndForumAndStatus(
                                                        analista, titulo, gerente, forum, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndForum(
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
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndDepartamentoAndTamanhoAndStatus(
                                                        analista, titulo, solicitante, gerente, departamento, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndDepartamentoAndTamanho(
                                                        analista, titulo, solicitante, gerente, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndStatus(
                                                        analista, titulo, gerente, departamento, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(
                                                        analista, titulo, gerente, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndDepartamentoAndStatus(
                                                        analista, titulo, solicitante, gerente, departamento, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndDepartamento(
                                                        analista, titulo, solicitante, gerente, departamento, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndDepartamentoAndStatus(
                                                        analista, titulo, gerente, departamento, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndDepartamento(
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
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndTamanhoAndStatus(
                                                        analista, titulo, solicitante, gerente, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndTamanho(
                                                        analista, titulo, solicitante, gerente, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndTamanhoAndStatus(
                                                        analista, titulo, gerente, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndTamanho(
                                                        analista, titulo, gerente, tamanho, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerenteAndStatus(
                                                        analista, titulo, solicitante, gerente, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndGerente(
                                                        analista, titulo, solicitante, gerente, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerenteAndStatus(
                                                        analista, titulo, gerente, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndGerente(
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
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndDepartamentoAndTamanhoAndStatus(
                                                        analista, titulo, solicitante, forum, departamento, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndDepartamentoAndTamanho(
                                                        analista, titulo, solicitante, forum, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndStatus(
                                                        analista, titulo, forum, departamento, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(
                                                        analista, titulo, forum, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndDepartamentoAndStatus(
                                                        analista, titulo, solicitante, forum, departamento, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndDepartamento(
                                                        analista, titulo, solicitante, forum, departamento, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndForumAndDepartamentoAndStatus(
                                                        analista, titulo, forum, departamento, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndForumAndDepartamento(
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
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndTamanhoAndStatus(
                                                        analista, titulo, solicitante, forum, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndTamanho(
                                                        analista, titulo, solicitante, forum, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndForumAndTamanhoAndStatus(
                                                        analista, titulo, forum, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndForumAndTamanho(
                                                        analista, titulo, forum, tamanho, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndForumAndStatus(
                                                        analista, titulo, solicitante, forum, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndForum(
                                                        analista, titulo, solicitante, forum, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndForumAndStatus(
                                                        analista, titulo, forum, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndForum(
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
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndDepartamentoAndTamanhoAndStatus(
                                                        analista, titulo, solicitante, departamento, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndDepartamentoAndTamanho(
                                                        analista, titulo, solicitante, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndStatus(
                                                        analista, titulo, departamento, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndDepartamentoAndTamanho(
                                                        analista, titulo, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndDepartamentoAndStatus(
                                                        analista, titulo, solicitante, departamento, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndDepartamento(
                                                        analista, titulo, solicitante, departamento, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndDepartamentoAndStatus(
                                                        analista, titulo, departamento, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndDepartamento(
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
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndTamanhoAndStatus(
                                                        analista, titulo, solicitante, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndTamanho(
                                                        analista, titulo, solicitante, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndTamanhoAndStatus(
                                                        analista, titulo, tamanho, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndTamanho(
                                                        analista, titulo, tamanho, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitanteAndStatus(
                                                        analista, titulo, solicitante, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndSolicitante(
                                                        analista, titulo, solicitante, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContainingAndStatus(
                                                        analista, titulo, status, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findPageByAnalistaAndTituloContaining(
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
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(status, titulo, gerente, forum, departamento, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(titulo, gerente, forum, departamento, tamanho, pageable)
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(titulo, gerente, forum, departamento, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndForumAndDepartamento(titulo, gerente, forum, departamento, pageable)
                                        );
                                    }
                                }
                            }
                        } else {
                            if (tamanho != null && !tamanho.isEmpty()) {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(titulo, gerente, forum, tamanho, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndTamanho(status, titulo, gerente, forum, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndForumAndTamanho(titulo, gerente, forum, tamanho, pageable)
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndForumAndSolicitante(titulo, gerente, forum, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndForum(status, titulo, gerente, forum, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndForum(titulo, gerente, forum, pageable)
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
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(titulo, gerente, departamento, tamanho, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(status, titulo, gerente, departamento, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndDepartamentoAndTamanho(titulo, gerente, departamento, tamanho, pageable)
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndDepartamentoAndSolicitante(titulo, gerente, departamento, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndDepartamento(titulo, gerente, departamento, pageable)
                                        );
                                    }
                                }
                            }
                        } else {
                            if (tamanho != null && !tamanho.isEmpty()) {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(status, titulo, gerente, tamanho, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndTamanhoAndSolicitante(titulo, gerente, tamanho, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndTamanho(status, titulo, gerente, tamanho, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndTamanho(titulo, gerente, tamanho, pageable)
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerenteAndSolicitante(status, titulo, gerente, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerenteAndSolicitante(titulo, gerente, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndGerente(status, titulo, gerente, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndGerente(titulo, gerente, pageable)
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
                                                demandaService.findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, forum, departamento, tamanho, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(titulo, forum, departamento, tamanho, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(status, titulo, forum, departamento, tamanho, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndForumAndDepartamentoAndTamanho(titulo, forum, departamento, tamanho, pageable)
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(status, titulo, forum, departamento, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndForumAndDepartamentoAndSolicitante(titulo, forum, departamento, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndForumAndDepartamento(status, titulo, forum, departamento, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndForumAndDepartamento(titulo, forum, departamento, pageable)
                                        );
                                    }
                                }
                            }
                        } else {
                            if (tamanho != null && !tamanho.isEmpty()) {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(status, titulo, forum, tamanho, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndForumAndTamanhoAndSolicitante(titulo, forum, tamanho, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndForumAndTamanho(status, titulo, forum, tamanho, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndForumAndTamanho(titulo, forum, tamanho, pageable)
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndForumAndSolicitante(status, titulo, forum, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndForumAndSolicitante(titulo, forum, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndForum(status, titulo, forum, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndForum(titulo, forum, pageable)
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
                                                demandaService.findByStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(status, titulo, departamento, tamanho, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndDepartamentoAndTamanhoAndSolicitante(titulo, departamento, tamanho, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndDepartamentoAndTamanho(status, titulo, departamento, tamanho, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndDepartamentoAndTamanho(titulo, departamento, tamanho, pageable)
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndDepartamentoAndSolicitante(status, titulo, departamento, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndDepartamentoAndSolicitante(titulo, departamento, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndDepartamento(status, titulo, departamento, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndDepartamento(titulo, departamento, pageable)
                                        );
                                    }
                                }
                            }
                        } else {
                            if (tamanho != null && !tamanho.isEmpty()) {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndTamanhoAndSolicitante(status, titulo, tamanho, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndTamanhoAndSolicitante(titulo, tamanho, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndTamanho(status, titulo, tamanho, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndTamanho(titulo, tamanho, pageable)
                                        );
                                    }
                                }
                            } else {
                                if (solicitante != null) {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContainingAndSolicitante(status, titulo, solicitante, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContainingAndSolicitante(titulo, solicitante, pageable)
                                        );
                                    }
                                } else {
                                    if (status != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByStatusAndTituloContaining(status, titulo, pageable)
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                demandaService.findByTituloContaining(titulo, pageable)
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

    /**
     * Método GET para buscar uma demanda específica através do id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findById(id).get());
    }

    /**
     * Método GET para buscar as demandas com um determinado status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Demanda>> findByStatus(@PathVariable(value = "status") Status status) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByStatus(status));
    }

    /**
     * Método GET para buscar as demandas de um determinado forum
     */
    @GetMapping("/forum/{forum}")
    public ResponseEntity<List<Demanda>> findByForum(@PathVariable(value = "forum") Forum forum) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByForum(forum));
    }

    /**
     * Método GET para buscar todas as demandas de um determinado departamento
     */
    @GetMapping("departamento/{departamento}")
    public ResponseEntity<List<Demanda>> findByDepartamento(@PathVariable(value = "departamento") Departamento departamento) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByDepartamento(departamento));
    }

    /**
     * Método POST para criar uma demanda adicionado um ou vários anexos
     *
     * @param files
     * @param demandaJSON
     * @return
     */
    @PostMapping("/{usuarioId}")
    public ResponseEntity<Object> save(@RequestParam("anexos") List<MultipartFile> files, @RequestParam("demanda") String demandaJSON, @PathVariable(value = "usuarioId") Long usuarioId) {

        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModel(demandaJSON);
        Usuario usuario = usuarioService.findById(usuarioId).get();

        ArrayList<Beneficio> listaBeneficios = new ArrayList<>();
        for (Beneficio beneficio : demanda.getBeneficios()) {
            listaBeneficios.add(beneficioService.save(beneficio));
        }

        demanda.setData(new Date());
        demanda.setDepartamento(usuario.getDepartamento());
        demanda.setBeneficios(listaBeneficios);
        demanda.setSolicitante(usuario);
        demanda.setAnexos(files);

        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    @PostMapping("/sem-arquivos/{usuarioId}")
    public ResponseEntity<Object> saveSemArquivos(@RequestParam("demanda") String demandaJSON, @PathVariable(value = "usuarioId") Long usuarioId) {

        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModel(demandaJSON);
        Usuario usuario = usuarioService.findById(usuarioId).get();
        Departamento departamento = usuario.getDepartamento();

        ArrayList<Beneficio> listaBeneficios = new ArrayList<>();
        for (Beneficio beneficio : demanda.getBeneficios()) {
            listaBeneficios.add(beneficioService.save(beneficio));
        }

        demanda.setData(new Date());
        demanda.setDepartamento(departamento);
        demanda.setBeneficios(listaBeneficios);
        demanda.setSolicitante(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Método PUT para editar uma demanda já existente
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestParam("anexos") List<MultipartFile> files, @RequestParam("demanda") String demandaJSON) {
        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);
        demanda.setAnexos(files);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    @PutMapping("/sem-arquivos")
    public ResponseEntity<Object> updateSemArquivos(@RequestParam("demanda") String demandaJSON) {
        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);
        demanda.setAnexo(null);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    @PutMapping("/manter-arquivos-velhos")
    public ResponseEntity<Object> updateManterArquivos(@RequestParam("demanda") String demandaJSON) {
        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);
        Optional<Demanda> demandaVelha = demandaService.findById(demanda.getId());
        demanda.setAnexosWithoutMultiparFile(demandaVelha.get().getAnexo());
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    @PutMapping("/status/{id}/{status}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable(value = "id") Long id,
                                                  @PathVariable(value = "status") Status status) {
        Demanda demanda = demandaService.findById(id).get();
        demanda.setStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Método DELETE para editar uma demanda, editando sua visibilidade para false
     */
    @Transactional
    @DeleteMapping("/visibilidade/{id}")
    public ResponseEntity<Object> deleteByIdVisibilidade(@PathVariable(value = "id") Long id) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        Demanda demanda = demandaService.findById(id).get();
        demandaService.save(demanda);
        return ResponseEntity.status(HttpStatus.OK).body(demanda);
    }

    /**
     * Método DELETE para deletar uma demanda
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
