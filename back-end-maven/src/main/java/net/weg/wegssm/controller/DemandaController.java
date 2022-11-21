package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.DemandaService;
import net.weg.wegssm.model.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/weg_ssm/demanda")
public class DemandaController {

    private DemandaService demandaService;
    private UsuarioService usuarioService;

    /**
     * Método GET para buscar todas as demandas
     */
    @GetMapping
    public ResponseEntity<List<Demanda>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Demanda>> findPage(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Usuario solicitante,
            @RequestParam(required = false) Usuario gerente,
            @RequestParam(required = false) Forum forum,
            @RequestParam(required = false) Departamento departamento,
            @RequestParam(required = false) String tamanho,
            @RequestParam Status status
    ) {
        if (titulo != null && !titulo.isEmpty()) {
            if (gerente != null) {
                if (forum != null) {
                    if (departamento != null) {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(status, titulo, gerente, forum, departamento, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndForumAndTamanho(status, titulo, gerente, forum, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndForum(status, titulo, gerente, forum, pageable)
                                );
                            }
                        }
                    }
                } else {
                    if (departamento != null) {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanho(status, titulo, gerente, departamento, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndTamanhoAndSolicitante(status, titulo, gerente, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndTamanho(status, titulo, gerente, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerenteAndSolicitante(status, titulo, gerente, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndGerente(status, titulo, gerente, pageable)
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
                                        demandaService.findByStatusAndTituloAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, forum, departamento, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndForumAndDepartamentoAndTamanho(status, titulo, forum, departamento, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndForumAndDepartamentoAndSolicitante(status, titulo, forum, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndForumAndDepartamento(status, titulo, forum, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndForumAndTamanhoAndSolicitante(status, titulo, forum, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndForumAndTamanho(status, titulo, forum, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndForumAndSolicitante(status, titulo, forum, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndForum(status, titulo, forum, pageable)
                                );
                            }
                        }
                    }
                } else {
                    if (departamento != null) {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndDepartamentoAndTamanhoAndSolicitante(status, titulo, departamento, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndDepartamentoAndTamanho(status, titulo, departamento, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndDepartamentoAndSolicitante(status, titulo, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndDepartamento(status, titulo, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndTamanhoAndSolicitante(status, titulo, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndTamanho(status, titulo, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloAndSolicitante(status, titulo, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTitulo(status, titulo, pageable)
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
                                        demandaService.findByStatusAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, gerente, forum, departamento, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndForumAndDepartamentoAndTamanho(status, gerente, forum, departamento, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndForumAndDepartamentoAndSolicitante(status, gerente, forum, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndForumAndDepartamento(status, gerente, forum, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndForumAndTamanhoAndSolicitante(status, gerente, forum, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndForumAndTamanho(status, gerente, forum, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndForumAndSolicitante(status, gerente, forum, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndForum(status, gerente, forum, pageable)
                                );
                            }
                        }
                    }
                } else {
                    if (departamento != null) {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, gerente, departamento, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndDepartamentoAndTamanho(status, gerente, departamento, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndDepartamentoAndSolicitante(status, gerente, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndDepartamento(status, gerente, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndTamanhoAndSolicitante(status, gerente, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndTamanho(status, gerente, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerenteAndSolicitante(status, gerente, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndGerente(status, gerente, pageable)
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
                                        demandaService.findByStatusAndForumAndDepartamentoAndTamanhoAndSolicitante(status, forum, departamento, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndForumAndDepartamentoAndTamanho(status, forum, departamento, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndForumAndDepartamentoAndSolicitante(status, forum, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndForumAndDepartamento(status, forum, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndForumAndTamanhoAndSolicitante(status, forum, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndForumAndTamanho(status, forum, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndForumAndSolicitante(status, forum, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndForum(status, forum, pageable)
                                );
                            }
                        }
                    }
                } else {
                    if (departamento != null) {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndDepartamentoAndTamanhoAndSolicitante(status, departamento, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndDepartamentoAndTamanho(status, departamento, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndDepartamentoAndSolicitante(status, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndDepartamento(status, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTamanhoAndSolicitante(status, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTamanho(status, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndSolicitante(status, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatus(status, pageable)
                                );
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
     * Método GET para ordenar as demandas de Z-A (Decrescente)
     */
    @GetMapping("/ordenarTituloZA")
    public ResponseEntity<Page<Demanda>> findAllTituloDecrescente(@PageableDefault(
            page = 0, size = 20, sort = "titulo", direction = Sort.Direction.DESC
    ) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as demandas de A-Z (Crescente)
     */
    @GetMapping("/ordenarTituloAZ")
    public ResponseEntity<Page<Demanda>> findAllTituloCrescente(@PageableDefault(
            page = 0, size = 20, sort = "titulo", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findAll(pageable));
    }

    /**
     * Método POST para criar uma demanda
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DemandaDTO demandaDto) {
        Demanda demanda = new Demanda();
        demanda.setVisibilidade(true);
        BeanUtils.copyProperties(demandaDto, demanda);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Método PUT para editar uma demanda já existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid DemandaDTO demandaDto) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        Demanda demanda = new Demanda();
        demanda.setVisibilidade(true);
        BeanUtils.copyProperties(demandaDto, demanda);
        demanda.setId(id);
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
        demanda.setVisibilidade(false);
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
