package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.PropostaService;
import net.weg.wegssm.util.PropostaUtil;
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
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/weg_ssm/proposta")
public class PropostaController {

    private PropostaService propostaService;

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
     * @param codigoPPM    - Código PPM da proposta
     * @param pageable     - Objeto que contém as informações da páginação
     * @param titulo       - Título da proposta (usado na barra de pesquisa)
     * @param solicitante  - Solicitante da proposta (usado no modal de filtro)
     * @param gerente      - Gerente da proposta (usado no modal de filtro)
     * @param forum        - Fórum da proposta (usado no modal de filtro)
     * @param departamento - Departamento da proposta (usado no modal de filtro)
     * @param tamanho      - Tamanho da proposta (usado no modal de filtro)
     * @param status       - Status da proposta (usado sempre quando é feita a busca)
     * @return - Retorna uma página com as propostas encontradas
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Proposta>> findPage(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) Long codigoPPM,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Usuario solicitante,
            @RequestParam(required = false) Usuario gerente,
            @RequestParam(required = false) Forum forum,
            @RequestParam(required = false) Departamento departamento,
            @RequestParam(required = false) String tamanho,
            @RequestParam(required = false) Usuario analista,
            @RequestParam(required = false) Long id,
            @RequestParam Status status
    ) {
        if (analista != null) {
            if(codigoPPM != null) {
                if(status != null) {
                    if(titulo != null && !titulo.isEmpty()) {
                        if(gerente != null) {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                        if(gerente != null) {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                    if(titulo != null) {
                        if(gerente != null) {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                        if(gerente != null) {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                                    if(tamanho != null) {
                                        if(solicitante != null) {
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
                                        if(solicitante != null) {
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
                if(status != null) {
                    if(titulo != null && !titulo.isEmpty()) {
                        if(gerente != null) {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            } else {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            } else {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if(gerente != null) {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            } else {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            } else {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if(titulo != null && !titulo.isEmpty()) {
                        if(gerente != null) {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            } else {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            } else {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if(gerente != null) {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            } else {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            }
                        } else {
                            if(forum != null) {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                }
                            } else {
                                if(departamento != null) {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    }
                                } else {
                                    if(tamanho != null) {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        }
                                    } else {
                                        if(solicitante != null) {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
                                        } else {
//                                            return ResponseEntity.status(HttpStatus.OK).body(
//                                                    propostaService.findByVisibilidadeAndStatusAndAnalistaAndCodigoPPMAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
//                                                            true, status, analista, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
//                                                    )
//                                            );
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
    public ResponseEntity<Object> save(@RequestParam("anexos") List<MultipartFile> files, @RequestParam("proposta") String propostaJSON) {
        PropostaUtil propostaUtil = new PropostaUtil();
        Proposta proposta = propostaUtil.convertJsonToModel(propostaJSON);

        proposta.setAnexos(files);
        proposta.setVisibilidade(true);

        return ResponseEntity.status(HttpStatus.CREATED).body(propostaService.save(proposta));
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
