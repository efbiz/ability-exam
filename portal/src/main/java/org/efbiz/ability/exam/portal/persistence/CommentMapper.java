package org.efbiz.ability.exam.portal.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.efbiz.ability.exam.common.domain.question.Comment;
import org.efbiz.ability.exam.common.util.Page;


/**
 * @author Joni
 * @date 2018年6月8日 下午8:32:33
 */
public interface CommentMapper {

  List<Comment> getCommentByTypeAndReferId(@Param("commentType") int commentType,
      @Param("referId") int referId, @Param("indexId") int indexId,
      @Param("page") Page<Comment> page);

  /**
   * 添加评论
   *
   * @param comment
   */
  public void addComment(Comment comment);

  public Integer getMaxCommentIndexByTypeAndReferId(@Param("commentType") int commentType,
      @Param("referId") int referId);
}
