package net.sharma.journalApp.schedulers;

import net.sharma.journalApp.entity.JournalEntry;
import net.sharma.journalApp.entity.User;
import net.sharma.journalApp.enums.Sentiment;
import net.sharma.journalApp.repository.UserRepositoryImpl;
import net.sharma.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepositoryImpl userRepositoryImpl;
    

    //@Scheduled(cron = "0 0 9 * * SUN")
    public void getUsersAndSendEmail(){
        List<User> users = userRepositoryImpl.getUsersForSA();

        for (User user: users){
            List<JournalEntry> journalEntries = user.getJournalEntries();

            // Here, Sentiments of past 7 days are collected of each user
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDateTime().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());

            // Then, hashmap is used to store the frequency of each sentiment
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment: sentiments){
                if(sentiment != null){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment,0) + 1);
                }
            }

            // Logic to get the most frequent sentiment of the user
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> sentimentEntry : sentimentCounts.entrySet()) {
                if(sentimentEntry.getValue() > maxCount){
                    maxCount = sentimentEntry.getValue();
                    mostFrequentSentiment = sentimentEntry.getKey();
                }
            }

            // Sending email along with that sentiment
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days",mostFrequentSentiment.toString());

        }
    }
}
