package com.company.WeGoDent.dto;

import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.enums.DoctorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    public Long id;
    public UserDTO userDTO;
    public String experience;
    public String language;
    public Double hourlyRate;
    public DoctorType doctorType;
    public String officeLocationName;
    public List<DoctorAvailabilityDTO> doctorAvailabilityDTOList;


}
