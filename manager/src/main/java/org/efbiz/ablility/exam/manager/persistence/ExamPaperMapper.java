package org.efbiz.ablility.exam.manager.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.efbiz.ability.exam.common.domain.exam.ExamPaper;
import org.efbiz.ability.exam.common.util.Page;


public interface ExamPaperMapper {

  public List<ExamPaper> getExamPaperList(@Param("searchStr") String searchStr,
      @Param("paperType") String paperType, @Param("page") Page<ExamPaper> page);

  public void insertExamPaper(ExamPaper examPaper);

  public ExamPaper getExamPaperById(int examPaperId);

  public void updateExamPaper(ExamPaper examPaper);

  public void deleteExamPaper(int id);

  public List<ExamPaper> getEnabledExamPaperList(@Param("userName") String userName,
      @Param("page") Page<ExamPaper> page);
}
