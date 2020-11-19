package com.saltsoftware.service.bookingSchedule.impl;

import com.saltsoftware.entity.bookingSchedule.BookingSchedule;
import com.saltsoftware.repository.bookingSchedule.BookingScheduleRepository;
import com.saltsoftware.service.bookingSchedule.BookingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by :Craig Carr AKA Fish84
 * Student no: 204111307
 * Desc: Create BookingScheduleServiceImpl similar to repository without DB access
 */
@Service
public class BookingScheduleServiceImpl implements BookingScheduleService {


    //Encapsulate BookingSchedule Repo
    private static BookingScheduleService bookingScheduleService = null;

    //Constructor
    @Autowired
    private BookingScheduleRepository repository;

    @Override
    public Set<BookingSchedule> getAll() {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public BookingSchedule create(BookingSchedule bookingSchedule) {
        return this.repository.save(bookingSchedule);
    }

    @Override
    public BookingSchedule read(String s) {
        return this.repository.findById(s).orElseGet(null);
    }

    @Override
    public BookingSchedule update(BookingSchedule bookingSchedule) {
        if (this.repository.existsById(bookingSchedule.getBookingID())) {
            return this.repository.save(bookingSchedule);
        }
        return null;
    }

    @Override
    public boolean delete(String s) {
        this.repository.deleteById(s);
        if (this.repository.existsById(s)) return false;
        else return true;
    }
}
