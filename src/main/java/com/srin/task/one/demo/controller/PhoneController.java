package com.srin.task.one.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srin.task.one.demo.dao.PhoneBookDao;
import com.srin.task.one.demo.model.PhoneBook;

@RestController
public class PhoneController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    private PhoneBookDao phoneBookDao;

    @GetMapping("/phonebooks")
    public List<PhoneBook> getAllRecords() {
        return phoneBookDao.findAll();
    }

    @GetMapping("/phonebooks/{id}")
    public ResponseEntity<PhoneBook> getRecordById(@PathVariable("id") Long phoneBookId) throws Exception {
        PhoneBook phoneBook = phoneBookDao.findById(phoneBookId)
            .orElseThrow(() -> new Exception("Phonebook not found for this id :: " + phoneBookId));
        return ResponseEntity.ok().body(phoneBook);
    }

    @GetMapping("/phonebooks/{firstName}")
    public ResponseEntity<List<PhoneBook>> getRecordByFirstName(@PathVariable("firstName") String firstName) throws Exception {
        List<PhoneBook> phoneBooks = phoneBookDao.findByFirstNameEquals(firstName);
        if (phoneBooks.isEmpty())
            throw new Exception("Phonebook not found for this first name :: " + firstName);
        return ResponseEntity.ok().body(phoneBooks);
    }

    @GetMapping("/phonebooks/{phoneNumber}")
    public ResponseEntity<PhoneBook> getRecordByPhoneNumber(@PathVariable("phoneNumber") Integer phoneNumber) throws Exception {
        PhoneBook phoneBook = phoneBookDao.findByPhoneNumberEquals(phoneNumber)
            .orElseThrow(() -> new Exception("Phonebook not found for this number :: " + phoneNumber));
        return ResponseEntity.ok().body(phoneBook);
    }

    @PostMapping("/phonebooks")
    public PhoneBook createRecord(@RequestBody PhoneBook phoneBook) {
        return phoneBookDao.save(phoneBook);
    }

    @PutMapping("/phonebooks/{id}")
    public ResponseEntity<PhoneBook> updateRecordById(@PathVariable("id") Long phoneBookId, 
        @RequestBody PhoneBook phoneBookDetails) throws Exception {
        PhoneBook phoneBook = phoneBookDao.findById(phoneBookId)
            .orElseThrow(() -> new Exception("PhoneBook not found for this id :: " + phoneBookId));

        phoneBook.setFirstName(phoneBookDetails.getFirstName());
        phoneBook.setLastName(phoneBookDetails.getLastName());
        phoneBook.setPhoneNumber(phoneBookDetails.getPhoneNumber());
        final PhoneBook updatedPhoneBook = phoneBookDao.save(phoneBook);

        return ResponseEntity.ok(updatedPhoneBook);
    }

    @PutMapping("/phonebooks/{phoneNumber}")
    public ResponseEntity<PhoneBook> updateRecordByPhoneNumber(@PathVariable("phoneNumber") Integer phoneNumber,
        @RequestBody PhoneBook phoneBookDetails) throws Exception {
        PhoneBook phoneBook = phoneBookDao.findByPhoneNumberEquals(phoneNumber)
            .orElseThrow(() -> new Exception("PhoneBook not found for this number :: " + phoneNumber));

        phoneBook.setFirstName(phoneBookDetails.getFirstName());
        phoneBook.setLastName(phoneBookDetails.getLastName());
        phoneBook.setPhoneNumber(phoneBookDetails.getPhoneNumber());
        final PhoneBook updatedPhoneBook = phoneBookDao.save(phoneBook);

        return ResponseEntity.ok(updatedPhoneBook);
    }

    @DeleteMapping("/phonebooks/{id}")
    public Map<String, Object> deleteRecordById(@PathVariable("id") Long phoneBookId) throws Exception {
        PhoneBook phoneBook = phoneBookDao.findById(phoneBookId)
            .orElseThrow(() -> new Exception("PhoneBook not found for this id :: " + phoneBookId));

        phoneBookDao.delete(phoneBook);
        Map<String, Object> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        response.put("recordDeleted", phoneBook);
        
        return response;
    }

    @DeleteMapping("/phonebooks/{phoneNumber}")
    public Map<String, Object> deleteRecordByPhoneNumber(@PathVariable("phoneNumber") Integer phoneNumber) throws Exception {
        PhoneBook phoneBook = phoneBookDao.findByPhoneNumberEquals(phoneNumber)
            .orElseThrow(() -> new Exception("PhoneBook not found for this number :: " + phoneNumber));

        phoneBookDao.delete(phoneBook);
        Map<String, Object> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        response.put("recordDeleted", phoneBook);

        return response;
    }
}