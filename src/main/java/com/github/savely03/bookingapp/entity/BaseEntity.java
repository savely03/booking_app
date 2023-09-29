package com.github.savely03.bookingapp.entity;

public interface BaseEntity<I> {
    I getId();

    void setId(I id);
}
