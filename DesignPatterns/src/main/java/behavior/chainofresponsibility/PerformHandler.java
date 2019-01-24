package behavior.chainofresponsibility;

/**
 * Created by osboxes on 08/01/18.
 */
public class PerformHandler extends AbstractWebHandler {
    @Override
    public void handle(Request request) {
        if(request.isAuthorized()){
            request.setResponseCode("200");
            request.setResponseBody("Content of web page");
        } else {
            request.setResponseCode("403");
            request.setResponseBody("You are not authorized to do this!");
        }
    }
}
