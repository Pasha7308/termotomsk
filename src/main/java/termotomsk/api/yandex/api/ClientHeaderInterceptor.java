package termotomsk.api.yandex.api;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class ClientHeaderInterceptor implements RequestInterceptor {
    private static String APY_KEY = "625e44f2-03ab-43e0-bce5-f4be4a633917";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-Yandex-API-Key", APY_KEY);
    }
}
