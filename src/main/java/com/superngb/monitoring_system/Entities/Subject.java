package com.superngb.monitoring_system.Entities;

import com.superngb.monitoring_system.Entities.event.Event;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Entities.person.Teacher;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Event> events;

    @Transient
    @ManyToMany(mappedBy = "subjects")
    private List<Group> groups;

    @Transient
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

    @Transient
    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teachers;
}
