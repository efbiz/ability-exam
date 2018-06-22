package org.efbiz.ability.exam.portal.service;

import java.util.List;
import java.util.Map;
import org.efbiz.ability.exam.common.domain.training.Training;
import org.efbiz.ability.exam.common.domain.training.TrainingSection;
import org.efbiz.ability.exam.common.domain.training.TrainingSectionProcess;
import org.efbiz.ability.exam.common.domain.training.UserTrainingHistory;
import org.efbiz.ability.exam.common.util.Page;


public interface TrainingService {

  /**
   * 获取培训列表
   *
   * @param page
   *
   * @return
   */
  public List<Training> getTrainingList(Page<Training> page);

  /**
   * 获取培训章节
   *
   * @param trainingId
   * @param page
   *
   * @return
   */
  public List<TrainingSection> getTrainingSectionByTrainingId(int trainingId,
      Page<TrainingSection> page);

  /**
   * 获取培训章节
   *
   * @param sectionId
   * @param page
   *
   * @return
   */
  public List<TrainingSection> getTrainingSectionById(int sectionId, Page<TrainingSection> page);

  /**
   * 获取用户培训历史
   *
   * @param sectionId
   * @param userId
   *
   * @return
   */
  public UserTrainingHistory getTrainingHistBySectionId(int sectionId, int userId);

  /**
   * 增加或更新用户培训历史
   *
   * @param hist
   */
  public void setUserTrainingHistory(UserTrainingHistory hist);

  /**
   * 获取用户培训进度清单
   *
   * @param userId
   *
   * @return
   */
  public Map<Integer, List<TrainingSectionProcess>> getTrainingSectionProcessMapByUserId(
      int userId);
}
