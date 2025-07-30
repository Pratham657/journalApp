//package net.engineeringdigest.journalApp.Controller;
//
//import net.engineeringdigest.journalApp.Entity.journalEntry;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journalApp")
//public class journalEntryController {
//
//   private Map<Long , journalEntry> list = new HashMap<>();
//
//    @GetMapping("/getAllJournalEntries")
//    public List<journalEntry> getAll(){
//        return new ArrayList<>(list.values());
//    }
//
//    @GetMapping("/getJournalEntries/{id}")
//    public ResponseEntity<journalEntry> findByid(@PathVariable Long id){
//
//        if (list.containsKey(id)){
//            return new ResponseEntity<>(list.get(id), HttpStatus.OK);
//        }
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping("/updateJournalEntries/{id}")
//    public ResponseEntity<journalEntry> updateByid(@PathVariable Long id , @RequestBody journalEntry entry){
//
//        if (!list.containsKey(id)){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        list.put(id , entry);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deleteJournalEntries/{id}")
//    public ResponseEntity<journalEntry> deleteByid(@PathVariable Long id ){
//
//        if (!list.containsKey(id)){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        list.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("/addJournalEntries")
//    public void createEntry(@RequestBody journalEntry entry){
////            list.put(entry.getId(), entry);
//    }
//}
