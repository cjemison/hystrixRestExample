package com.cjemison.hytrixExample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by cjemison on 6/5/16.
 */
public interface ExampleController {

  DeferredResult<ResponseEntity<?>> get() throws Exception;
}
