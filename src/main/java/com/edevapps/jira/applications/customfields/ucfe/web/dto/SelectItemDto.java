/*
 * Copyright (c) 2020, The Eduard Burenkov. All rights reserved. http://edevapps.com
 */

package com.edevapps.jira.applications.customfields.ucfe.web.dto;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class SelectItemDto {
  private String value;
  private String name;
  private boolean selected;

  public SelectItemDto() {
  }

  public SelectItemDto(String value, String name, boolean selected) {
    this.value = value;
    this.name = name;
    this.selected = selected;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isSelected() {
    return this.selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
}