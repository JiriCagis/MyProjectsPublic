package task;

import model.Loan;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.ZonkyMarketService;

import java.util.Date;
import java.util.List;

/**
 * Created by Jiří Cága 7/9/2018.
 */
@Component
public class ZonkyCheckerTaskImpl implements ZonkyCheckerTask {

    private final static Logger LOGGER = Logger.getLogger(ZonkyCheckerTaskImpl.class);

    @Autowired
    private ZonkyMarketService zonkyMarketService;

    @Value("${checkInterval}")
    private Long checkInterval = 0L;

    private boolean firstCallMethodCheckNewLoans = true;



    public ZonkyCheckerTaskImpl(){

    }

    @Scheduled(fixedRateString= "${checkInterval}")
    public void checkNewLoans(){
        if(firstCallMethodCheckNewLoans){
            LOGGER.info("----------------------------------------------------");
            LOGGER.info("Zonky checker app");
            LOGGER.info("----------------------------------------------------");
            LOGGER.info("App periodically check new loans on market each " + getCheckInterval()/60/1000 + " minute(s).");
            LOGGER.info("Note: For terminate app press CTRL+C");
            firstCallMethodCheckNewLoans = false;
        }

        List<Loan> newLoans = zonkyMarketService.getPublishedLoansAfter(new Date(System.currentTimeMillis() - checkInterval));
        if(!newLoans.isEmpty()){
            displayLoans(newLoans);
        } else {
            LOGGER.info("On the market not come new loans.");
        }
    }

    public void displayLoans(List<Loan> loans){
        LOGGER.info("On the market was add this new loan(s):");
        for(Loan loan:loans){
            LOGGER.info(loan);
        }
    }

    // Getters and setters
    public Long getCheckInterval() {
        return checkInterval;
    }

    public void setCheckInterval(Long checkInterval) {
        this.checkInterval = checkInterval;
    }

    public ZonkyMarketService getZonkyMarketService() {
        return zonkyMarketService;
    }

    public void setZonkyMarketService(ZonkyMarketService zonkyMarketService) {
        this.zonkyMarketService = zonkyMarketService;
    }
}
