package com.scht.front.controller;

import com.scht.admin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vanjoger on 2016/12/3.
 */
@Controller
@RequestMapping("/rest/member")
public class RestMemberController {

    @Autowired
    MemberService memberService;



}
