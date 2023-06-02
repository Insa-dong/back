package com.insadong.application.emporg.dto;

import com.insadong.application.common.entity.Auth;
import lombok.Data;

@Data
public class EmpAuthDTO {

    private EmpAuthPKDTO empAuthPK;

    private Auth auth;


}
