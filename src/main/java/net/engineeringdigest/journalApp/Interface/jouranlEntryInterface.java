package net.engineeringdigest.journalApp.Interface;

import net.engineeringdigest.journalApp.Entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface jouranlEntryInterface extends MongoRepository<journalEntry, ObjectId> {

}
