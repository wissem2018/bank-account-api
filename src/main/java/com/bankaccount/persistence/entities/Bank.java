package com.bankaccount.persistence.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @NoArgsConstructor
public class Bank {
	@Setter(value=AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String code;
	private String details;
	
	
	@OneToOne (mappedBy="bank")
	private Address address;
	
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Override
	public String toString() {
		return "Bank [name=" + name + ", code=" + code + ", details=" + details + ", lastUpdatedDate=" + lastUpdatedDate
				+ ", createdDate=" + createdDate + "]";
	}
	
	

}
