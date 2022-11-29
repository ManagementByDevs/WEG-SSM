package net.weg.wegssm.controller;

import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.DemandaService;
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
import java.util.List;

@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm/demanda")
public class DemandaController {

    private DemandaService demandaService;

    /**
     * Método GET para buscar todas as demandas
     */
    @GetMapping
    public ResponseEntity<List<Demanda>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.findAll());
    }

    /**
     * Função principal para buscar demandas, retornando uma página com as demandas seguindo o filtro utilizado
     * @param pageable - Objeto que contém as informações da páginação
     * @param titulo - Título da demanda (usado na barra de pesquisa)
     * @param solicitanteJson - Solicitante da demanda (usado no modal de filtro)
     * @param gerenteJson - Gerente da demanda (usado no modal de filtro)
     * @param forumJson - Fórum da demanda (usado no modal de filtro)
     * @param departamentoJson - Departamento da demanda (usado no modal de filtro)
     * @param tamanho - Tamanho da demanda (usado no modal de filtro)
     * @param status - Status da demanda (usado sempre quando é feita a busca)
     * @return - Retorna uma página com as demandas encontradas
     */
    @GetMapping("/page")
    public ResponseEntity<Page<Demanda>> findPage(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "solicitante", required = false) String solicitanteJson,
            @RequestParam(value = "gerente", required = false) String gerenteJson,
            @RequestParam(value = "forum", required = false) String forumJson,
            @RequestParam(value = "departamento", required = false) String departamentoJson,
            @RequestParam(value = "tamanho", required = false) String tamanho,
            @RequestParam(value = "status", required = false) Status status
    ) {
        System.out.println("JSON - " + solicitanteJson);
        Usuario solicitante = new UsuarioUtil().convertJsonToModel(solicitanteJson);
        Usuario gerente = new UsuarioUtil().convertJsonToModel(gerenteJson);
        Forum forum = new ForumUtil().convertJsonToModel(forumJson);
        Departamento departamento = new DepartamentoUtil().convertJsonToModel(departamentoJson);


        System.out.println(solicitante);
        if (titulo != null && !titulo.isEmpty()) {
            if (gerente != null) {
                if (forum != null) {
                    if (departamento != null) {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(status, titulo, gerente, forum, departamento, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(status, titulo, gerente, forum, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndDepartamento(status, titulo, gerente, forum, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndTamanho(status, titulo, gerente, forum, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndForumAndSolicitante(status, titulo, gerente, forum, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndForum(status, titulo, gerente, forum, pageable)
                                );
                            }
                        }
                    }
                } else {
                    if (departamento != null) {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndDepartamentoAndTamanho(status, titulo, gerente, departamento, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(status, titulo, gerente, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndDepartamento(status, titulo, gerente, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndTamanhoAndSolicitante(status, titulo, gerente, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndTamanho(status, titulo, gerente, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerenteAndSolicitante(status, titulo, gerente, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndGerente(status, titulo, gerente, pageable)
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
                                        demandaService.findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(status, titulo, forum, departamento, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndForumAndDepartamentoAndTamanho(status, titulo, forum, departamento, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndForumAndDepartamentoAndSolicitante(status, titulo, forum, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndForumAndDepartamento(status, titulo, forum, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndForumAndTamanhoAndSolicitante(status, titulo, forum, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndForumAndTamanho(status, titulo, forum, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndForumAndSolicitante(status, titulo, forum, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndForum(status, titulo, forum, pageable)
                                );
                            }
                        }
                    }
                } else {
                    if (departamento != null) {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(status, titulo, departamento, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndDepartamentoAndTamanho(status, titulo, departamento, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndDepartamentoAndSolicitante(status, titulo, departamento, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndDepartamento(status, titulo, departamento, pageable)
                                );
                            }
                        }
                    } else {
                        if (tamanho != null && !tamanho.isEmpty()) {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndTamanhoAndSolicitante(status, titulo, tamanho, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndTamanho(status, titulo, tamanho, pageable)
                                );
                            }
                        } else {
                            if (solicitante != null) {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContainingAndSolicitante(status, titulo, solicitante, pageable)
                                );
                            } else {
                                return ResponseEntity.status(HttpStatus.OK).body(
                                        demandaService.findByStatusAndTituloContaining(status, titulo, pageable)
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
     * Método POST para criar uma demanda adicionado um ou vários anexos
     * @param files
     * @param demandaJSON
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestParam("anexos")List<MultipartFile> files, @RequestParam("demanda") String demandaJSON) {
        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModel(demandaJSON);

        demanda.setAnexos(files);

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
