package com.load.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Hotel {
    private String Id;
    private String hotelName;
    private String hotelLocation;
    private String hotelDescription;

}
