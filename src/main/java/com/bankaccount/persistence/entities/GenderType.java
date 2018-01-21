package com.bankaccount.persistence.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="ref_gender_type")
@Data
@NoArgsConstructor
public class GenderType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String type;
	private String code;
	private String description;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	public GenderType(String type, String code, String description) {
		super();
		this.type = type;
		this.code = code;
		this.description=description;
	}
	
	
}
