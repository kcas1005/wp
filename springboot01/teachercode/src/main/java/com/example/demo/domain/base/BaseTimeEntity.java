package com.example.demo.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.util.Date;


@Setter
@Getter
@MappedSuperclass
public class BaseTimeEntity {

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date updateDate;
}
