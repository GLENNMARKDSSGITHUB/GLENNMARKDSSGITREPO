/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */


package com.dss.controller.error;

import com.dss.dto.error.ErrorDTO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * This class is a controller layer for DSS Error Message
 */

@RestController
public class DssErrorController implements ErrorController {

    /** Returns a list of ErrorDTO that contains an error message
     * @param request HttpServletRequest
     * @return a list of ErrorDTO
     * @see #handleError(HttpServletRequest)
     */

    @RequestMapping("/error")
    @ResponseBody
    public List<ErrorDTO> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return Collections.singletonList(new ErrorDTO(
                "Error Page", statusCode.toString(), exception==null? "N/A": exception.getMessage()
        ));
    }
}
