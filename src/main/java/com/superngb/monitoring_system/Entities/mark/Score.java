package com.superngb.monitoring_system.Entities.mark;

import com.superngb.monitoring_system.Entities.event.Checkpoint;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Enums.ScoreEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="students_scores",
            joinColumns = {@JoinColumn(name="score_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")}
    )
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "mark")
    private ScoreEnum scoreMark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="checkpoints_scores",
            joinColumns = {@JoinColumn(name="score_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="checkpoint_id", referencedColumnName="id")}
    )
    private Checkpoint checkpoint;
}
