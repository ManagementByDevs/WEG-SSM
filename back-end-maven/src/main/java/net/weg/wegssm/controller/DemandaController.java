package net.weg.wegssm.controller;

import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.HistoricoDTO;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.*;
import net.weg.wegssm.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Classe controller da demanda
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm/demanda")
public class DemandaController {

    private DemandaService demandaService;
    private UsuarioService usuarioService;
    private PropostaService propostaService;

    private DocumentoHistoricoService documentoHistoricoService;
    private HistoricoService historicoService;

    private PDFGeneratorService pdfGeneratorService;

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
    public ResponseEntity<Page<Demanda>> findPage(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
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
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, gerente, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(id, analista, titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(id, analista, titulo, gerente, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(id, analista, titulo, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(id, analista, titulo, gerente, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(id, analista, titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamentoAndStatus(id, analista, titulo, gerente, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndDepartamento(id, analista, titulo, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, gerente, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(id, analista, titulo, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanhoAndStatus(id, analista, titulo, gerente, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndTamanho(id, analista, titulo, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitanteAndStatus(id, analista, titulo, gerente, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndSolicitante(id, analista, titulo, gerente, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForumAndStatus(id, analista, titulo, gerente, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndForum(id, analista, titulo, gerente, forum, pageable)
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
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, gerente, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(id, analista, titulo, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndStatus(id, analista, titulo, gerente, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndTamanho(id, analista, titulo, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndStatus(id, analista, titulo, gerente, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(id, analista, titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamentoAndStatus(id, analista, titulo, gerente, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndDepartamento(id, analista, titulo, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, gerente, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndSolicitante(id, analista, titulo, gerente, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndTamanhoAndStatus(id, analista, titulo, gerente, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndTamanho(id, analista, titulo, gerente, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndSolicitanteAndStatus(id, analista, titulo, gerente, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndSolicitante(id, analista, titulo, gerente, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerenteAndStatus(id, analista, titulo, gerente, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndGerente(id, analista, titulo, gerente, pageable)
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
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(id, analista, titulo, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanhoAndStatus(id, analista, titulo, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndTamanho(id, analista, titulo, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndStatus(id, analista, titulo, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndSolicitante(id, analista, titulo, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndDepartamentoAndStatus(id, analista, titulo, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndDepartamento(id, analista, titulo, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndTamanhoAndSolicitante(id, analista, titulo, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndTamanhoAndStatus(id, analista, titulo, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndTamanho(id, analista, titulo, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndSolicitanteAndStatus(id, analista, titulo, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndSolicitante(id, analista, titulo, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForumAndStatus(id, analista, titulo, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndForum(id, analista, titulo, forum, pageable)
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
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(id, analista, titulo, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndDepartamentoAndTamanhoAndStatus(id, analista, titulo, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndDepartamentoAndTamanho(id, analista, titulo, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndDepartamentoAndSolicitanteAndStatus(id, analista, titulo, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndDepartamentoAndSolicitante(id, analista, titulo, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndDepartamentoAndStatus(id, analista, titulo, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndDepartamento(id, analista, titulo, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndTamanhoAndSolicitanteAndStatus(id, analista, titulo, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndTamanhoAndSolicitante(id, analista, titulo, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndTamanhoAndStatus(id, analista, titulo, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndTamanho(id, analista, titulo, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndSolicitanteAndStatus(id, analista, titulo, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndSolicitante(id, analista, titulo, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContainingAndStatus(id, analista, titulo, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndAnalistaAndTituloContaining(id, analista, titulo, pageable)
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
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, titulo, gerente, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndSolicitante(id, titulo, gerente, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanhoAndStatus(id, titulo, gerente, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndTamanho(id, titulo, gerente, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitanteAndStatus(id, titulo, gerente, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndSolicitante(id, titulo, gerente, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndDepartamentoAndStatus(id, titulo, gerente, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndDepartamento(id, titulo, gerente, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitanteAndStatus(id, titulo, gerente, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndTamanhoAndSolicitante(id, titulo, gerente, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndTamanhoAndStatus(id, titulo, gerente, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndTamanho(id, titulo, gerente, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndSolicitanteAndStatus(id, titulo, gerente, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndSolicitante(id, titulo, gerente, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForumAndStatus(id, titulo, gerente, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndForum(id, titulo, gerente, forum, pageable)
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
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, titulo, gerente, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndSolicitante(id, titulo, gerente, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndDepartamentoAndTamanhoAndStatus(id, titulo, gerente, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndDepartamentoAndTamanho(id, titulo, gerente, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndDepartamentoAndSolicitanteAndStatus(id, titulo, gerente, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndDepartamentoAndSolicitante(id, titulo, gerente, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndDepartamentoAndStatus(id, titulo, gerente, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndDepartamento(id, titulo, gerente, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndTamanhoAndSolicitanteAndStatus(id, titulo, gerente, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndTamanhoAndSolicitante(id, titulo, gerente, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndTamanhoAndStatus(id, titulo, gerente, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndTamanho(id, titulo, gerente, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndSolicitanteAndStatus(id, titulo, gerente, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndSolicitante(id, titulo, gerente, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerenteAndStatus(id, titulo, gerente, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndGerente(id, titulo, gerente, pageable)
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
                                                    demandaService.findByIdAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, titulo, forum, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndDepartamentoAndTamanhoAndSolicitante(id, titulo, forum, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndDepartamentoAndTamanhoAndStatus(id, titulo, forum, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndDepartamentoAndTamanho(id, titulo, forum, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndDepartamentoAndSolicitanteAndStatus(id, titulo, forum, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndDepartamentoAndSolicitante(id, titulo, forum, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndDepartamentoAndStatus(id, titulo, forum, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndDepartamento(id, titulo, forum, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndTamanhoAndSolicitanteAndStatus(id, titulo, forum, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndTamanhoAndSolicitante(id, titulo, forum, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndTamanhoAndStatus(id, titulo, forum, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndTamanho(id, titulo, forum, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndSolicitanteAndStatus(id, titulo, forum, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndSolicitante(id, titulo, forum, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForumAndStatus(id, titulo, forum, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndForum(id, titulo, forum, pageable)
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
                                                    demandaService.findByIdAndTituloContainingAndDepartamentoAndTamanhoAndSolicitanteAndStatus(id, titulo, departamento, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndDepartamentoAndTamanhoAndSolicitante(id, titulo, departamento, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndDepartamentoAndTamanhoAndStatus(id, titulo, departamento, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndDepartamentoAndTamanho(id, titulo, departamento, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndDepartamentoAndSolicitanteAndStatus(id, titulo, departamento, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndDepartamentoAndSolicitante(id, titulo, departamento, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndDepartamentoAndStatus(id, titulo, departamento, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndDepartamento(id, titulo, departamento, pageable)
                                            );
                                        }
                                    }
                                }
                            } else {
                                if (tamanho != null && !tamanho.isEmpty()) {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndTamanhoAndSolicitanteAndStatus(id, titulo, tamanho, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndTamanhoAndSolicitante(id, titulo, tamanho, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndTamanhoAndStatus(id, titulo, tamanho, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndTamanho(id, titulo, tamanho, pageable)
                                            );
                                        }
                                    }
                                } else {
                                    if (solicitante != null) {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndSolicitanteAndStatus(id, titulo, solicitante, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndSolicitante(id, titulo, solicitante, pageable)
                                            );
                                        }
                                    } else {
                                        if (status != null) {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContainingAndStatus(id, titulo, status, pageable)
                                            );
                                        } else {
                                            return ResponseEntity.status(HttpStatus.OK).body(
                                                    demandaService.findByIdAndTituloContaining(id, titulo, pageable)
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
    }

    /**
     * Método GET para buscar uma demanda específica através do id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        if (!demandaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma demanda com este id.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(demandaService.findById(id).get());
    }

    @GetMapping("/ppm/{ppm}")
    public ResponseEntity<Object> findByPPM(@PathVariable(value = "ppm") Long ppm) {
        if (!propostaService.existsByPPM(ppm)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma proposta com este id.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(propostaService.findByPPM(ppm));
    }

    /**
     * Método POST para criar uma demanda adicionado um ou vários anexos
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

        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /**
     * Método PUT para editar uma demanda já existente
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestParam("demanda") String demandaJSON) {
        DemandaUtil demandaUtil = new DemandaUtil();
        Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);

        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    /** Função utilizada para somente atualizar o status de uma demanda, recebendo como parâmetros o id e o novo status da demanda.
     * Usada na aceitação/recusa do gerente e na criação/edição de propostas para acompanhamento do status da demanda */
    @PutMapping("/status/{id}/{status}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable(value = "id") Long id,
                                                  @PathVariable(value = "status") Status status) {
        Demanda demanda = demandaService.findById(id).get();
        demanda.setStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(demandaService.save(demanda));
    }

    @PutMapping("/add-historico/{idDemanda}")
    public ResponseEntity<Demanda> addHistorico(@PathVariable(value = "idDemanda") Long idDemanda,
                                                @RequestParam(value = "historico") String historicoJson,
                                                @RequestParam(value = "documento") MultipartFile documento) {

        HistoricoUtil util = new HistoricoUtil();
        Historico historico = util.convertJsonToModel(historicoJson);
        Demanda demanda = demandaService.findById(idDemanda).get();

        historico.setDocumentoMultipart(documento);
        List<Historico> listaHistorico = demanda.getHistoricoDemanda();

        DocumentoHistorico documentoHistorico = historico.getDocumentoHistorico();
        documentoHistorico.setNome(demanda.getTitulo() + " - Versão " + (listaHistorico.size() + 1));

        listaHistorico.add(historicoService.save(historico));
        demanda.setHistoricoDemanda(listaHistorico);

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
