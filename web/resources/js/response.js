const responseBodyToastContainer = $("#response-body-toast-container");
const responseBodyToast = responseBodyToastContainer.children(".toast");

$(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
    const status = jqXHR.status;
    const toastHeader = responseBodyToastContainer.find(".toast-header");

    if (status >= 200 && status < 300) {
        // toastHeader.find("i").class("<i class='fa-sharp fa-solid fa-check'></i>")
        toastHeader.removeClass("text-danger").addClass("text-success");
        toastHeader.find("strong").html("Done");
        responseBodyToastContainer.find(".toast-body").html(jqXHR.responseText);
    } else if (status >= 400 && status < 500) {
        // toastHeader.find("i").replaceWith("<i class=\"fa-sharp fa-solid fa-xmark\"></i>")
        toastHeader.removeClass("text-success").addClass("text-danger");
        toastHeader.find("strong").html("Failed");
        responseBodyToastContainer.find(".toast-body").html(jqXHR.responseText);
    }
    responseBodyToast.show();
});

responseBodyToastContainer.on('click', "button[data-bs-dismiss='toast']", () => {
   responseBodyToast.hide();
});


