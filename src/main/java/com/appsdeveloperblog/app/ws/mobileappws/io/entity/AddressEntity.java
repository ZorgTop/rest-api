package com.appsdeveloperblog.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "addresses")
@NoArgsConstructor
@Getter
@Setter
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = -1005994289577099242L;

    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String addressId;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 120)
    private String country;

    @Column(nullable = false, length = 7)
    private String postalCode;

    @Column(nullable = false, length = 120)
    private String streetName;

    @Column(nullable = false, length = 30)
    private String type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private UserEntity userDetails;
}
