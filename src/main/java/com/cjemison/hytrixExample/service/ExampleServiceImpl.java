package com.cjemison.hytrixExample.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjemison on 6/5/16.
 */
@Service
public class ExampleServiceImpl implements ExampleService {
  private static final Logger logger = LoggerFactory.getLogger(ExampleServiceImpl.class);

  @Override
  @HystrixCommand(groupKey = "startTrekLookup", fallbackMethod = "failure")
  public List<String> getStarTrekArticles() throws Exception {
    List<String> list = new ArrayList<>();
    try {
      Document doc = Jsoup.connect("http://www.chicagotribune.com/rss2.0.xml").get();
      doc.select("rss").select("channel").select("item").stream().forEach(c -> {
        list.add(c.getElementsByTag("title").text());
        logger.trace(c.getElementsByTag("title").text());
      });
      logger.info("Thread ID: {}", Thread.currentThread().getId());
      return list;
    } catch (Exception e) {
      logger.error("### ERROR ###", e);
      throw e;
    }
  }

  public List<String> failure() {
    return new ArrayList<>(0);
  }
}
