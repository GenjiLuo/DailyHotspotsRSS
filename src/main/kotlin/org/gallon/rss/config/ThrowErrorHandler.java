package org.gallon.rss.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by gallon on 2018/12/4.
 */

public class ThrowErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        // 返回false表示不管response的status是多少都返回没有错
        // 这里可以自己定义那些status code你认为是可以抛Error
        System.out.println("response error: " + response.getStatusText());
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // 这里面可以实现你自己遇到了Error进行合理的处理
    }

}
