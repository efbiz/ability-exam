package org.efbiz.ability.exam.portal.service;

import java.util.List;
import java.util.Map;
import org.efbiz.ability.exam.common.domain.question.Field;
import org.efbiz.ability.exam.common.domain.question.KnowledgePoint;
import org.efbiz.ability.exam.common.domain.question.Question;
import org.efbiz.ability.exam.common.domain.question.QuestionQueryResult;
import org.efbiz.ability.exam.common.domain.question.QuestionStatistic;
import org.efbiz.ability.exam.common.domain.question.QuestionType;
import org.efbiz.ability.exam.common.util.Page;


/**
 * @author Joni
 * @date 2018年6月8日 下午5:52:44
 */
public interface QuestionService {

  public Map<Integer, QuestionType> getQuestionTypeMap();

  public Question getQuestionByQuestionId(int questionId);

  public List<QuestionQueryResult> getQuestionAnalysisListByPointIdAndTypeId(
      int typeId, int pointId);

  /**
   * 获取所有的Field
   *
   * @param page
   *
   * @return
   */
  public List<Field> getAllField(Page<Field> page);

  /**
   * 获取Field下的知识点
   *
   * @param fieldIdList 为null则获取所有知识点
   * @param page
   *
   * @return
   */
  public Map<Integer, KnowledgePoint> getKnowledgePointByFieldId(Page<KnowledgePoint> page,
      int... fieldIdList);

  /**
   * 按专业获取试题
   *
   * @param fieldId
   * @param page
   *
   * @return
   */
  public Map<Integer, Map<Integer, List<QuestionQueryResult>>> getQuestionMapByFieldId(int fieldId,
      Page<QuestionQueryResult> page);

  /**
   * 根据试题id获取试题清单
   *
   * @param idList
   *
   * @return
   */
  List<QuestionQueryResult> getQuestionAnalysisListByIdList(List<Integer> idList);

  /**
   * 根据fieldId,pointId分组统计试题数量
   *
   * @param fieldId
   *
   * @return
   */
  public Map<Integer, QuestionStatistic> getQuestionStaticByFieldId(int fieldId);

  /**
   * 根据fieldId,pointId,typeId分组统计试题数量
   *
   * @param fieldId
   *
   * @return
   */
  public Map<Integer, Map<Integer, QuestionStatistic>> getTypeQuestionStaticByFieldId(int fieldId);
}
