package behavior.chainofresponsibility;

/**
 * Created by osboxes on 08/01/18.
 */
public abstract class AbstractWebHandler implements Handler {

    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    protected void handleByNext(Request request){
        if (this.next != null){
            this.next.handle(request);
        }
    }
}
