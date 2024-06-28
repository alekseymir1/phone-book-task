package com.beamtrail.phonebooking;

import com.beamtrail.phonebooking.entity.Phone;
import com.beamtrail.phonebooking.repo.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class PhoneBookingApplication implements CommandLineRunner {

    @Autowired
    private PhoneRepository phoneRepository;

    public static void main(String[] args) {
        SpringApplication.run(PhoneBookingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        phoneRepository.saveAll(Arrays.asList(
                new Phone("Samsung Galaxy S9"),
                new Phone("Samsung Galaxy S8"),
                new Phone("Samsung Galaxy S8"),
                new Phone("Motorola Nexus 6"),
                new Phone("Oneplus 9"),
                new Phone("Apple iPhone 13"),
                new Phone("Apple iPhone 12"),
                new Phone("Apple iPhone 11"),
                new Phone("iPhone X"),
                new Phone("Nokia 3310")
        ));
    }
}
