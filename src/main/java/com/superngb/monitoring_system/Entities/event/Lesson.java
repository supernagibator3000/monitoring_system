package com.superngb.monitoring_system.Entities.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.mark.Attendance;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="subjects_lessons",
            joinColumns = {@JoinColumn(name="lesson_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id", referencedColumnName="id")}
    )
    private Subject subject;

    @OneToMany(mappedBy = "lesson",fetch = FetchType.LAZY)
//    @JsonIgnore
    private List<Attendance> attendanceList;
}
