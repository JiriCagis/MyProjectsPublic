package service;

import model.Loan;

import java.util.Date;
import java.util.List;

/**
 * Service get published loans from Zonky REST API.
 * Note: Zonky apiary is here: https://zonky.docs.apiary.io/#
 *
 * Created by Jiří Cága
 */
public interface ZonkyMarketService {

    /**
     * Method get all published loans from Zonky server.
     * @return published loans
     */
    List<Loan> getAllPublishedLoans();

    /**
     * Method get all published loans after specific time
     * @param date specific date with time
     * @return published loans
     */
    List<Loan> getPublishedLoansAfter(Date date);

}
