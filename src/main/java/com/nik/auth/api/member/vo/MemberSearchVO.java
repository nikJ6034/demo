package com.nik.auth.api.member.vo;

import com.nik.auth.common.vo.SearchVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class MemberSearchVO extends SearchVO{

    private Long roleId;

}
