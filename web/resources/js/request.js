const savedRequestSessionKey = "SavedRequest";
let savedRequest = sessionStorage.getItem(savedRequestSessionKey);

class SavedRequest {
    constructor(method, url, data) {
        this.method = method;
        this.url = url;
        this.data = data;
    }
}

if (savedRequest !== null) {
    console.log(savedRequest);
    $.ajax({
        type: requestBodyJson.method,
        url: requestBodyJson.url,
        contentType: 'application/json',
        dataType: 'json',
        data: savedRequest
    }).always(() => sessionStorage.removeItem(savedRequestSessionKey));
}
