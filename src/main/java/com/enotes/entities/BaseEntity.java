package com.enotes.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    private Boolean isActive;
    private Boolean isDeleted;
    private Integer createdBy;
    private Date createdDate;
    private Integer updatedBy;
    private Date updatedOn;
}
