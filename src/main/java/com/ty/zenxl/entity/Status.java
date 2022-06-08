package com.ty.zenxl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defines the Status table created in the database with the mentioned fields.
 * 
 * statusName and statusCategory are defined to be used as composite key Status Table
 * 
 * @author Indrajit
 * @version 1.0
 *
 */

@Table
@Getter
@Setter
@Entity
@Builder
@IdClass(StatusPKID.class)
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @Column(name="status_name", length=45)
    private String statusName;

    @Id
    @Column(name="status_category", length=45)
    private String statusCategory;
    
    @Column(length=145)
    private String description;
    
    @Column(name="is_active", length=1)
    private boolean isActive;
    
}
