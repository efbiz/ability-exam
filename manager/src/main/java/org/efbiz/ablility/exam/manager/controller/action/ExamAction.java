package org.efbiz.ablility.exam.manager.controller.action;

import com.google.gson.Gson;
import java.util.List;
import org.efbiz.ability.exam.common.domain.exam.AnswerSheet;
import org.efbiz.ability.exam.common.domain.exam.AnswerSheetItem;
import org.efbiz.ability.exam.common.domain.exam.ExamPaper;
import org.efbiz.ability.exam.common.domain.exam.Message;
import org.efbiz.ablility.exam.manager.service.ExamPaperService;
import org.efbiz.ablility.exam.manager.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExamAction {

  @Autowired
  private ExamPaperService examPaperService;
  @Autowired
  private ExamService examService;

  @RequestMapping(value = "/api/exampaper/{id}", method = RequestMethod.GET)
  public @ResponseBody
  ExamPaper getExamPaper(@PathVariable("id") int id) {
    ExamPaper paper = examPaperService.getExamPaperById(id);
    return paper;
  }

  @RequestMapping(value = "/api/answersheet", method = RequestMethod.POST)
  public @ResponseBody
  Message submitAnswerSheet(@RequestBody AnswerSheet answerSheet) {

    List<AnswerSheetItem> itemList = answerSheet.getAnswerSheetItems();

    //全部是客观题，则状态更改为已阅卷
    int approved = 3;
    for (AnswerSheetItem item : itemList) {
      if (item.getQuestionTypeId() != 1 && item.getQuestionTypeId() != 2
          && item.getQuestionTypeId() != 3) {
        approved = 2;
        break;
      }
    }
    Gson gson = new Gson();
    examService.updateUserExamHist(answerSheet, gson.toJson(answerSheet), approved);

    return new Message();
  }

}
