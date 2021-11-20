package com.superngb.monitoring_system.Entities.event;

import com.superngb.monitoring_system.Entities.Subject;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="subjects_events",
            joinColumns = {@JoinColumn(name="event_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id", referencedColumnName="id")}
    )
    private Subject subject;
}
