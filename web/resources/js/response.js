const responseBodyToast = $("#response-body-toast");

$(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
    const status = jqXHR.status;
    const toastHeader = responseBodyToast.find(".toast-header");

    if (status >= 200 && status < 300) {
        // toastHeader.find("i").class("<i class='fa-sharp fa-solid fa-check'></i>")
        toastHeader.addClass("text-success").find("strong").html("Done");
        responseBodyToast.find(".toast-body").html(jqXHR.responseText);
    } else if (status >= 400 && status < 500) {
        // toastHeader.find("i").replaceWith("<i class=\"fa-sharp fa-solid fa-xmark\"></i>")
        toastHeader.addClass("text-danger").find("strong").html("Failed");
        responseBodyToast.find(".toast-body").html(jqXHR.responseText);
    }
    responseBodyToast.show();
});

