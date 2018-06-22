package org.efbiz.ablility.exam.manager.controller.action.teacher;

import java.util.List;
import org.efbiz.ability.exam.common.domain.question.Field;
import org.efbiz.ablility.exam.manager.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DashBoardPageTeacher {

  @Autowired
  private QuestionService questionService;


  @RequestMapping(value = "/teacher/dashboard", method = RequestMethod.GET)
  public String dashboardPage(Model model) {

    List<Field> fieldList = questionService.getAllField(null);

    model.addAttribute("fieldList", fieldList);
    return "dashboard";
  }

}
