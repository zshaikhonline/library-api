package com.transistorwebservices.libraryapi.user;

import com.transistorwebservices.libraryapi.book.BookRepository;
import com.transistorwebservices.libraryapi.book.BookService;
import com.transistorwebservices.libraryapi.book.BookStatusRepository;
import com.transistorwebservices.libraryapi.exception.LibraryResourceAlreadyExistException;
import com.transistorwebservices.libraryapi.exception.LibraryResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: coffee@2am
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private BookRepository bookRepository;
    private BookStatusRepository bookStatusRepository;
    private BookService bookService;
    private UserBookEntityRepository userBookEntityRepository;

    @Value("3")
    private int maxNumberOfTimesIssue;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       BookRepository bookRepository, BookStatusRepository bookStatusRepository,
                       BookService bookService, UserBookEntityRepository userBookEntityRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.bookRepository = bookRepository;
        this.bookStatusRepository = bookStatusRepository;
        this.bookService = bookService;
        this.userBookEntityRepository = userBookEntityRepository;
    }

    public void addUser(User userToBeAdded, String traceId) throws LibraryResourceAlreadyExistException {
        logger.debug("TraceID: {}, Request to add user: {} ", traceId, userToBeAdded);

    }

    public User getUserByUserName(String userName) throws LibraryResourceNotFoundException {

        UserEntity userEntity = userRepository.findByUserName(userName);
        if (userEntity != null) {
            return createUserFromEntityForLogin(userEntity);
        } else {
            throw new LibraryResourceNotFoundException(null, "Library username: " + userName + " Not found");
        }
    }

    private User createUserFromEntityForLogin(UserEntity ue) {
        return new User(ue.getUserId(), ue.getUsername(), ue.getPassword(), ue.getFirstName(), ue.getLastName(),
                ue.getDateOfBirth(), ue.getGender(), ue.getPhoneNumber(), ue.getEmailId(), Role.valueOf(ue.getRole()));
    }
}
