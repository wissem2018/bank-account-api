package com.bankaccount.persistence.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
public class Client {
	@Setter(value=AccessLevel.NONE)
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Column(nullable=false)
	private String clientNumber;
	@NotNull
	@Size(max=100)
	@Column(nullable=false)
	private String firstName;
	
	@NotNull
	@Size(max=100)
	@Column(nullable=false)
	private String lastName;
	
	@NotNull
	@Size(max=100)
	private String phoneNumber;
	
	@Column(nullable=false)
	private LocalDate birthDate;
	
	@NotNull
	@Size(max=100)
	@Email
	private String email;
	@OneToOne (fetch=FetchType.EAGER)
	private GenderType gender;
	
	private String details;
	
	@ManyToOne (fetch=FetchType.EAGER)
	private Bank bank;
	
	
	@OneToOne(mappedBy="client", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private Address address;
	
	
	@Setter(value=AccessLevel.NONE)
	@OneToMany (mappedBy="client", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Account> accounts = new ArrayList<Account>();
	
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdDate;

	public Client(String clientNumber, String firstName, String lastName, String phoneNumber, LocalDate birthDate,
			String email, GenderType gender, String details) {
		super();
		this.clientNumber = clientNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.email = email;
		this.gender = gender;
		this.details = details;
	}
	
//	public void addAccount(Account account) {
//		this.accounts.add(account);
//	}
//	
//	public void removeAccount(Account account) {
//		this.accounts.remove(account);
//	}
//
//	@Override
//	public String toString() {
//		return "Client [id=" + id + ", clientNumber=" + clientNumber + ", firstName=" + firstName + ", lastName="
//				+ lastName + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + ", email=" + email
//				+ ", gender=" + gender + ", details=" + details + ", bank=" + bank + ", lastUpdatedDate="
//				+ lastUpdatedDate + ", createdDate=" + createdDate 
//				+ ", accounts = " + accounts 
//				+ ", address=" + address +
//				"]";
//	}
}
