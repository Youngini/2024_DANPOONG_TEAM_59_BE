package com.goorm.dapum.domain.auth.service;


import com.goorm.dapum.application.dto.auth.KakaoLoginRequest;
import com.goorm.dapum.application.dto.auth.KakaoLoginResponse;
import com.goorm.dapum.domain.member.dto.MemberRequest;
import com.goorm.dapum.domain.member.service.MemberService;
import com.goorm.dapum.infrastructure.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final MemberService memberService;

    public KakaoLoginResponse login(KakaoLoginRequest request) {
        if(!memberService.existsByEmail(request.email())){
            String nickname = "새가입자" + request.id();
            memberService.create(new MemberRequest(request.email(), request.id(), request.nickname(), nickname, request.profile_image_url()));
        }
        return new KakaoLoginResponse(jwtService.generateToken(request.email()));
    }
}