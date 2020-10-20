jQuery['postJSON'] = function( url, data, callback ) {
  // shift arguments if data argument was omitted
  if ( jQuery.isFunction( data ) ) {
      callback = data;
      data = undefined;
  }

  return jQuery.ajax({
    url: url,
    type: 'POST',
    contentType:'application/json; charset=utf-8',
    dataType: 'json',
    data: data,
    success: callback,
    error: function (jqXHR, textStatus, errorThrown) {
      if(jqXHR.status&&jqXHR.status == 400){
       AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
       });
      } else if(jqXHR.status&&jqXHR.status == 409) {
       AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
       });
      } else if(jqXHR.status&&jqXHR.status == 500) {
        AJS.flag({
        type: 'error',
        body: jqXHR.responseText,
        });
      } else {
       AJS.flag({
         type: 'error',
         body: textStatus + ':' + errorThrown,
       });
      };
    }});
};

jQuery['post2'] = function( url, callback ) {
  return jQuery.ajax({
    url: url,
    type: 'POST',
    contentType:'application/json; charset=utf-8',
    dataType: 'json',
    data: undefined,
    success: callback,
    error: function (jqXHR, textStatus, errorThrown) {
      if(jqXHR.status&&jqXHR.status == 400){
       AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
       });
      } else if(jqXHR.status&&jqXHR.status == 409) {
       AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
       });
      } else if(jqXHR.status&&jqXHR.status == 500) {
        AJS.flag({
          type: 'error',
          body: jqXHR.responseText,
        });
      } else {
       AJS.flag({
         type: 'error',
         body: textStatus + ':' + errorThrown,
       });
      };
    }});
};

jQuery['put'] = function( url, callback ) {

    return jQuery.ajax({
      url: url,
      type: 'PUT',
      success: callback,
      error: function (jqXHR, textStatus, errorThrown) {
        if(jqXHR.status&&jqXHR.status == 400){
         AJS.flag({
           type: 'error',
           body: jqXHR.responseText,
         });
        } else if(jqXHR.status&&jqXHR.status == 409) {
         AJS.flag({
           type: 'error',
           body: jqXHR.responseText,
         });
        } else if(jqXHR.status&&jqXHR.status == 500) {
          AJS.flag({
          type: 'error',
          body: jqXHR.responseText,
          });
        } else {
         AJS.flag({
           type: 'error',
           body: textStatus + ':' + errorThrown,
         });
        };
    }});
};

jQuery['del'] = function( url, callback ) {

  return jQuery.ajax({
    url: url,
    type: 'DELETE',
    success: callback,
    error: function (jqXHR, textStatus, errorThrown) {
      if(jqXHR.status&&jqXHR.status == 400){
       AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
       });
      } else if(jqXHR.status&&jqXHR.status == 409) {
       AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
       });
      } else if(jqXHR.status&&jqXHR.status == 500) {
        AJS.flag({
        type: 'error',
        body: jqXHR.responseText,
        });
      } else {
       AJS.flag({
         type: 'error',
         body: textStatus + ':' + errorThrown,
       });
      };
  }});
};

function sendFormWithAjax(formId, url) {
  sendFormWithAjaxCallback(formId, url,
  function(response) {
    AJS.flag({
      type: 'success',
      body: response,
      close: "auto",
    });
  });
}

function sendFormWithAjaxCallback(formId, url, callback) {
  AJS.$.ajax({type:'POST', url: url,
    data:AJS.$('#' + formId).serialize(),
    success: callback,
    error: function (jqXHR, textStatus, errorThrown) {
      if(jqXHR.status&&jqXHR.status == 400){
       AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
       });
      } else if(jqXHR.status&&jqXHR.status == 409) {
       AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
       })
      } else if(jqXHR.status&&jqXHR.status == 500) {
        AJS.flag({
         type: 'error',
         body: jqXHR.responseText,
        });
      } else {
       AJS.flag({
         type: 'error',
         body: textStatus + ':' + errorThrown,
       });
      };
    }});
}