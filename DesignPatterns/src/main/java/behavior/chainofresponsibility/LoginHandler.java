package behavior.chainofresponsibility;

/**
 * Created by osboxes on 08/01/18.
 */
public class LoginHandler extends AbstractWebHandler {

    @Override
    public void handle(Request request) {
        if(tryLogin(request)){
            request.setAuthorized(true);
        }
        handleByNext(request);
    }

    private boolean tryLogin(Request request) {
        return true;
    }
}
