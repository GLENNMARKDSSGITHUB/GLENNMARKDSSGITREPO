package com.dss.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@RestController
public class DssErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html>" +
                        "<body>" +
                        "<h2>Error Page</h2>" +
                        "<div>Status code: <b>%s</b></div>"
                        +"<div>Exception Message: <b>%s</b></div>" +
                        "<body>" +
                        "</html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }
}
