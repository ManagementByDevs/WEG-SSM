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
     * Função principal para buscar demandas, retornando uma página com as demandas seguindo o filtro utilizado
     *
     * @param pageable     - Objeto que contém as informações da páginação
     * @param titulo       - Título da demanda (usado na barra de pesquisa)
     * @param solicitante  - Solicitante da demanda (usado no modal de filtro)
     * @param gerente      - Gerente da demanda (usado no modal de filtro)
     * @param forum        - Fórum da demanda (usado no modal de filtro)
     * @param departamento - Departamento da demanda (usado no modal de filtro)
     * @param tamanho      - Tamanho da demanda (usado no modal de filtro)
     * @param status       - Status da demanda (usado sempre quando é feita a busca)
     * @return - Retorna uma página com as demandas encontradas
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
            @RequestParam Status status
    ) {
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
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                        true, codigoPPM, titulo, gerente, forum, departamento, tamanho, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(
                                                        true, codigoPPM, titulo, gerente, forum, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(
                                                        true, codigoPPM, titulo, gerente, forum, departamento, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndDepartamento(
                                                        true, codigoPPM, titulo, gerente, forum, departamento, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(
                                                        true, codigoPPM, titulo, gerente, forum, tamanho, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndTamanho(
                                                        true, codigoPPM, titulo, gerente, forum, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForumAndSolicitante(
                                                        true, codigoPPM, titulo, gerente, forum, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndForum(
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
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(
                                                        true, codigoPPM, titulo, gerente, departamento, tamanho, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndTamanho(
                                                        true, codigoPPM, titulo, gerente, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamentoAndSolicitante(
                                                        true, codigoPPM, titulo, gerente, departamento, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndDepartamento(
                                                        true, codigoPPM, titulo, gerente, departamento, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanhoAndSolicitante(
                                                        true, codigoPPM, titulo, gerente, tamanho, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndTamanho(
                                                        true, codigoPPM, titulo, gerente, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerenteAndSolicitante(
                                                        true, codigoPPM, titulo, gerente, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndGerente(
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
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(
                                                        true, codigoPPM, titulo, forum, departamento, tamanho, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndTamanho(
                                                        true, codigoPPM, titulo, forum, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamentoAndSolicitante(
                                                        true, codigoPPM, titulo, forum, departamento, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndDepartamento(
                                                        true, codigoPPM, titulo, forum, departamento, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanhoAndSolicitante(
                                                        true, codigoPPM, titulo, forum, tamanho, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndTamanho(
                                                        true, codigoPPM, titulo, forum, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndForumAndSolicitante(
                                                        true, codigoPPM, titulo, forum, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndForum(
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
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanhoAndSolicitante(
                                                        true, codigoPPM, titulo, departamento, tamanho, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndTamanho(
                                                        true, codigoPPM, titulo, departamento, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamentoAndSolicitante(
                                                        true, codigoPPM, titulo, departamento, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndDepartamento(
                                                        true, codigoPPM, titulo, departamento, pageable
                                                )
                                        );
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndTamanhoAndSolicitante(
                                                        true, codigoPPM, titulo, tamanho, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndTamanho(
                                                        true, codigoPPM, titulo, tamanho, pageable
                                                )
                                        );
                                    }
                                } else {
                                    if (solicitante != null) {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTituloAndSolicitante(
                                                        true, codigoPPM, titulo, solicitante, pageable
                                                )
                                        );
                                    } else {
                                        return ResponseEntity.status(HttpStatus.OK).body(
                                                propostaService.findByVisibilidadeAndCodigoPPMAndTitulo(
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
                return null;
            }
        }
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
