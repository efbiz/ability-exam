package org.efbiz.ability.exam.portal.controller.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.efbiz.ability.exam.common.domain.exam.Message;
import org.efbiz.ability.exam.common.domain.question.Comment;
import org.efbiz.ability.exam.common.domain.question.Comments;
import org.efbiz.ability.exam.common.util.Page;
import org.efbiz.ability.exam.portal.security.UserInfo;
import org.efbiz.ability.exam.portal.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CommentAction {

  @Autowired
  private CommentService commentService;

  @RequestMapping(value = "student/comment-list/{commentType}/{referId}/{index}/{indexId}", method = RequestMethod.GET)
  public @ResponseBody
  Message getQuestionComments(@PathVariable("commentType") int commentType,
      @PathVariable("referId") int referId, @PathVariable("index") int index,
      @PathVariable("indexId") int indexId) {
    if (index <= 0) {
      index = 1;
    }
    Message msg = new Message();
    msg.setMessageInfo("not-has-next");
    Page<Comment> page = new Page<Comment>();
    if (index == 1) {
      page.setPageNo(index);
      page.setPageSize(6);
    } else {
      index = index + 2;
      page.setPageNo(index);
      page.setPageSize(2);
    }

    try {
      List<Comment> commentList = commentService
          .getCommentByTypeAndReferId(commentType, referId, indexId, page);
      Comments c = new Comments();
      c.setComments(commentList);
      c.setSize(page.getTotalRecord());
      if (page.getTotalRecord() > page.getPageSize() * index) {
        msg.setMessageInfo("has-next");
      }
      msg.setObject(c);
    } catch (Exception e) {
      msg.setResult(e.getClass().getName());
      e.printStackTrace();
    }

    return msg;
  }

  @RequestMapping(value = "student/submit-comment", method = RequestMethod.POST)
  public @ResponseBody
  Message submitComment(@RequestBody Comment comment, HttpServletRequest request) {
    Message msg = new Message();
    UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    try {
      comment.setUserId(userInfo.getUserid());
      commentService.addComment(comment);

    } catch (Exception e) {
      e.printStackTrace();
      msg.setResult(e.getClass().getName());
    }
    return msg;

  }

}
