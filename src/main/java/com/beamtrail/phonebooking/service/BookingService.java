package com.beamtrail.phonebooking.service;

import com.beamtrail.phonebooking.entity.Phone;
import com.beamtrail.phonebooking.entity.PhoneReservation;
import com.beamtrail.phonebooking.exception.EntityNotFoundException;
import com.beamtrail.phonebooking.repo.BookingRepository;
import com.beamtrail.phonebooking.exception.PhoneNotAvailableException;
import com.beamtrail.phonebooking.repo.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * Creates a {@link PhoneReservation PhoneReservation} if requested phone is available
     * @param phoneId
     * @param bookedBy
     * @return
     */
    @Transactional
    public PhoneReservation bookPhone(Long phoneId, String bookedBy) {
        Phone phone = phoneRepository.findById(phoneId).orElseThrow(() -> new EntityNotFoundException("Phone not found with id: " + phoneId));
        if (bookingRepository.findByPhoneId(phoneId).isPresent()) {
            throw new PhoneNotAvailableException("Phone is not available for booking");
        }
        PhoneReservation booking = new PhoneReservation();
        booking.setPhone(phone);
        booking.setBookedBy(bookedBy);
        booking.setBookedAt(new Date());
        return bookingRepository.save(booking);
    }

    /**
     * Deletes a {@link PhoneReservation PhoneReservation}
     * @param phoneId
     */
    @Transactional
    public void returnPhoneByPhoneId(Long phoneId) {
        phoneRepository.findById(phoneId).orElseThrow(() -> new EntityNotFoundException("Phone not found with id: " + phoneId));
        long count = bookingRepository.deleteByPhoneId(phoneId);
        if (count == 0) {
            throw new EntityNotFoundException("Phone reservation not found");
        }
    }

    /**
     * Reads all a {@link PhoneReservation reservations}
     * @return
     */
    public List<PhoneReservation> listAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Deletes a {@link PhoneReservation PhoneReservation}
     * @param reservationId
     */
    @Transactional
    public void returnPhone(Long reservationId) {
        bookingRepository.findById(reservationId).orElseThrow(() -> new EntityNotFoundException("Reservation not found with id:" + reservationId));
        bookingRepository.deleteById(reservationId);
    }
}
