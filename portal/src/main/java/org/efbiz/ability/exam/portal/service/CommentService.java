package org.efbiz.ability.exam.portal.service;

import java.util.List;
import org.efbiz.ability.exam.common.domain.question.Comment;
import org.efbiz.ability.exam.common.util.Page;


public interface CommentService {

  public List<Comment> getCommentByTypeAndReferId(int referType, int referId, int indexId,
      Page<Comment> page);

  public void addComment(Comment comment);
}
