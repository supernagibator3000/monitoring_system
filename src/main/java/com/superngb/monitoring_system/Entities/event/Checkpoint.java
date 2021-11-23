package com.superngb.monitoring_system.Entities.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.mark.Score;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "checkpoints")
public class Checkpoint{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="subjects_checkpoints",
            joinColumns = {@JoinColumn(name="checkpoint_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id", referencedColumnName="id")}
    )
    private Subject subject;

    @OneToMany(mappedBy = "checkpoint",fetch = FetchType.LAZY)
//    @JsonIgnore
    private List<Score> scoreList;
}
