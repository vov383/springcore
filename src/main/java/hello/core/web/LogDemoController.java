package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestUrl = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        /**
         * 찍어봤더니
         * myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$a4072c0a
         * 얘는 스프링이 조작해서 만든 MyLogger
         * 가짜 myLogger를 집어넣어놓고
         * 실제 기능을 호출하는 시점에 provider처럼 진짜 MyLogger를 찾아서 동작한다.
         * */
        myLogger.setRequestUrl(requestUrl);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");

        return "OK";
    }
}
