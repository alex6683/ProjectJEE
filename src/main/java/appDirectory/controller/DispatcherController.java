package appDirectory.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DispatcherController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    String now = (new Date()).toString();

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws ServletException, IOException {

        logger.info("Returning hello view with ");
        return new ModelAndView("accueil", "now", now);

    }

}