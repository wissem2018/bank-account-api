package com.bankaccount.persistence.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data @NoArgsConstructor

public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(value=AccessLevel.NONE)
	private Long id;
	@Size(max=100)
	private String street;
	@Size(max=8)
	private String postalCode;
	private String city;
	@Size(max=25)
	private String country;
	private String details;
	
	@JsonIgnore
	@OneToOne
	private Client client;
	
	@OneToOne
	private Bank bank;
	
	public Address(String street, String postalCode, String city, String country, String details) {
		super();
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
		this.details = details;
		
	}
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Override
	public String toString() {
		return "Address [street=" + street + ", postalCode=" + postalCode + ", city=" + city + ", country=" + country
				+ ", details=" + details + ", lastUpdatedDate=" + lastUpdatedDate + ", createdDate=" + createdDate
				+ "]";
	}
}
