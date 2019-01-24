import model.Loan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.ZonkyMarketService;
import task.ZonkyCheckerTask;
import task.ZonkyCheckerTaskImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Jiří Cága
 */
@RunWith(MockitoJUnitRunner.class)
public class ZonkyCheckerTaskTest {

    @Mock
    private ZonkyMarketService zonkyMarketService;

    @InjectMocks
    private ZonkyCheckerTask task = Mockito.spy(new ZonkyCheckerTaskImpl());



    @Test
    public void should_callDisplayMethod_whenMarketServiceReturnSomeLoans(){
        when(zonkyMarketService.getPublishedLoansAfter(any(Date.class))).thenReturn(generateDummyLoans(20));
        task.checkNewLoans();
        verify((ZonkyCheckerTaskImpl) task,times(1)).displayLoans(anyList());
    }

    @Test
    public void should_doesNotCallDisplayMethod_whenMarketServiceReturnEmptyList(){
        when(zonkyMarketService.getPublishedLoansAfter(any(Date.class))).thenReturn(Collections.emptyList());
        task.checkNewLoans();
        verify((ZonkyCheckerTaskImpl) task,times(0)).displayLoans(anyList());
    }

    private List<Loan> generateDummyLoans(int count){
        List<Loan> loans = new ArrayList<>();

        for(int i=0;i<count;i++){
            Loan loan = new Loan();
            loan.setId((long) i);
            loan.setStory("Story"+i);
            loan.setName("Name"+i);
            loan.setAmount(BigDecimal.ONE);
            loan.setRating("AAA");
            loan.setDatePublished(new Date());
            loan.setDeadline(new Date());
            loan.setUserId("UserId" + i);
            loan.setNickName("NickName"+i);
            loans.add(loan);
        }

        return loans;
    }
}
