package org.efbiz.ability.exam.portal.service;


import org.efbiz.ability.exam.common.domain.exam.ExamPaper;

public interface ExamPaperService {

  /**
   * 获取一张试卷
   *
   * @param examPaperId
   *
   * @return
   */
  public ExamPaper getExamPaperById(int examPaperId);


}
