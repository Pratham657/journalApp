package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testFindByUserName(){
        assertNotNull(userService.findByUsername("ram"));
    }

    @Test
    public void testJournalEntryEmptyorNot(){
        User user = userService.findByUsername("ram");
        assertTrue(!user.getJournalEntriesList().isEmpty());
    }


    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3 , 5 ,6"
    })
    public void test(int a  , int b , int expected){
        assertEquals(expected , a+b);
    }
}
