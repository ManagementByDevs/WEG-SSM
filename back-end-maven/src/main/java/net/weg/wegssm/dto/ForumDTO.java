package net.weg.wegssm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ForumDTO {

    @NotNull
    private String nome;

}
