package com.superngb.monitoring_system.Entities.event;

import com.superngb.monitoring_system.Entities.mark.Score;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "checkpoints")
public class Checkpoint{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @OneToMany(mappedBy = "checkpoint",fetch = FetchType.LAZY)
    private List<Score> scoreList;
}
