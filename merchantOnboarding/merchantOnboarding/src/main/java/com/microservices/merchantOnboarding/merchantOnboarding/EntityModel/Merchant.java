package com.microservices.merchantOnboarding.merchantOnboarding.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name= "Merchant" )
public class Merchant {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long merchantId;
   @Column(name="Name")
    private String name;
   @Column(name="UserName")
    private String username;
   @Column(name="Password")
    private String password;
   @Column(name="Address")
    private String address;
   @Column(name="State")
    private String state;
   @Column(name="Country")
    private String country;
   @Column(name="Contact-No")
    private long contactNo;
   @Column(name="Role")
   private String roles;

    

    public Merchant()
    {

    }

    public Merchant(Long merchantId, String name, String username, String password, String address, String state, String country, long contactNo,String roles ) {
        this.merchantId = merchantId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.state = state;
        this.country = country;
        this.contactNo = contactNo;
        this.roles=roles;
    }

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	

    
}
