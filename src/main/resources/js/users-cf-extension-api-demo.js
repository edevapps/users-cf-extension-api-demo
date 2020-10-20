AJS.toInit(function(a) {

  AJS.$('#select-api-type').auiSelect2({minimumResultsForSearch: -1});
  AJS.$('#select-project').auiSelect2();
  AJS.$('#select-cf-groups').auiSelect2();
  AJS.$('#select-cf-users-groups').auiSelect2();
  AJS.$('#select-def-value-single-group').auiSelect2();
  AJS.$('#select-def-value-for-groups').auiSelect2();
  AJS.$('#select-def-value-for-single-user').auiSelect2();
  AJS.$('#select-def-value-for-users').auiSelect2();

  AJS.$('#select-api-type').change(function () {
    AJS.$('#api-type').val(
      AJS.$('#select-api-type').val());
  });

  AJS.$('#select-project').change(function () {
    AJS.$('#selected-project-key').val(
      AJS.$('#select-project').val());
  });

  AJS.$('#select-cf-groups').change(function () {
    AJS.$('#selected-cf-group-for-groups').val(
      AJS.$('#select-cf-groups').val());
  });

  AJS.$('#select-cf-users-groups').change(function () {
    AJS.$('#selected-cf-group-for-users-group').val(
      AJS.$('#select-cf-users-groups').val());
  });

  AJS.$('#select-def-value-single-group').change(function () {
    AJS.$('#selected-def-value-for-single-group').val(
      AJS.$('#select-def-value-single-group').val());
  });

  AJS.$('#select-def-value-for-groups').change(function () {
    AJS.$('#selected-def-value-for-groups').val(
      AJS.$('#select-def-value-for-groups').val());
  });

  AJS.$('#select-def-value-for-single-user').change(function () {
    AJS.$('#selected-def-value-for-single-user').val(
      AJS.$('#select-def-value-for-single-user').val());
  });

  AJS.$('#select-def-value-for-users').change(function () {
    AJS.$('#selected-def-value-for-users').val(
      AJS.$('#select-def-value-for-users').val());
  });

  //Actions
  AJS.$('#add-group-for-project-button').click(function (event) {
    window.onbeforeunload = null;
    var apiType = AJS.$('#api-type').val();
    if(apiType == 'JAVA') {
      AJS.$('#action').val('ADD_GROUP_FOR_PROJECT');
      AJS.$('#api-demo-form').submit();
    } else {
      var projectKey = AJS.$('#selected-project-key').val();
      var groupName = AJS.$('#selected-cf-group-for-groups').val();
      var apiType = AJS.$('#api-type').val();
      AJS.$.post2('/rest/userscfextension/1.0/projects/' + projectKey + '/users-groups/' + groupName,
        function (response) {
          location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
        });
    }
  });

  AJS.$('#remove-group-for-project-button').click(function (event) {
    window.onbeforeunload = null;
    var apiType = AJS.$('#api-type').val();
    if(apiType == 'JAVA') {
      AJS.$('#action').val('REMOVE_GROUP_FOR_PROJECT');
      AJS.$('#api-demo-form').submit();
    } else {
      var projectKey = AJS.$('#selected-project-key').val();
      var groupName = AJS.$('#selected-cf-group-for-groups').val();
      var apiType = AJS.$('#api-type').val();
      AJS.$.del('/rest/userscfextension/1.0/projects/' + projectKey + '/users-groups/' + groupName,
        function (response) {
          location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
        });
    }
  });

  AJS.$('#set-users-group-for-project-button').click(function (event) {
    window.onbeforeunload = null;
    var apiType = AJS.$('#api-type').val();
    if(apiType == 'JAVA') {
      AJS.$('#action').val('SET_USERS_GROUP_FOR_PROJECT');
      AJS.$('#api-demo-form').submit();
    } else {
      var projectKey = AJS.$('#selected-project-key').val();
      var groupName = AJS.$('#selected-cf-group-for-users-group').val();
      var apiType = AJS.$('#api-type').val();
      AJS.$.post2('/rest/userscfextension/1.0/projects/' + projectKey + '/users-group?name=' + groupName,
       function (response) {
         location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
       });
    }
  });

  AJS.$('#set-def-value-for-single-group-button').click(function (event) {
    window.onbeforeunload = null;
    var apiType = AJS.$('#api-type').val();
    if(apiType == 'JAVA') {
      AJS.$('#action').val('SET_DEF_VALUE_FOR_SINGLE_GROUP');
      AJS.$('#api-demo-form').submit();
    } else {
      var projectKey = AJS.$('#selected-project-key').val();
      var groupName = AJS.$('#selected-def-value-for-single-group').val();
      var apiType = AJS.$('#api-type').val();
      AJS.$.post2('/rest/userscfextension/1.0/projects/' + projectKey + '/properties/single-group-field/default-group?name=' + groupName,
       function (response) {
         location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
       });
    }
  });

  AJS.$('#add-def-value-for-groups-button').click(function (event) {
    window.onbeforeunload = null;
    var apiType = AJS.$('#api-type').val();
    if(apiType == 'JAVA') {
      AJS.$('#action').val('ADD_DEF_VALUE_FOR_GROUPS');
      AJS.$('#api-demo-form').submit();
    } else {
      var projectKey = AJS.$('#selected-project-key').val();
      var groupName = AJS.$('#selected-def-value-for-groups').val();
      var apiType = AJS.$('#api-type').val();
      AJS.$.post2('/rest/userscfextension/1.0/projects/' + projectKey + '/properties/groups-field/default-groups/' + groupName,
      function (response) {
        location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
      });
    }
  });

  AJS.$('#remove-def-value-for-groups-button').click(function (event) {
    window.onbeforeunload = null;
    var apiType = AJS.$('#api-type').val();
    if(apiType == 'JAVA') {
      AJS.$('#action').val('REMOVE_DEF_VALUE_FOR_GROUPS');
      AJS.$('#api-demo-form').submit();
    } else {
      var projectKey = AJS.$('#selected-project-key').val();
      var groupName = AJS.$('#selected-def-value-for-groups').val();
      var apiType = AJS.$('#api-type').val();
      AJS.$.del('/rest/userscfextension/1.0/projects/' + projectKey + '/properties/groups-field/default-groups/' + groupName,
       function (response) {
         location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
       });
    }
  });

  AJS.$('#set-def-value-for-single-user-button').click(function (event) {
    window.onbeforeunload = null;
    var apiType = AJS.$('#api-type').val();
    if(apiType == 'JAVA') {
      AJS.$('#action').val('SET_DEF_VALUE_FOR_SINGLE_USER');
      AJS.$('#api-demo-form').submit();
    } else {
      var projectKey = AJS.$('#selected-project-key').val();
      var userName = AJS.$('#selected-def-value-for-single-user').val();
      var apiType = AJS.$('#api-type').val();
      AJS.$.post2('/rest/userscfextension/1.0/projects/' + projectKey + '/properties/single-user-field/default-user?name=' + userName,
      function (response) {
        location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
      });
    }
  });

  AJS.$('#add-def-value-for-users-button').click(function (event) {
      window.onbeforeunload = null;
      var apiType = AJS.$('#api-type').val();
      if(apiType == 'JAVA') {
        AJS.$('#action').val('ADD_DEF_VALUE_FOR_USERS');
        AJS.$('#api-demo-form').submit();
      } else {
        var projectKey = AJS.$('#selected-project-key').val();
        var userName = AJS.$('#selected-def-value-for-users').val();
        var apiType = AJS.$('#api-type').val();
        AJS.$.post2('/rest/userscfextension/1.0/projects/' + projectKey + '/properties/users-field/default-users/' + userName,
        function (response) {
         location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
        });
      }
    });

    AJS.$('#remove-def-value-for-users-button').click(function (event) {
      window.onbeforeunload = null;
      var apiType = AJS.$('#api-type').val();
      if(apiType == 'JAVA') {
        AJS.$('#action').val('REMOVE_DEF_VALUE_FOR_USERS');
        AJS.$('#api-demo-form').submit();
      } else {
        var projectKey = AJS.$('#selected-project-key').val();
        var userName = AJS.$('#selected-def-value-for-users').val();
        var apiType = AJS.$('#api-type').val();
        AJS.$.del('/rest/userscfextension/1.0/projects/' + projectKey + '/properties/users-field/default-users/' + userName,
        function (response) {
        location.href = '/secure/UsersCFExtensionDemo!default.jspa?apiType=' + apiType;
        });
      }
    });
});