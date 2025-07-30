package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.Services.UserService;
import net.engineeringdigest.journalApp.Services.journalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journalApp")
public class journalEntryControllerNew {

    @Autowired
    journalEntryService jes;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(){

        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        List<journalEntry> all = user.getJournalEntriesList();

        if (all !=null && all.size() != 0) {
            return new ResponseEntity<>(all , HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<journalEntry>> findByid(@PathVariable ObjectId id){
//
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<journalEntry>list = user.getJournalEntriesList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!list.isEmpty()){
            Optional<journalEntry> entry = jes.getOne(id);
            if (entry.isPresent()){
                return new ResponseEntity<>(entry , HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> createEntry(@RequestBody journalEntry entry ){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = authentication.getName();
        if (entry==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        jes.saveNewData(entry,username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateByid(@PathVariable ObjectId id , @RequestBody journalEntry entry){

        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<journalEntry>list = user.getJournalEntriesList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!list.isEmpty()){
            Optional<journalEntry> temp = jes.getOne(id);
            if (temp.isPresent()){
                journalEntry old = temp.get();
                if (entry.getName()!=null && !entry.getName().equals(""))
                    old.setName(entry.getName());
                if (entry.getContent()!=null && !entry.getContent().equals("") )
                    old.setContent(entry.getContent());

                jes.saveData(old);
                return new ResponseEntity<>(old , HttpStatus.ACCEPTED);
            }
        }
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteByid( @PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String username = authentication.getName();

        if(jes.deleteByid(username , id)){
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
