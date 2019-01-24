package task;

/**
 * Task periodically check new loans on the market and it show loans into terminal.
 *
 * Created by Jiří Cága 7/9/2018.
 */
public interface ZonkyCheckerTask {

    /**
     * Method check new loans and print its into terminal.
     */
    void checkNewLoans();
}
