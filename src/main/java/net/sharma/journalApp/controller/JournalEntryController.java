package net.sharma.journalApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sharma.journalApp.dto.JournalEntryDTO;
import net.sharma.journalApp.entity.JournalEntry;
import net.sharma.journalApp.entity.User;
import net.sharma.journalApp.service.JournalEntryService;
import net.sharma.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("journal")
@Tag(name = "Journal APIs")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;

    @GetMapping
    @Operation(summary = "get all journal entries of a user")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{journalId}")
    @Operation(summary = "get a journal entry of a user using Journal ID")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable String journalId){
        ObjectId id = new ObjectId(journalId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!journalEntries.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getJournalById(id);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "create a journal entry")
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntryDTO journalEntryDTO){
        JournalEntry journalEntry = JournalEntry.builder()
                .title(journalEntryDTO.getTitle())
                .content(journalEntryDTO.getContent())
                .sentiment(journalEntryDTO.getSentiment())
                .build();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            journalEntryService.saveEntry(journalEntry,username);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id/{journalId}")
    @Operation(summary = "update a journal entry using journal ID")
    public ResponseEntity<?> updateJournalById(@PathVariable String journalId, @RequestBody JournalEntryDTO journalEntryDTO){
        ObjectId id = new ObjectId(journalId);

        JournalEntry newEntry = JournalEntry.builder()
                .title(journalEntryDTO.getTitle())
                .content(journalEntryDTO.getContent())
                .sentiment(journalEntryDTO.getSentiment())
                .build();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!journalEntries.isEmpty()){
            JournalEntry old = journalEntryService.getJournalById(id).orElse(null);
            if(old!=null){
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                old.setSentiment(newEntry.getSentiment() != null ? newEntry.getSentiment() : old.getSentiment());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{journalId}")
    @Operation(summary = "delete a journal entry using journal ID")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String journalId){
        ObjectId id = new ObjectId(journalId);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!journalEntries.isEmpty()){
            boolean removed = journalEntryService.deleteJournalById(id, username);
            if(removed){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
