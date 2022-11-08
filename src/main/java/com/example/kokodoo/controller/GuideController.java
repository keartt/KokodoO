package com.example.kokodoo.controller;

import com.example.kokodoo.dto.PlanDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class GuideController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuideController.class);

    @GetMapping("/guide")
    public String guide() {
        LOGGER.info("/guide GET 진입");
        return "guide";
    }

    @GetMapping("/plan")
    public String plan(){
        LOGGER.info("/plan GET 진입");
        System.out.println("/plan GET 진입");
        return "plan";
    }

    // 만드는 알고리즘
    @PostMapping("/plan")
    public String made(@RequestParam(value="food") List<Integer> food, @RequestParam(value="place") List<Integer> place, Model model){
        LOGGER.info("/plan POST 진입");
        LOGGER.info("food {}", food);
        LOGGER.info("place {}", place);

        // 체크박스에서 받아온 아이디 (int 형을 넣어야 함 )
        int firstA = food.size() > 0 ? food.get(0): 0;    // 음식 카테고리 2개 의 아이디 값 들어가야 함

        int secondA = food.size() > 1 ? food.get(1) : 0;

        int firstB = place.size() > 0 ? place.get(0) : 0;   // 장소 카테고리 2개~4개 의 아이디 값 들어가야 함
        int secondB = place.size() > 1 ? place.get(1) : 0;
        int thirdB = place.size() > 2 ? place.get(2) : 0;
        int fourthB = place.size() > 3 ? place.get(3) : 0;
        LOGGER.info("firstB = {}, secondB = {}, thirdB = {}, fourthB = {}", firstB, secondB, thirdB, fourthB);


        // 정해져있는 9개의 B 좌표에서
        // 선택된 2개의 A 좌표 사이의 거리 순서
        double B1_A1 = 0;
        double B2_A1 = 0;
        double B3_A1 = 0;
        double B4_A1 = 0;
        double B5_A1 = 0;
        double B6_A1 = 0;
        double B7_A1 = 0;
        double B8_A1 = 0;
        double B9_A1 = 0;

        double B1_A2 = 0;
        double B2_A2 = 0;
        double B3_A2 = 0;
        double B4_A2 = 0;
        double B5_A2 = 0;
        double B6_A2 = 0;
        double B7_A2 = 0;
        double B8_A2 = 0;
        double B9_A2 = 0;

        // A 값 들어왔을 때 각 B 까지의 거리 순서 표
        switch (firstA) {
            case 1:
                B1_A1 = 4.131;
                B2_A1 = 5.012;
                B3_A1 = 3.111;
                B4_A1 = 2.131;
                B5_A1 = 8.041;
                B6_A1 = 6.021;
                B7_A1 = 7.013;
                B8_A1 = 1.021;
                B9_A1 = 9.231;
                break;

            case 2:
                B1_A1 = 1.253;
                B2_A1 = 5.233;
                B3_A1 = 4.273;
                B4_A1 = 3.273;
                B5_A1 = 8.253;
                B6_A1 = 6.243;
                B7_A1 = 7.273;
                B8_A1 = 2.258;
                B9_A1 = 9.293;
                break;

            case 3:
                B1_A1 = 8.454;
                B2_A1 = 9.435;
                B3_A1 = 5.455;
                B4_A1 = 6.345;
                B5_A1 = 1.425;
                B6_A1 = 3.423;
                B7_A1 = 2.575;
                B8_A1 = 7.435;
                B9_A1 = 4.425;
                break;

            case 4:
                B1_A1 = 7.587;
                B2_A1 = 8.587;
                B3_A1 = 5.647;
                B4_A1 = 6.647;
                B5_A1 = 3.657;
                B6_A1 = 4.677;
                B7_A1 = 2.697;
                B8_A1 = 9.647;
                B9_A1 = 1.667;
                break;

            case 5:
                B1_A1 = 8.839;
                B2_A1 = 9.879;
                B3_A1 = 5.879;
                B4_A1 = 6.839;
                B5_A1 = 4.849;
                B6_A1 = 3.839;
                B7_A1 = 2.839;
                B8_A1 = 7.829;
                B9_A1 = 1.819;
                break;
        }

        switch (secondA) {
            case 1:
                B1_A2 = 4.241;
                B2_A2 = 5.031;
                B3_A2 = 3.301;
                B4_A2 = 2.031;
                B5_A2 = 8.021;
                B6_A2 = 6.011;
                B7_A2 = 7.081;
                B8_A2 = 1.081;
                B9_A2 = 9.081;
                break;

            case 2:
                B1_A2 = 1.263;
                B2_A2 = 5.273;
                B3_A2 = 4.233;
                B4_A2 = 3.253;
                B5_A2 = 8.253;
                B6_A2 = 6.243;
                B7_A2 = 7.253;
                B8_A2 = 2.263;
                B9_A2 = 9.273;
                break;

            case 3:
                B1_A2 = 8.455;
                B2_A2 = 9.465;
                B3_A2 = 5.475;
                B4_A2 = 6.485;
                B5_A2 = 1.495;
                B6_A2 = 3.465;
                B7_A2 = 2.455;
                B8_A2 = 7.445;
                B9_A2 = 4.465;
                break;

            case 4:
                B1_A2 = 7.667;
                B2_A2 = 8.657;
                B3_A2 = 5.667;
                B4_A2 = 6.677;
                B5_A2 = 3.657;
                B6_A2 = 4.677;
                B7_A2 = 2.687;
                B8_A2 = 9.697;
                B9_A2 = 1.677;
                break;

            case 5:
                B1_A2 = 8.869;
                B2_A2 = 9.589;
                B3_A2 = 5.849;
                B4_A2 = 6.869;
                B5_A2 = 4.879;
                B6_A2 = 3.889;
                B7_A2 = 2.899;
                B8_A2 = 7.809;
                B9_A2 = 1.809;
                break;
        }

        double b1 = 0;
        double b2 = 0;
        double b3 = 0;
        double b4 = 0;

        switch (firstB) {
            case 1: b1 = (B1_A1 + B1_A2) / 2; break;
            case 2: b1 = (B2_A1 + B2_A2) / 2; break;
            case 3: b1 = (B3_A1 + B3_A2) / 2; break;
            case 4: b1 = (B4_A1 + B4_A2) / 2; break;
            case 5: b1 = (B5_A1 + B5_A2) / 2; break;
            case 6: b1 = (B6_A1 + B6_A2) / 2; break;
            case 7: b1 = (B7_A1 + B7_A2) / 2; break;
            case 8: b1 = (B8_A1 + B8_A2) / 2; break;
            case 9: b1 = (B9_A1 + B9_A2) / 2; break;
        }

        switch (secondB) {
            case 1: b2 = (B1_A1 + B1_A2) / 2; break;
            case 2: b2 = (B2_A1 + B2_A2) / 2; break;
            case 3: b2 = (B3_A1 + B3_A2) / 2; break;
            case 4: b2 = (B4_A1 + B4_A2) / 2; break;
            case 5: b2 = (B5_A1 + B5_A2) / 2; break;
            case 6: b2 = (B6_A1 + B6_A2) / 2; break;
            case 7: b2 = (B7_A1 + B7_A2) / 2; break;
            case 8: b2 = (B8_A1 + B8_A2) / 2; break;
            case 9: b2 = (B9_A1 + B9_A2) / 2; break;
        }

        switch (thirdB) {
            case 1: b3 = (B1_A1 + B1_A2) / 2; break;
            case 2: b3 = (B2_A1 + B2_A2) / 2; break;
            case 3: b3 = (B3_A1 + B3_A2) / 2; break;
            case 4: b3 = (B4_A1 + B4_A2) / 2; break;
            case 5: b3 = (B5_A1 + B5_A2) / 2; break;
            case 6: b3 = (B6_A1 + B6_A2) / 2; break;
            case 7: b3 = (B7_A1 + B7_A2) / 2; break;
            case 8: b3 = (B8_A1 + B8_A2) / 2; break;
            case 9: b3 = (B9_A1 + B9_A2) / 2; break;
        }

        switch (fourthB) {
            case 1: b4 = (B1_A1 + B1_A2) / 2; break;
            case 2: b4 = (B2_A1 + B2_A2) / 2; break;
            case 3: b4 = (B3_A1 + B3_A2) / 2; break;
            case 4: b4 = (B4_A1 + B4_A2) / 2; break;
            case 5: b4 = (B5_A1 + B5_A2) / 2; break;
            case 6: b4 = (B6_A1 + B6_A2) / 2; break;
            case 7: b4 = (B7_A1 + B7_A2) / 2; break;
            case 8: b4 = (B8_A1 + B8_A2) / 2; break;
            case 9: b4 = (B9_A1 + B9_A2) / 2; break;
        }



        double[] arr = { b1, b2, b3, b4 };

        double min = 999;
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }

        double second = 999;
        int secondIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i == minIndex) { // 가장 작은 값은 패스
                continue;
            }
            if (arr[i] < second) {
                second = arr[i];
                secondIndex = i;
            }
        }

        // 반환을 위해 배열 넘버 에 1 플러스
        minIndex += 1;
        secondIndex += 1;

        LOGGER.info("minIndex = {}", minIndex);
        LOGGER.info("secondIndex = {}", secondIndex);

        //반환할 b값
        int b1result=0;
        int b2result=0;

        //인덱스값, 즉 순서에 맞는 원래 입력된 b 넘버 반환
        switch (minIndex) {
            case 1: b1result = firstB; break;
            case 2: b1result = secondB; break;
            case 3: b1result = thirdB; break;
            case 4: b1result = fourthB; break;	}

        switch (secondIndex) {
            case 1: b2result = firstB; break;
            case 2: b2result = secondB; break;
            case 3: b2result = thirdB; break;
            case 4: b2result = fourthB; break;	}

        model.addAttribute("firstA",firstA);
        model.addAttribute("secondA",secondA);
        model.addAttribute("b1result",b1result);
        model.addAttribute("b2result",b2result);


        String firstA_title="";
        String secondA_title="";
        String b1result_title="";
        String b2result_title="";

        switch (firstA){
            case 1 : firstA_title = "나카스 포장마차 거리"; break;
            case 2 : firstA_title = "사누키 우동 시나리"; break;
            case 3 : firstA_title = "미즈노 오코노미야끼"; break;
            case 4 : firstA_title = "키라쿠 라멘"; break;
            case 5 : firstA_title = "스시잔마이 본점"; break;
        }
        switch (secondA){
            case 1 : secondA_title = "나카스 포장마차 거리"; break;
            case 2 : secondA_title = "사누키 우동 시나리"; break;
            case 3 : secondA_title = "미즈노 오코노미야끼"; break;
            case 4 : secondA_title = "키라쿠 라멘"; break;
            case 5 : secondA_title = "스시잔마이 본점"; break;
        }

        switch (b1result){
            case 1 : b1result_title = "오호리 공원"; break;
            case 2 : b1result_title = "모모치 해변 공원"; break;
            case 3 : b1result_title = "나미하노유 온천"; break;
            case 4 : b1result_title = "카와바타 상점가"; break;
            case 5 : b1result_title = "오사카 도톤부리"; break;
            case 6 : b1result_title = "유니버셜 스튜디오"; break;
            case 7 : b1result_title = "오사카성"; break;
            case 8 : b1result_title = "캐널시티 하카타"; break;
            case 9 : b1result_title = "짱구마을"; break;
        }

        switch (b2result){
            case 1 : b2result_title = "오호리 공원"; break;
            case 2 : b2result_title = "모모치 해변 공원"; break;
            case 3 : b2result_title = "나미하노유 온천"; break;
            case 4 : b2result_title = "카와바타 상점가"; break;
            case 5 : b2result_title = "오사카 도톤부리"; break;
            case 6 : b2result_title = "유니버셜 스튜디오"; break;
            case 7 : b2result_title = "오사카성"; break;
            case 8 : b2result_title = "캐널시티 하카타"; break;
            case 9 : b2result_title = "짱구마을"; break;
        }

        LOGGER.info("firstA_title = {}", firstA_title);
        LOGGER.info("secondA_title = {}", secondA_title);
        LOGGER.info("b1result_title = {}", b1result_title);
        LOGGER.info("b2result_title = {}", b2result_title);
        LOGGER.info("b1result = {}", b1result);
        LOGGER.info("b2result = {}", b2result);



        model.addAttribute("firstA_title",firstA_title);
        model.addAttribute("secondA_title",secondA_title);
        model.addAttribute("b1result_title",b1result_title);
        model.addAttribute("b2result_title",b2result_title);

        // return "redirect:/plan";
        return "plan";
    }

}

