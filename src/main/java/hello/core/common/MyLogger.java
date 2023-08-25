package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
/**
 * 기존에 리퀘스트 스코프를 주입받고 싶은데 오류났어.
 * proxyMode = ScopedProxyMode.TARGET_CLASS 를 추가했더니
 * provider를 사용한 것과 동일하게 동작
 *
 * 어떻게?
 *
 * */
public class MyLogger {
    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    
    public void log(String message){
        System.out.println("[" + uuid +"]" + "[" + requestUrl + "]" + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid +"]" + "request scope bean create:" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid +"]" + "request scope bean close:" + this);
    }
}
