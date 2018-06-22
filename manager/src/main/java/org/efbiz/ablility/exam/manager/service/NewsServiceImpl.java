package org.efbiz.ablility.exam.manager.service;

import java.util.List;
import org.efbiz.ability.exam.common.domain.news.News;
import org.efbiz.ability.exam.common.util.Page;
import org.efbiz.ablility.exam.manager.persistence.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NewsServiceImpl implements NewsService {

  @Autowired
  private NewsMapper newsMapper;

  @Override
  public List<News> getNewsList(Page<News> page) {
    // TODO Auto-generated method stub
    return newsMapper.getNewsList(page);
  }

  @Override
  public News getNewsById(int newsId) {
    // TODO Auto-generated method stub
    return newsMapper.getNewsById(newsId);
  }

  @Override
  public void addNews(News news) {
    // TODO Auto-generated method stub
    newsMapper.addNews(news);
  }

}
