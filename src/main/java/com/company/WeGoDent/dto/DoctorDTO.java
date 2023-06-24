package com.company.WeGoDent.dto;

import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.enums.DoctorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    public Long id;
    public User doctorId;
    public String experience;
    public String language;
    public Double hourlyRate;
    public DoctorType doctorType;
    public String officeLocationName;

}
