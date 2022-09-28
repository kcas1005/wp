package com.example.donecode_v2.entity.customDto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CustomDtoExample {

    @Id
    private String input_id;
    private String input_writer;
    private String input_title;
}
