package com.transistorwebservices.libraryapi.user;

import com.transistorwebservices.libraryapi.book.UserBookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserBookEntityRepository extends CrudRepository<UserBookEntity, Integer> {
    List<UserBookEntity> findByUserIdAndBookId(int userId, int bookId);
}
