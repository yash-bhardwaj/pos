package com.test.pos.dao;

import com.test.pos.dao.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PhoneOrderRepository extends JpaRepository<Phone, String> {
    Phone findByName(String phoneName);
}
