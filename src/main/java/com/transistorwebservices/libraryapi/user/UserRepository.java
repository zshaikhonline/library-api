package com.transistorwebservices.libraryapi.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: coffee@2am
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByUserName(String userName);

    List<UserEntity> findByFirstNameAndLastNameContaining(String firstName, String lastName);

    List<UserEntity> findByFirstNameContaining(String firstName);

    List<UserEntity> findByLastNameContaining(String lastName);


}
