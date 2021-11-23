package com.superngb.monitoring_system.DTOs.event;

import com.superngb.monitoring_system.Entities.event.Checkpoint;
import com.superngb.monitoring_system.Entities.mark.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckpointDtoModel implements Serializable {

    private Long id;
    private String name;
    private Long subject;
    private List<Long> scoreList;

    static public CheckpointDtoModel checkpointMapper(Checkpoint checkpoint) {
        List<Long> scoreList = new ArrayList<>();

        for (Score score: checkpoint.getScoreList())
            scoreList.add(score.getId());

        return new CheckpointDtoModel(checkpoint.getId(), checkpoint.getName(), checkpoint.getSubject().getId(), scoreList);
    }

    static public List<CheckpointDtoModel> listCheckpointsMapper(List<Checkpoint> checkpoints) {
        List<CheckpointDtoModel> checkpointDtoModels = new ArrayList<>();
        for (Checkpoint checkpoint : checkpoints)
            checkpointDtoModels.add(checkpointMapper(checkpoint));
        return checkpointDtoModels;
    }
}
