package net.engineeringdigest.journalApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
@NoArgsConstructor
@Data
@Document(collection = "journalEntries")
public class journalEntry {



    @Id
    private ObjectId id;
    private LocalDateTime date;
    private String name;
    private String content;
}
