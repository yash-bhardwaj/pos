package com.test.pos.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private LocalDateTime bookedDate;

    @Column
    private String bookedBy;

    @Column
    private Boolean available;

    @Column
    private String technology;

    @Column(length = Integer.MAX_VALUE)
    private String bands;

}
