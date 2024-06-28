package com.beamtrail.phonebooking.web;

import com.beamtrail.phonebooking.entity.PhoneReservation;
import com.beamtrail.phonebooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Reads all bookings from the DB
     * @return
     */
    @GetMapping
    public List<PhoneReservation> listAllBookings(){
        return bookingService.listAllBookings();
    }

    /**
     * Books a phone by creating a new reservation in the DB
     * @param phoneId
     * @param bookedBy
     * @return
     */
    @PostMapping
    public PhoneReservation bookPhone(@RequestParam Long phoneId, @RequestParam String bookedBy) {
        return bookingService.bookPhone(phoneId, bookedBy);
    }

    /**
     * Deletes a booking of the phone by phone id
     * @param phoneId
     */
    @DeleteMapping("/by-phone-id/{phoneId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void returnPhoneByPhoneId(@PathVariable Long phoneId) {
        bookingService.returnPhoneByPhoneId(phoneId);
    }

    /**
     * Deletes a booking of the phone by reservation id
     * @param reservationId
     */
    @DeleteMapping("/{reservationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void returnPhone(@PathVariable Long reservationId) {
        bookingService.returnPhone(reservationId);
    }
}
