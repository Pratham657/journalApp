package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.Interface.jouranlEntryInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class journalEntryService  {

    @Autowired
    jouranlEntryInterface jei;

    @Autowired
    UserService userService;

    @Transactional
    public void saveNewData(journalEntry entry, String username) {
        entry.setDate(LocalDateTime.now());
        journalEntry savedEntry = jei.save(entry);
        User user = userService.findByUsername(username);
        user.getJournalEntriesList().add(savedEntry);
        userService.createUser(user);
    }

    public void saveData(journalEntry entry) {
//        entry.setDate(LocalDateTime.now());
//        journalEntry savedEntry = jei.save(entry);
            jei.save(entry);
//        User user = userService.findByUsername(username);
//        user.getJournalEntriesList().add(savedEntry);
//        userService.createUser(user);
    }


    public Optional<List<journalEntry>> getAll(){
        return Optional.of(jei.findAll());
    }

    public Optional<journalEntry> getOne(ObjectId id) {
        return jei.findById(id);
    }


    @Transactional
    public boolean deleteByid(String username, ObjectId id) {

        User user = userService.findByUsername(username);
        if (user == null) return false;

        List<journalEntry> list = user.getJournalEntriesList();
        boolean b =user.getJournalEntriesList().removeIf(e -> e.getId().equals(id));
        if (b) {
            userService.createUser(user);
            jei.deleteById(id);
            return true;
        }
        return false;
    }
}
