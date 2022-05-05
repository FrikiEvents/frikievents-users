package com.frikiteam.frikievents.users.query.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.frikiteam.frikievents.users.query.projections.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserQueryController {
    private final UserViewRepository userViewRepository;
    private final UserHistoryViewRepository userHistoryViewRepository;

    public UserQueryController(UserViewRepository userViewRepository, UserHistoryViewRepository userHistoryViewRepository) {
        this.userViewRepository = userViewRepository;
        this.userHistoryViewRepository = userHistoryViewRepository;
    }

    @GetMapping("")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserView>> getAll() {
        try {
            return new ResponseEntity<List<UserView>>(userViewRepository.findAll(), HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get user by id")
    public ResponseEntity<UserView> getById(@PathVariable("id") String id) {
        try {
            Optional<UserView> userViewOptional = userViewRepository.findById(id);
            if (userViewOptional.isPresent()) {
                return new ResponseEntity<UserView>(userViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    @Operation(summary = "Get user history")
    public ResponseEntity<List<UserHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<UserHistoryView> users = userHistoryViewRepository.getHistoryByUserId(id);
            return new ResponseEntity<List<UserHistoryView>>(users, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}