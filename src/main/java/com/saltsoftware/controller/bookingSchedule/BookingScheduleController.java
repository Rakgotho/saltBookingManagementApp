package com.saltsoftware.controller.bookingSchedule;

import com.saltsoftware.entity.bookingSchedule.BookingSchedule;
import com.saltsoftware.factory.bookingSchedule.BookingScheduleFactory;
import com.saltsoftware.service.bookingSchedule.BookingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by :Craig Carr AKA Fish84
 * Student no: 204111307
 * Desc: BookingSchedule controller
 */

@RestController
@RequestMapping("/bookingSchedule")
public class BookingScheduleController {

    //injecting the constructor, calling payment type service
    @Autowired
    private BookingScheduleService bookingScheduleService;

    @PostMapping("/create")
    public BookingSchedule create(@RequestBody BookingSchedule bookingSchedule){
        //bookingSchedule = BookingScheduleFactory.createBookingSchedule(bookingSchedule.getBookingDate(), bookingSchedule.getBookingTime());
        BookingSchedule newBookingSchedule = BookingScheduleFactory.createBookingSchedule(bookingSchedule.getBookingDate(), bookingSchedule.getBookingTime());
        return bookingScheduleService.create(newBookingSchedule);
    }

    //send to server
    @GetMapping("/all")
    public Set<BookingSchedule> getAll() {
        return bookingScheduleService.getAll();
    }

    //read
    @GetMapping("/read/{id}")
    @ResponseBody
    public BookingSchedule read(@PathVariable String id){
        return bookingScheduleService.read(id);
    }

    //Update
    @PostMapping("/update")
    public BookingSchedule update(@RequestBody BookingSchedule bookingSchedule){
        return bookingScheduleService.update(bookingSchedule);
    }

    //Delete method
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable String id){
        return bookingScheduleService.delete(id);

    }
}
