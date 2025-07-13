package net.sharma.journalApp.repository;

import net.sharma.journalApp.entity.FrequentlyUsedData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FrequentlyUsedDataRepository extends MongoRepository<FrequentlyUsedData, ObjectId> {

}
