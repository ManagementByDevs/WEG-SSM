package net.weg.wegssm.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "forum")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;
}
