package com.saltsoftware.entity.bookingSchedule;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by :Craig Carr AKA Fish84
 * Student no: 204111307
 * Desc: Create BookingSchedule Builder
 */

@Entity
public class BookingSchedule{

    @Id
    private String bookingID;
    private String bookingDate, bookingTime;

    //default constructor
    protected BookingSchedule(){}

    //constructor
    private BookingSchedule(Builder builder) {
        this.bookingID = builder.bookingID;
        this.bookingDate = builder.bookingDate;
        this.bookingTime = builder.bookingTime;
    }

    //getters
    public String getBookingID() {
        return bookingID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBookingTime(){
        return bookingTime;
    }

    //toString
    @Override
    public String toString() {
        return "BookingSchedule{" +
                "bookingID=" + bookingID + '\'' +
                ", bookingDate=" + bookingDate + '\'' +
                ", bookingTime=" + bookingTime +
                '}';
    }


    //inner builder class
    public static class Builder {
        private String bookingID, bookingDate, bookingTime;

        //setters
        public Builder setBookingID(String bookingID) {
            this.bookingID = bookingID;
            return this;
        }

        public Builder setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public Builder setBookingTime(String bookingTime) {
            this.bookingTime = bookingTime;
            return this;
        }


            //copy method
            public Builder copy (BookingSchedule bookingSchedule){
                this.bookingID = bookingSchedule.bookingID;
                this.bookingDate = bookingSchedule.bookingDate;
                this.bookingTime = bookingSchedule.bookingTime;
                return this;
            }

            //builder method
            public BookingSchedule build () {

                return new BookingSchedule(this);
            }
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingSchedule that = (BookingSchedule) o;
        return bookingID.equals(that.bookingID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingID);
    }
}
