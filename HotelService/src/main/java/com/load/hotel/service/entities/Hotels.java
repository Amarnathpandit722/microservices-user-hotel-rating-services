package com.load.hotel.service.entities;



import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Hotels")
@Entity
public class Hotels {

    @Id
    private String Id;
    private String hotelName;
    private String hotelLocation;
    private String hotelDescription;
    
}
