package com.superngb.monitoring_system.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.superngb.monitoring_system.Entities.event.Checkpoint;
import com.superngb.monitoring_system.Entities.event.Lesson;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Entities.person.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subject")
//    @JsonIgnore
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "subject")
//    @JsonIgnore
    private List<Checkpoint> checkpoints;

//    @Transient
    @ManyToMany(mappedBy = "subjects")
//    @JsonIgnore
    private List<Group> groups;

//    @Transient
    @ManyToMany(mappedBy = "subjects")
//    @JsonIgnore
    private List<Student> students;

//    @Transient
    @ManyToMany(mappedBy = "subjects")
//    @JsonIgnore
    private List<Teacher> teachers;
}
