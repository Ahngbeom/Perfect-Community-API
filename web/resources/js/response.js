const responseBodyToastContainer = $("#response-body-toast-container");
const responseBodyToast = responseBodyToastContainer.children(".toast");
const toastHeader = responseBodyToastContainer.find(".toast-header");

$(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
    const status = jqXHR.status;

    if (status >= 200 && status < 300) {
        // toastHeader.find("i").class("<i class='fa-sharp fa-solid fa-check'></i>")
        toastHeader.removeClass("text-danger").addClass("text-success");
        toastHeader.find("strong").html("Done" + " - [" + ajaxOptions.type + "]" + ajaxOptions.url);
        responseBodyToastContainer.find(".toast-body").html(jqXHR.responseText);
    } else if (status >= 400 && status < 500) {
        // toastHeader.find("i").replaceWith("<i class=\"fa-sharp fa-solid fa-xmark\"></i>")
        toastHeader.removeClass("text-success").addClass("text-danger");
        toastHeader.find("strong").html("Failed" + " - [" + ajaxOptions.type + "]" + ajaxOptions.url);
        responseBodyToastContainer.find(".toast-body").html(jqXHR.responseText);
    }
    responseBodyToast.show();
});

responseBodyToastContainer.on('click', "button[data-bs-dismiss='toast']", () => {
   responseBodyToast.hide();
});

const messageToToast = (bt_color, message) => {
    // https://gist.github.com/imcmahon/4335d05967f54bb6d14e
    toastHeader.removeClass((index, className) => {
        return (className.match(/\btext-\S+/g) || []).join(' ');
    });

    toastHeader.addClass("text-" + bt_color);
    toastHeader.find("strong").html("");
    responseBodyToastContainer.find(".toast-body").html(message);
    responseBodyToast.show();
}


