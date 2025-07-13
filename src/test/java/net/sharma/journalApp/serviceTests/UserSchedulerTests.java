package net.sharma.journalApp.serviceTests;

import net.sharma.journalApp.schedulers.UserScheduler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTests {

    @Autowired
    UserScheduler userScheduler;

    @Disabled
    @Test
    public void getUsersAndSendEmailTests(){
        userScheduler.getUsersAndSendEmail();
    }
}
