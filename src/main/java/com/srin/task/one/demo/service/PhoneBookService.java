package com.srin.task.one.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srin.task.one.demo.dao.PhoneBookDao;
import com.srin.task.one.demo.model.PhoneBook;

@Service
public class PhoneBookService {

    @Autowired
    private PhoneBookDao phoneBookDao;

    public void insertNewRecord(String firstName, String lastName, int phoneNumber) {
        phoneBookDao.createPhoneBook(firstName, lastName, phoneNumber);
    }

    public List<PhoneBook> getRecord(String firstName) {
        List<PhoneBook> result = new ArrayList<>();
        if (!phoneBookDao.findByFirstNameEquals(firstName).isEmpty())
            result = phoneBookDao.findByFirstNameEquals(firstName);
        
        return result;
    }

    public Optional<PhoneBook> getRecord(int phoneNumber) {
        return phoneBookDao.findByPhoneNumberEquals(phoneNumber);
    }

    public Collection<PhoneBook> getAllRecord() {
        return phoneBookDao.getAllRecords();
    }

    public void removeRecord(String firstName) {
        phoneBookDao.removeByFirstName(firstName);
    }

    public void removeRecord(int phoneNumber) {
        phoneBookDao.removeByPhoneNumber(phoneNumber);
    }

}