package com.transistorwebservices.libraryapi.publisher;

import com.transistorwebservices.libraryapi.exception.LibraryResourceAlreadyExistException;
import com.transistorwebservices.libraryapi.exception.LibraryResourceNotFoundException;
import com.transistorwebservices.libraryapi.exception.LibraryResourceUnauthorizedException;
import com.transistorwebservices.libraryapi.util.LibraryApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/publishers")
public class PublisherController {

    private static Logger logger = LoggerFactory.getLogger(PublisherController.class);

    private PublisherService publisherService;
    private Integer publisherId;
    private String traceId;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(path = "{publisherId}")
    public ResponseEntity<?> getPublisher(@PathVariable Integer publisherId,
                                          @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {
        this.publisherId = publisherId;
        this.traceId = traceId;
        if (!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        return new ResponseEntity<>(publisherService.getPublisher(publisherId, traceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPublisher(@Valid @RequestBody Publisher publisher,
                                          @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId,
                                          @RequestHeader(value = "Authorization") String bearerToken)
            throws LibraryResourceAlreadyExistException, LibraryResourceUnauthorizedException {
        logger.debug("Request to add publisher: {}", publisher);

        if (!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        if (!LibraryApiUtils.isUserAdmin(bearerToken)) {
            logger.error(LibraryApiUtils.getUserIdFromClaim(bearerToken) + " Adding user not allowed as user is not an admin");
            throw new LibraryResourceUnauthorizedException(traceId, "User not allowed to add publisher");
        }
        logger.debug("Added trace id: {}", traceId);
        publisherService.addPublisher(publisher, traceId);
        logger.debug("Returning response from tarce id: {}", traceId);

        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<?> updatePublisher(@PathVariable Integer publisherId,
                                             @Valid @RequestBody Publisher publisher,
                                             @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId,
                                             @RequestHeader(value = "Authorization") String bearerToken)
            throws LibraryResourceNotFoundException, LibraryResourceUnauthorizedException {
        if (!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        if(!LibraryApiUtils.isUserAdmin(bearerToken)){
logger.error(LibraryApiUtils.getUserIdFromClaim(bearerToken)+ "Not allowed to update since user is not an admin");
throw new LibraryResourceUnauthorizedException(traceId, " User not allowed to update a publisher");
        }
        logger.debug("Added traceId: {}" , traceId);

        publisher.setPublisherId(publisherId);
        publisherService.updatePublisher(publisher, traceId);
    logger.debug("Returning Response for traceId: {} " ,traceId);
return new ResponseEntity<>(publisher, HttpStatus.OK);
    }
}
