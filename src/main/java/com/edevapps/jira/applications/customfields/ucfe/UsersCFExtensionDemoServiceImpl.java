/*
 * Copyright (c) 2020, The Eduard Burenkov. All rights reserved. http://edevapps.com
 */

package com.edevapps.jira.applications.customfields.ucfe;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.sal.api.lifecycle.LifecycleAware;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

@ExportAsService({LifecycleAware.class})
@Named(UsersCFExtensionDemoServiceImpl.SERVICE_NAME)
public class UsersCFExtensionDemoServiceImpl implements LifecycleAware, DisposableBean {

  private static final Logger log = LoggerFactory.getLogger(UsersCFExtensionDemoServiceImpl.class);

  public static final String SERVICE_NAME = "usersCFExtensionDemoService";

  public UsersCFExtensionDemoServiceImpl() {
  }

  public void onStart() {
  }

  private void stop() {
  }

  public void onStop() {
    this.stop();
  }

  public void destroy() {
    this.stop();
  }
}