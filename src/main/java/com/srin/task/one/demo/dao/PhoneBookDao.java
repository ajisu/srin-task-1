package com.srin.task.one.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.srin.task.one.demo.model.PhoneBook;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneBookDao extends JpaRepository<PhoneBook, String> {
    // creating data
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO srin.task.phone_book " +
            "(phone_book_id, first_name, last_name, phone_number)\n" +
            "VALUES(newId(), :firstName, :lastName, :phoneNumber)", nativeQuery = true)
    void createPhoneBook(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("phoneNumber") int phoneNumber);

    // retrieving single data
    List<PhoneBook> findByFirstNameEquals(@Param("firstName") String firstName);
    Optional<PhoneBook> findByPhoneNumberEquals(@Param("phoneNumber") int phoneNumber);

    // retrieving all data
    @Query(value = "SELECT * FROM srin.task.phone_book pb.status = 1", nativeQuery = true)
    Collection<PhoneBook> getAllRecords();

    // updating data
    @Transactional
    @Modifying
    @Query(value = "UPDATE srin.task.phone_book pb SET pb.phone_number = :phoneNumber WHERE pb.firstName = :firstName AND pb.lastName = :lastName")
    void updatePhone(@Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName, @Param(value = "phoneNumber") int phoneNumber);

    // removing data
    void removeByPhoneNumber(int phoneNumber);
    void removeByFirstName(String firstName);
}
