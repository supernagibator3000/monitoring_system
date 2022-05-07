package com.superngb.monitoring_system.Entities.person;

import com.superngb.monitoring_system.Entities.Subject;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personality_id", referencedColumnName = "id")
    private Personality personality;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="teachers_subjects",
            joinColumns = {@JoinColumn(name="teacher_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id", referencedColumnName="id")}
    )
//    @JsonIgnore
    private List<Subject> subjects;
}
