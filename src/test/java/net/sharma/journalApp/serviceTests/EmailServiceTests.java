package net.sharma.journalApp.serviceTests;

import net.sharma.journalApp.service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    EmailService emailService;

    @Disabled
    @Test
    public void sendEmailTest(){
        emailService.sendEmail("sharmacoding16@gmail.com","TESTING MAIL","Hello, kese hai aap?");
    }
}
