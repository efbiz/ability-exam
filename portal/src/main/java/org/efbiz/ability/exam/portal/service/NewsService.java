package org.efbiz.ability.exam.portal.service;

import java.util.List;
import org.efbiz.ability.exam.common.domain.news.News;
import org.efbiz.ability.exam.common.util.Page;
import org.springframework.stereotype.Service;


@Service
public interface NewsService {

  public List<News> getNewsList(Page<News> page);

  public News getNewsById(int newsId);

}
