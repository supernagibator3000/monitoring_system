package com.superngb.monitoring_system.DTOs.mark;

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
public class ScoreDtoModel implements Serializable {

    private Long id;
    private Long student;
    private String scoreMark;
    private Long checkpoint;

    static public ScoreDtoModel scoreMapper(Score score){
        return new ScoreDtoModel(score.getId(), score.getStudent().getId(), score.getScoreMark().getDescription(), score.getCheckpoint().getId());
    }

    static public List<ScoreDtoModel> listScoresMapper(List<Score> scoreList){
        List<ScoreDtoModel> scoreDtoModels = new ArrayList<>();
        for (Score score: scoreList)
            scoreDtoModels.add(scoreMapper(score));
        return scoreDtoModels;
    }
}
