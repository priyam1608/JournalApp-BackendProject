package net.sharma.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "frequently_used_data")
@Data
@NoArgsConstructor
public class FrequentlyUsedData {

    @Id
    private ObjectId objectId;
    private String key;
    private String value;
}
