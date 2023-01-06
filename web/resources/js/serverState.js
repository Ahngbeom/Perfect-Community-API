const responseArea = $("#api-response-area");

serverState();

function serverState(writeResponseBody) {
    let result = null;
    $.ajax({
        type: "GET",
        url: "/api",
        async: false,
        contentType: "application/json",
        success: function (data) {
            result = data;
            console.log("Server State", result);
            if (writeResponseBody) {
                responseArea.append("<li>server_name: " + data.server_name + "</li>");
                responseArea.append("<li>server_port: " + data.server_port + "</li>");
                responseArea.append("<li>locale: " + data.locale + "</li>");
                responseArea.append("<li>character_encoding: " + data.character_encoding + "</li>");
                responseArea.append("<li>content_type: " + data.content_type + "</li>");
                responseArea.append("<li>request_uri: " + data.request_uri + "</li>");
                responseArea.append("<li>request_method: " + data.method + "</li>");
                responseArea.append("<li>query_string: " + data.query_string + "</li>");
                responseArea.append("<li>principal: " + data.principal + "</li>");
            }
        },
        error: function (xhr) {
            console.error(xhr.responseText);
        }
    });
    return result;
}

$("#server-info-btn").on('click', () => {
    serverState(true);
});

// $(".collapse").on('click', "button", (e) => {
//     const targetOffsetTop = $($(e.target).data('anchor-target')).offset().top;
//     console.log(targetOffsetTop);
//     // location.href = $(e.target).data('anchor-target');
//     // $(window).scrollTop(0);
//     $('html, body').animate({
//         scrollTop: targetOffsetTop < 120 ? 0 : targetOffsetTop
//     }, 500);
// });