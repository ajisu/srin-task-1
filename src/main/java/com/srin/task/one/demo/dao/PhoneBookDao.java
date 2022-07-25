package com.srin.task.one.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.srin.task.one.demo.model.PhoneBook;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneBookDao extends JpaRepository<PhoneBook, Long> {
    // retrieving single data
    List<PhoneBook> findByFirstNameEquals(@Param("firstName") String firstName);
    Optional<PhoneBook> findByPhoneNumberEquals(@Param("phoneNumber") int phoneNumber);

    // retrieving all data
    @Query(value = "SELECT * FROM srin.task_one.phone_book", nativeQuery = true)
    List<PhoneBook> getAllRecords();

    // removing data
    void removeByPhoneNumber(int phoneNumber);
    void removeByFirstName(String firstName);
}
