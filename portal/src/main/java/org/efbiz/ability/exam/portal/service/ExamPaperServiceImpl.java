package org.efbiz.ability.exam.portal.service;

import org.efbiz.ability.exam.common.domain.exam.ExamPaper;
import org.efbiz.ability.exam.portal.persistence.ExamPaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("examPaperService")
public class ExamPaperServiceImpl implements ExamPaperService {

  @Autowired
  private ExamPaperMapper examPaperMapper;

  @Override
  public ExamPaper getExamPaperById(int examPaperId) {
    // TODO Auto-generated method stub
    return examPaperMapper.getExamPaperById(examPaperId);
  }

}
