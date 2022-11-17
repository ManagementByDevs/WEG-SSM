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
import java.util.ArrayList;
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

//    @GetMapping("/page")
//    public ResponseEntity<List<Demanda>> findPage(
//            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
//            @RequestParam(required = false) String titulo,
//            @RequestParam(required = false) String nomeSolicitante,
//            @RequestParam(required = false) String nomeGerente,
//            @RequestParam(required = false) Forum forum,
//            @RequestParam(required = false) Departamento departamento,
//            @RequestParam(required = false) String tamanho,
//            @RequestParam(required = false) Long numero,
//            @RequestParam Status status
//    ) {
//        if (titulo != null && !titulo.isEmpty()) {
//            if (nomeGerente != null && !nomeGerente.isEmpty()) {
//                Usuario gerente = usuarioService.findByNomeStartsWith(nomeGerente).get(0);
//                if (forum != null) {
//                    if (departamento != null) {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    Integer contador = 0, pagina = 0;
//                                    for (Usuario solicitante : solicitantes) {
//                                        List<Demanda> listaSolicitante = demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndNumeroAndSolicitante(status, titulo, gerente, forum, departamento, numero, solicitante, pageable);
//                                        for (Demanda demanda : listaSolicitante) {
//                                            if (pagina == pageable.getPageNumber()) {
//                                                listaDemandas.add(demanda);
//                                            }
//                                            listaDemandas.add(demanda);
//                                            contador++;
//                                            if (contador % pageable.getPageSize() == 0) {
//                                                pagina++;
//                                            }
//                                            if (pagina > pageable.getPageNumber()) {
//                                                break;
//                                            }
//                                        }
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndNumero(status, titulo, gerente, forum, departamento, numero, pageable)
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndTamanho(status, titulo, gerente, forum, departamento, pageable)
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndNumeroAndSolicitante(status, titulo, gerente, forum, departamento, numero, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndNumero(status, titulo, gerente, forum, departamento, numero, pageable)
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable)
//                                    );
//                                }
//                            }
//                        }
//                    } else {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndForumAndTamanhoAndNumeroAndSolicitante(status, titulo, gerente, forum, numero, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndForumAndTamanhoAndNumero(status, titulo, gerente, forum, numero, pageable)
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndForumAndTamanhoAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndForumAndTamanho(status, titulo, gerente, forum, pageable)
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndForumAndNumeroAndSolicitante(status, titulo, gerente, forum, numero, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndForumAndNumero(status, titulo, gerente, forum, numero, pageable)
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndForum(status, titulo, gerente, forum, pageable)
//                                    );
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (departamento != null) {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndNumeroAndSolicitante(status, titulo, gerente, departamento, numero, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndNumero(status, titulo, gerente, departamento, numero, pageable)
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(listaDemandas);
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//                                            demandaService.findByStatusAndTituloAndGerenteAndDepartamentoAndTamanho(status, titulo, gerente, departamento, pageable)
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    List<Demanda> listaDemandas = new ArrayList<>();
//                                    for (Usuario solicitante : solicitantes) {
//                                        listaDemandas.add(
//                                                demandaService.findByStatusAndTituloAndGerenteAndDepartamentoAndNumeroAndSolicitante(status, titulo, gerente, departamento, numero, solicitante, pageable)
//                                        );
//                                    }
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    } else {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    }
//                }
//            } else {
//                if (forum != null) {
//                    if (departamento != null) {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    } else {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (departamento != null) {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    } else {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } else {
//            if (nomeGerente != null && !nomeGerente.isEmpty()) {
//                Usuario gerente = usuarioService.findByNomeStartsWith(nomeGerente).get(0);
//                if (forum != null) {
//                    if (departamento != null) {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    } else {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (departamento != null) {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    } else {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    }
//                }
//            } else {
//                if (forum != null) {
//                    if (departamento != null) {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    } else {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (departamento != null) {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    } else {
//                        if (tamanho != null && !tamanho.isEmpty()) {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        } else {
//                            if (numero != null) {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            } else {
//                                if (nomeSolicitante != null && !nomeSolicitante.isEmpty()) {
//                                    List<Usuario> solicitantes = usuarioService.findByNomeStartsWith(nomeSolicitante);
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                } else {
//                                    return ResponseEntity.status(HttpStatus.OK).body(
//
//                                    );
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    /**
     * Méotod GET para buscar uma demanda específica através do id
     *
     * @param id
     * @return
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
     *
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Demanda>> findByStatus(@PathVariable(value = "status") Status status) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByStatus(status));
    }

    /**
     * Método GET para buscar as demandas de um determinado forum
     *
     * @param forum
     * @return
     */
    @GetMapping("/forum/{forum}")
    public ResponseEntity<List<Demanda>> findByForum(@PathVariable(value = "forum") Forum forum) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByForum(forum));
    }

    /**
     * Método GET para buscar as demandas de um determinado usuário
     *
     * @param usuario
     * @return
     */
//    @GetMapping("usuario/{usuario}")
//    public ResponseEntity<List<Demanda>> findByUsuario(@PathVariable(value = "usuario") Usuario usuario) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByUsuario(usuario));
//    }

    /**
     * Método GET para buscar todas as demandas de um determinado departamento
     *
     * @param departamento
     * @return
     */
    @GetMapping("departamento/{departamento}")
    public ResponseEntity<List<Demanda>> findByDepartamento(@PathVariable(value = "departamento") Departamento departamento) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findByDepartamento(departamento));
    }

    /**
     * Método GET para ordenar as demandas de Z-A (Decrescente)
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarTituloZA")
    public ResponseEntity<Page<Demanda>> findAllTituloDecrescente(@PageableDefault(
            page = 0, size = 20, sort = "titulo", direction = Sort.Direction.DESC
    ) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findAll(pageable));
    }

    /**
     * Método GET para ordenar as demandas de A-Z (Crescente)
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ordenarTituloAZ")
    public ResponseEntity<Page<Demanda>> findAllTituloCrescente(@PageableDefault(
            page = 0, size = 20, sort = "titulo", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(demandaService.findAll(pageable));
    }

    /**
     * Método POST para criar uma demanda
     *
     * @param demandaDto
     * @return
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
     *
     * @param id
     * @param demandaDto
     * @return
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
     *
     * @param id
     * @return
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
     *
     * @param id
     * @return
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
