$(document).ajaxSend((event, jqXHR, ajaxOptions) => {
    if (accessToken !== undefined && accessToken !== null && accessToken !== "")
        jqXHR.setRequestHeader("Authorization", "Bearer " + accessToken);
});

$(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
    // console.log(event);
    // console.log(jqXHR);
    // console.log(ajaxOptions);
    if (jqXHR.status >= 400) {
        console.error(jqXHR.status, jqXHR.statusText, jqXHR.responseText);
    }
});

$.ajaxSetup({
    contentType: 'application/json; chrset=utf-8',
    dataType: 'json'
});