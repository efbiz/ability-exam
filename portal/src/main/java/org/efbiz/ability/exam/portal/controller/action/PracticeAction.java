package org.efbiz.ability.exam.portal.controller.action;

import org.efbiz.ability.exam.common.domain.exam.Message;
import org.efbiz.ability.exam.common.domain.exam.UserQuestionHistory;
import org.efbiz.ability.exam.common.domain.question.QuestionHistory;
import org.efbiz.ability.exam.portal.security.UserInfo;
import org.efbiz.ability.exam.portal.service.QuestionHistoryService;
import org.efbiz.ability.exam.portal.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PracticeAction {

  @Autowired
  private QuestionService questionService;
  @Autowired
  private QuestionHistoryService questionHistoryService;

  /**
   * 练习模式完成一道题
   *
   * @param qh
   *
   * @return
   */
  @RequestMapping(value = "/student/practice-improve", method = RequestMethod.POST)
  public @ResponseBody
  Message submitPractice(@RequestBody QuestionHistory qh) {
    Message msg = new Message();
    UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();

    UserQuestionHistory history = new UserQuestionHistory();
    history.setQuestionId(qh.getQuestionId());
    history.setUserId(userInfo.getUserid());
    history.setQuestionTypeId(qh.getQuestionTypeId());
    boolean isRight = qh.getAnswer().equals(qh.getMyAnswer()) ? true : false;
    history.setRight(isRight);

    try {
      questionHistoryService.addUserQuestionHist(history);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      msg.setResult(e.getClass().getName());
      e.printStackTrace();
    }

    return msg;
  }
}
