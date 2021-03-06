package dev.be.moduleapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.be.moduleapi.enums.response.ReturnCode;
import dev.be.moduleapi.exception.CustomException;
import dev.be.modulecommon.domain.Member;
import dev.be.modulecommon.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemoService {

    @Value("${profile-name}")
    private String name;

    private final MemberRepository memberRepository;

    public String save() {
        System.out.println("env profile : " + name);

        Member newMember = memberRepository.save(Member.builder()
                                                       .name(Thread.currentThread().getName())
                                                       .build());
        return newMember.getName();
    }

    public String find() {

        return String.valueOf(memberRepository.findAll().size());
    }

    public String handleCustomException() {
        if (true) {
            throw new CustomException(ReturnCode.UNKNOWN_ERROR);
        }

        return "String";
    }
}
