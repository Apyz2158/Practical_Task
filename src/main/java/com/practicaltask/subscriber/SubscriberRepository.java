package com.practicaltask.subscriber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    @Query(value = "SELECT  * FROM subscriber WHERE mobile = ?1", nativeQuery = true)
    Subscriber findByMobile(String mobile);

    @Query(value = "SELECT  * FROM subscriber WHERE email = ?1", nativeQuery = true)
    Subscriber findByEmail(String email);

    @Query(value = "SELECT  * FROM subscriber WHERE id = ?1", nativeQuery = true)
    Subscriber findByID(Long id);

}
