package com.beamtrail.phonebooking;

import com.beamtrail.phonebooking.dto.PhoneAvailability;
import com.beamtrail.phonebooking.entity.PhoneReservation;
import com.beamtrail.phonebooking.exception.EntityNotFoundException;
import com.beamtrail.phonebooking.service.BookingService;
import com.beamtrail.phonebooking.service.PhoneService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PhoneBookingApplicationTests {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PhoneService phoneService;

    @Test
    void testSuccessPath() {
        PhoneReservation me = bookingService.bookPhone(1L, "me");
        assertThat(me.getBookedBy()).isEqualTo("me");
        assertThat(me.getPhone().getId()).isEqualTo(1L);
        assertThat(me.getBookedAt()).isNotNull();
        assertThat(bookingService.listAllBookings()).hasSize(1);
        bookingService.returnPhoneByPhoneId(1L);
        assertThat(bookingService.listAllBookings()).isEmpty();
    }

    @Test
    void testAvailability(){
        List<PhoneAvailability> phoneAvailability = phoneService.listPhoneAvailability();
        assertThat(phoneAvailability).hasSize(10);
        assertThat(phoneAvailability.stream().allMatch(PhoneAvailability::isAvailable)).isTrue();
        PhoneReservation booking = bookingService.bookPhone(1L, "me");
        phoneAvailability = phoneService.listPhoneAvailability();
        List<PhoneAvailability> notAvailable = phoneAvailability.stream().filter(t -> !t.isAvailable()).collect(Collectors.toList());
        assertThat(notAvailable).hasSize(1);
        assertThat(notAvailable.get(0).getBookedBy()).isEqualTo("me");
        assertThat(notAvailable.get(0).getPhoneId()).isEqualTo(1L);
        bookingService.returnPhone(booking.getId());
        assertThat(phoneService.listPhoneAvailability().stream().allMatch(PhoneAvailability::isAvailable)).isTrue();

    }

    @Test
    void testPhoneNotFoundException() {
        assertThatThrownBy(() -> bookingService.bookPhone(11L, "me")).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void testReservationNotFoundException() {
        assertThatThrownBy(() -> bookingService.returnPhoneByPhoneId(11L)).isInstanceOf(EntityNotFoundException.class);
        assertThatThrownBy(() -> bookingService.returnPhone(11L)).isInstanceOf(EntityNotFoundException.class);
    }
}
