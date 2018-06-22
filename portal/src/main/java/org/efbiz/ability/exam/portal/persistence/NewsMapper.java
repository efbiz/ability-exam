package org.efbiz.ability.exam.portal.persistence;

import java.util.List;
import org.efbiz.ability.exam.common.domain.news.News;
import org.efbiz.ability.exam.common.util.Page;

public interface NewsMapper {

  public List<News> getNewsList(Page<News> page);

  public News getNewsById(int newsId);
}
