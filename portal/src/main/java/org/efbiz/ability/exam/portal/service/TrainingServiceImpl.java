package org.efbiz.ability.exam.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.efbiz.ability.exam.common.domain.training.Training;
import org.efbiz.ability.exam.common.domain.training.TrainingSection;
import org.efbiz.ability.exam.common.domain.training.TrainingSectionProcess;
import org.efbiz.ability.exam.common.domain.training.UserTrainingHistory;
import org.efbiz.ability.exam.common.util.Page;
import org.efbiz.ability.exam.portal.persistence.TrainingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {

  @Autowired
  private TrainingMapper trainingMapper;

  @Override
  public List<Training> getTrainingList(Page<Training> page) {
    // TODO Auto-generated method stub
    return trainingMapper.getTrainingList(page);
  }

  @Override
  public List<TrainingSection> getTrainingSectionByTrainingId(int trainingId,
      Page<TrainingSection> page) {
    // TODO Auto-generated method stub
    return trainingMapper.getTrainingSectionByTrainingId(trainingId, page);
  }

  @Override
  public List<TrainingSection> getTrainingSectionById(int sectionId, Page<TrainingSection> page) {
    // TODO Auto-generated method stub
    return trainingMapper.getTrainingSectionById(sectionId, page);
  }

  @Override
  public UserTrainingHistory getTrainingHistBySectionId(int sectionId, int userId) {
    // TODO Auto-generated method stub
    return trainingMapper.getTrainingHistBySectionId(sectionId, userId);
  }

  @Override
  public void setUserTrainingHistory(UserTrainingHistory hist) {
    // TODO Auto-generated method stub
    trainingMapper.setUserTrainingHistory(hist);
  }

  @Override
  public Map<Integer, List<TrainingSectionProcess>> getTrainingSectionProcessMapByUserId(
      int userId) {
    // TODO Auto-generated method stub
    List<TrainingSectionProcess> processList = trainingMapper
        .getTrainingSectionProcessListByUserId(userId, null);
    HashMap<Integer, List<TrainingSectionProcess>> map = new HashMap<Integer, List<TrainingSectionProcess>>();
    for (TrainingSectionProcess process : processList) {
      List<TrainingSectionProcess> tmpList = new ArrayList<TrainingSectionProcess>();
      if (map.containsKey(process.getTrainingId())) {
        tmpList = map.get(process.getTrainingId());
      }
      tmpList.add(process);
      map.put(process.getTrainingId(), tmpList);
    }
    return map;
  }
}
