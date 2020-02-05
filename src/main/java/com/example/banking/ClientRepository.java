package com.example.banking;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ClientRepository
 */
@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}