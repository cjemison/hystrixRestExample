package com.cjemison.hytrixExample.controller;

import com.cjemison.hytrixExample.service.ExampleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by cjemison on 6/5/16.
 */
@RestController
public class ExampleControllerImpl implements ExampleController {
  public static final Logger logger = LoggerFactory.getLogger(ExampleControllerImpl.class);
  private final ExampleService exampleService;

  @Autowired
  public ExampleControllerImpl(ExampleService es) {
    this.exampleService = es;
  }

  @Override
  @RequestMapping(value = "/v1/example",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.GET)
  public DeferredResult<ResponseEntity<?>> get() throws Exception {
    logger.info("Thread ID: {}", Thread.currentThread().getId());
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
    logger.info("Performing Get");
    ResponseEntity<?> responseEntity = ResponseEntity.ok(exampleService
          .getStarTrekArticles());
    deferredResult.setResult(responseEntity);
    logger.info("Performing Get...exit");
    return deferredResult;
  }
}
