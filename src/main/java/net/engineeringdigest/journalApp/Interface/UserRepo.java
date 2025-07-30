package net.engineeringdigest.journalApp.Interface;

import net.engineeringdigest.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    public User findByUsername(String username);

    void deleteByusername(String name);
}
