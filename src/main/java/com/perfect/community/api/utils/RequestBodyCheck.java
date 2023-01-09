package com.perfect.community.api.utils;

import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestBodyCheck {

    public String getRequestBody(HttpServletRequest request) throws IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ServletInputStream inputStream = requestWrapper.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder requestJSON = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            requestJSON.append(line);
        }
        return String.valueOf(requestJSON);
    }
}
