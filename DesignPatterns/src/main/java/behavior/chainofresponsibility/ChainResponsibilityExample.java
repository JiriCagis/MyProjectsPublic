package behavior.chainofresponsibility;

/**
 * Created by osboxes on 08/01/18.
 */
public class ChainResponsibilityExample {

    public static void main(String[] args){
        LoginHandler loginHandler = new LoginHandler();
        PerformHandler performHandler = new PerformHandler();

        loginHandler.setNext(performHandler);

        Request request = new Request();
        request.setUrl("www.example.com/products");
        request.setUsername("username");
        request.setPassword("1234");

        loginHandler.handle(request);

        System.out.println(request.getResponseBody());
    }
}
