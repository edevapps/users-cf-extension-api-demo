/*
 * Copyright (c) 2020, The Eduard Burenkov. All rights reserved. http://edevapps.com
 */

package com.edevapps.jira.applications.customfields.ucfe.web.webwork;

import com.atlassian.crowd.embedded.api.Group;
import com.atlassian.jira.bc.group.search.GroupPickerSearchService;
import com.atlassian.jira.bc.user.search.UserSearchParams;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.message.I18nResolver;
import com.edevapps.jira.applications.customfields.ucfe.api.UsersCFExtension;
import com.edevapps.jira.applications.customfields.ucfe.api.UsersCFExtensionService;
import com.edevapps.jira.applications.customfields.ucfe.web.dto.SelectItemDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class UsersCFExtensionDemoWebworkAction extends JiraWebActionSupport {

  private static final long serialVersionUID = 6870733586874615813L;

  private static final Logger log = LoggerFactory
      .getLogger(UsersCFExtensionDemoWebworkAction.class);
  private static final String DELIMITER = ",";
  public static final String EMPTY_STRING = "";
  private static final String NULL_VAL = "null";
  private static final String ACTION_PAR = "action";
  private static final String MESSAGE_VALUE_NOT_EMPTY_RES = "message-is-not-be-empty.message";
  public static final String SELECTED_PROJECT_KEY_PAR = "selectedProjectKey";
  public static final String SELECTED_CF_GROUP_FOR_GROUPS_PAR = "selectedCFGroupForGroups";
  public static final String SELECTED_CF_GROUP_FOR_USERS_GROUP_PAR = "selectedCFGroupForUsersGroup";
  public static final String SELECTED_DEFAULT_SINGLE_GROUP_PAR = "selectedDefaultValueForSingleGroup";
  public static final String SELECTED_DEFAULT_GROUPS_PAR = "selectedDefaultValueForGroups";
  public static final String SELECTED_DEFAULT_SINGLE_USER_PAR = "selectedDefaultValueForSingleUser";
  public static final String SELECTED_DEFAULT_USERS_PAR = "selectedDefaultValueForUsers";
  public static final String API_TYPE_PAR = "apiType";
  private static final String DEFAULT_HOME_PAGE_URL = "/secure/UsersCFExtensionDemo!default.jspa";

  enum ApiType {
    JAVA("java"),
    REST("Rest");

    private final String caption;

    ApiType(String caption) {
      this.caption = caption;
    }

    public String getCaption() {
      return caption;
    }
  }

  enum Action {
    ADD_GROUP_FOR_PROJECT,
    REMOVE_GROUP_FOR_PROJECT,
    SET_USERS_GROUP_FOR_PROJECT,
    SET_DEF_VALUE_FOR_SINGLE_GROUP,
    ADD_DEF_VALUE_FOR_GROUPS,
    REMOVE_DEF_VALUE_FOR_GROUPS,
    SET_DEF_VALUE_FOR_SINGLE_USER,
    ADD_DEF_VALUE_FOR_USERS,
    REMOVE_DEF_VALUE_FOR_USERS,
  }

  private UsersCFExtensionService usersCFExtension;
  private String error;
  private String message;
  private final I18nResolver i18nResolver;
  private final ProjectManager projectManager;
  private List<SelectItemDto> apiTypes;
  private List<SelectItemDto> allProjects;
  private List<SelectItemDto> selectCFGroups;
  private List<SelectItemDto> selectCFUsersGroups;
  private List<SelectItemDto> selectDefaultSingleUsersGroups;
  private List<SelectItemDto> selectDefaultValueForGroups;
  private List<SelectItemDto> selectDefaultSingleUsers;
  private List<SelectItemDto> selectDefaultValueForUsers;
  private String currentProjectGroups;
  private String currentProjectUsersGroup;
  private String currentDefaultValueForSingleGroup;
  private String currentDefaultValueForGroups;
  private String currentDefaultValueForSingleUser;
  private String currentDefaultValueForUsers;
  private String selectedProjectKey;
  private String selectedCFGroupForGroups;
  private String selectedCFGroupForUsersGroup;
  private String selectedDefaultSingleUsersGroup;
  private String selectedDefaultValueForGroups;
  private String selectedDefaultValueForSingleUser;
  private String selectedDefaultValueForUsers;
  private Action currentAction;
  private String apiType;

  @Inject
  public UsersCFExtensionDemoWebworkAction(
      @ComponentImport I18nResolver i18nResolver,
      @ComponentImport ProjectManager projectManager) {
    this.i18nResolver = i18nResolver;
    this.projectManager = projectManager;
    this.usersCFExtension = UsersCFExtension.getService();
    this.apiType = ApiType.JAVA.name();
  }

  public String getMessage() {
    return this.message;
  }

  public String getError() {
    return this.error;
  }

  public String getApiType() {
    return apiType;
  }

  public List<SelectItemDto> getApiTypes() {
    return apiTypes;
  }

  public List<SelectItemDto> getAllProjects() {
    return allProjects;
  }

  public List<SelectItemDto> getSelectCFGroups() {
    return selectCFGroups;
  }

  public String getCurrentProjectGroups() {
    return currentProjectGroups;
  }

  public String getSelectedCFGroupForGroups() {
    return selectedCFGroupForGroups;
  }

  public String getSelectedProjectKey() {
    return selectedProjectKey;
  }

  public String getSelectedCFGroupForUsersGroup() {
    return selectedCFGroupForUsersGroup;
  }

  public List<SelectItemDto> getSelectCFUsersGroups() {
    return selectCFUsersGroups;
  }

  public String getCurrentProjectUsersGroup() {
    return currentProjectUsersGroup;
  }

  public List<SelectItemDto> getSelectDefaultSingleUsersGroups() {
    return selectDefaultSingleUsersGroups;
  }

  public String getSelectedDefaultSingleUsersGroup() {
    return selectedDefaultSingleUsersGroup;
  }

  public String getCurrentDefaultValueForSingleGroup() {
    return currentDefaultValueForSingleGroup;
  }

  public List<SelectItemDto> getSelectDefaultValueForGroups() {
    return selectDefaultValueForGroups;
  }

  public String getSelectedDefaultValueForGroups() {
    return selectedDefaultValueForGroups;
  }

  public String getCurrentDefaultValueForGroups() {
    return currentDefaultValueForGroups;
  }

  public String getSelectedDefaultValueForSingleUser() {
    return selectedDefaultValueForSingleUser;
  }

  public String getCurrentDefaultValueForSingleUser() {
    return currentDefaultValueForSingleUser;
  }

  public List<SelectItemDto> getSelectDefaultSingleUsers() {
    return selectDefaultSingleUsers;
  }

  public String getCurrentDefaultValueForUsers() {
    return currentDefaultValueForUsers;
  }

  public String getSelectedDefaultValueForUsers() {
    return selectedDefaultValueForUsers;
  }

  public List<SelectItemDto> getSelectDefaultValueForUsers() {
    return selectDefaultValueForUsers;
  }

  public String doDefault() {
    try {
      loadContent();
    } catch (Exception ex) {
      this.error = ex.getMessage();
      log.error(this.error);
    }
    return SUCCESS;
  }

  protected String doExecute() {
    try {
      loadContent();
      if (this.currentAction != null) {
        if (Action.ADD_GROUP_FOR_PROJECT.equals(this.currentAction)) {
          addGroupForProject();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        } else if (Action.REMOVE_GROUP_FOR_PROJECT.equals(this.currentAction)) {
          removeGroupForProject();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        } else if (Action.SET_USERS_GROUP_FOR_PROJECT.equals(this.currentAction)) {
          setUsersGroupForProject();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        } else if (Action.SET_DEF_VALUE_FOR_SINGLE_GROUP.equals(this.currentAction)) {
          setDefaultGroupForSingleGroup();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        } else if (Action.ADD_DEF_VALUE_FOR_GROUPS.equals(this.currentAction)) {
          addDefaultForGroup();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        } else if (Action.REMOVE_DEF_VALUE_FOR_GROUPS.equals(this.currentAction)) {
          removeDefaultForGroup();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        } else if (Action.SET_DEF_VALUE_FOR_SINGLE_USER.equals(this.currentAction)) {
          setDefaultForSingleUser();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        } else if (Action.ADD_DEF_VALUE_FOR_USERS.equals(this.currentAction)) {
          addDefaultForUsers();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        } else if (Action.REMOVE_DEF_VALUE_FOR_USERS.equals(this.currentAction)) {
          removeDefaultForUsers();
          return forceRedirect(DEFAULT_HOME_PAGE_URL);
        }
      }
      this.message = this.i18nResolver.getText(MESSAGE_VALUE_NOT_EMPTY_RES);
    } catch (Exception ex) {
      this.error = ex.getMessage();
      log.error(this.error);
    }
    return SUCCESS;
  }

  private void addGroupForProject() {
    if (isEmpty(this.selectedProjectKey) || isEmpty(this.selectedCFGroupForGroups)) {
      throw new IllegalArgumentException("Project key or group values is not be empty.");
    }
    this.usersCFExtension
        .addGroupForProject(this.selectedProjectKey, this.selectedCFGroupForGroups);
  }

  private void removeGroupForProject() {
    if (isEmpty(this.selectedProjectKey) || isEmpty(this.selectedCFGroupForGroups)) {
      throw new IllegalArgumentException("Project key or group values is not be empty.");
    }
    this.usersCFExtension
        .removeGroupForProject(this.selectedProjectKey, this.selectedCFGroupForGroups);
  }

  private void setUsersGroupForProject() {
    checkSelectedProjectKey();
    this.usersCFExtension
        .setUsersGroupForProject(this.selectedProjectKey, this.selectedCFGroupForUsersGroup);
  }

  private void setDefaultGroupForSingleGroup() {
    checkSelectedProjectKey();
    this.usersCFExtension.setDefaultGroupForSingleGroupField(this.selectedProjectKey,
        this.selectedDefaultSingleUsersGroup);
  }

  private void addDefaultForGroup() {
    checkSelectedProjectKey();
    this.usersCFExtension.addDefaultGroupForGroupsField(this.selectedProjectKey,
        this.selectedDefaultValueForGroups);
  }

  private void removeDefaultForGroup() {
    checkSelectedProjectKey();
    this.usersCFExtension.removeDefaultGroupForGroupsField(this.selectedProjectKey,
        this.selectedDefaultValueForGroups);
  }

  private void setDefaultForSingleUser() {
    checkSelectedProjectKey();
    this.usersCFExtension.setDefaultUserForSingleUserField(this.selectedProjectKey,
        this.selectedDefaultValueForSingleUser);
  }

  private void addDefaultForUsers() {
    checkSelectedProjectKey();
    this.usersCFExtension.addDefaultUserForUsersField(this.selectedProjectKey,
        this.selectedDefaultValueForUsers);
  }

  private void removeDefaultForUsers() {
    checkSelectedProjectKey();
    this.usersCFExtension.removeDefaultUserForUsersField(this.selectedProjectKey,
        this.selectedDefaultValueForUsers);
  }

  private void checkSelectedProjectKey() {
    if (isEmpty(this.selectedProjectKey)) {
      throw new IllegalArgumentException("Project key value is not be empty.");
    }
  }

  private void loadContent() {
    //Current api type
    this.apiType = getCurrentApiType();
    //Selected values for selects
    this.apiTypes = loadApiTypes();
    this.selectedProjectKey = loadSelectedProjectKeyValue(
        SELECTED_PROJECT_KEY_PAR);
    this.selectedCFGroupForGroups = loadSelectedGroupNameValue(
        SELECTED_CF_GROUP_FOR_GROUPS_PAR, false);
    this.selectedCFGroupForUsersGroup = loadSelectedGroupNameValue(
        SELECTED_CF_GROUP_FOR_USERS_GROUP_PAR, true);
    this.selectedDefaultSingleUsersGroup = loadSelectedGroupNameValue(
        SELECTED_DEFAULT_SINGLE_GROUP_PAR, true);
    this.selectedDefaultValueForGroups = loadSelectedGroupNameValue(
        SELECTED_DEFAULT_GROUPS_PAR, false);
    this.selectedDefaultValueForSingleUser = loadSelectedUserNameValue(
        SELECTED_DEFAULT_SINGLE_USER_PAR, true);
    this.selectedDefaultValueForUsers = loadSelectedUserNameValue(
        SELECTED_DEFAULT_USERS_PAR, false);

    //Select project for action
    this.allProjects = loadProjects(this.selectedProjectKey);
    //CF groups
    this.selectCFGroups = loadGroups(this.selectedCFGroupForGroups);
    this.currentProjectGroups = buildString(
        this.usersCFExtension.getGroupsForProject(this.selectedProjectKey), DELIMITER);
    //CF users
    this.currentProjectUsersGroup = this.usersCFExtension
        .getUsersGroupForProject(this.selectedProjectKey);
    this.selectCFUsersGroups = loadGroups(this.currentProjectUsersGroup);
    //Default value for single group field
    this.currentDefaultValueForSingleGroup = this.usersCFExtension
        .getDefaultGroupForSingleGroupField(this.selectedProjectKey);
    this.selectDefaultSingleUsersGroups = loadGroups(this.currentDefaultValueForSingleGroup);
    //Default value for groups field
    this.currentDefaultValueForGroups = buildString(
        this.usersCFExtension.getDefaultGroupsForGroupsField(this.selectedProjectKey), DELIMITER);
    this.selectDefaultValueForGroups = loadGroups(this.selectedDefaultValueForGroups);
    //Default value for single user field
    this.currentDefaultValueForSingleUser = this.usersCFExtension
        .getDefaultUserForSingleUserField(this.selectedProjectKey);
    this.selectDefaultSingleUsers = loadUsers(this.currentDefaultValueForSingleUser);
    //Default value for users field
    this.currentDefaultValueForUsers = buildString(
        this.usersCFExtension.getDefaultUsersForUsersField(this.selectedProjectKey), DELIMITER);
    this.selectDefaultValueForUsers = loadUsers(this.selectedDefaultValueForUsers);

    //Current action by buttons
    this.currentAction = getCurrentAction();
  }

  private List<SelectItemDto> loadApiTypes() {
    return Arrays.stream(ApiType.values()).map(
        apiType -> new SelectItemDto(apiType.name(), apiType.getCaption(),
            apiType.name().equals(this.apiType))).collect(Collectors.toList());
  }

  private String loadSelectedProjectKeyValue(String parameterName) {
    String result = tryGetRequestParameter(parameterName);
    if (isEmpty(result)) {
      result = this.projectManager.getProjectObjects().stream().findFirst()
          .orElseThrow(() -> new RuntimeException("Projects is not found.")).getKey();
    }
    return result;
  }

  private String loadSelectedGroupNameValue(String parameterName, boolean allowEmpty) {
    String result = tryGetRequestParameter(parameterName);
    if ((allowEmpty && result == null) || (!allowEmpty && isEmpty(result))) {
      result = findAllGroups().stream().map(Group::getName).findFirst().orElse(null);
    }
    return result;
  }

  private List<SelectItemDto> loadProjects(String selectedProjectKey) {
    return this.projectManager.getProjectObjects().stream().map(
        project -> new SelectItemDto(project.getKey(),
            project.getName() + " (" + project.getKey() + ")",
            project.getKey().equals(selectedProjectKey)))
        .collect(Collectors.toList());
  }

  private List<SelectItemDto> loadGroups(String selectedGroup) {
    return findAllGroups().stream()
        .map(group -> new SelectItemDto(group.getName(), group.getName(),
            group.getName().equals(selectedGroup))).collect(Collectors.toList());
  }

  private List<Group> findAllGroups() {
    return Collections.unmodifiableList(new ArrayList<>(
        ComponentAccessor.getComponent(GroupPickerSearchService.class).findGroups("")));
  }

  private String loadSelectedUserNameValue(String parameterName, boolean allowEmpty) {
    String result = tryGetRequestParameter(parameterName);
    if ((allowEmpty && result == null) || (!allowEmpty && isEmpty(result))) {
      result = findAllUsers().stream().map(ApplicationUser::getName).findFirst().orElse(null);
    }
    return result;
  }

  private List<SelectItemDto> loadUsers(String selectedUser) {
    return findAllUsers().stream()
        .map(user -> new SelectItemDto(user.getName(), user.getName(),
            user.getName().equals(selectedUser))).collect(Collectors.toList());
  }

  public static List<ApplicationUser> findAllUsers() {
    return Collections.unmodifiableList(new ArrayList<>(ComponentAccessor.getUserSearchService()
        .findUsers("", new UserSearchParams(true, true, false))));
  }

  private String getCurrentApiType() {
    String apiType = this.getHttpRequest().getParameter(API_TYPE_PAR);
    if (isEmpty(apiType)) {
      return ApiType.JAVA.name();
    }
    return apiType;
  }

  private Action getCurrentAction() {
    String actionString = this.getHttpRequest().getParameter(ACTION_PAR);
    if (isEmpty(actionString)) {
      return null;
    }
    return Action.valueOf(actionString);
  }

  private String tryGetRequestParameter(String name) {
    String parameter = this.getHttpRequest().getParameter(name);
    return !isEmpty(name) && !NULL_VAL.equals(name) ? parameter : EMPTY_STRING;
  }

  private boolean isEmpty(String value) {
    return value == null || value.equals(EMPTY_STRING);
  }

  public String buildString(Collection<?> values, String delimiter) {
    StringJoiner stringJoiner = new StringJoiner(delimiter);
    for (Object value : values) {
      if (value == null) {
        continue;
      }
      stringJoiner.add(value.toString());
    }
    return stringJoiner.toString();
  }
}