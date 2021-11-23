package com.superngb.monitoring_system.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.superngb.monitoring_system.Entities.person.Student;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group")
//    @JsonIgnore
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="groups_subjects",
            joinColumns = {@JoinColumn(name="group_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id", referencedColumnName="id")}
    )
//    @JsonIgnore
    private List<Subject> subjects;
}
