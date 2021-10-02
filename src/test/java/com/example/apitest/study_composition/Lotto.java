package com.example.apitest.study_composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Lotto {

//    protected List<Integer> lottoNumbers;
//
//    public Lotto(List<Integer> lottoNumbers) {
//        this.lottoNumbers = new ArrayList<>(lottoNumbers);
//    }
//
//    public  boolean contains(Integer integer) {
//        return this.lottoNumbers.contains(integer);
//    }

    // lottoNumbers 타입 변경 진행!

    protected int[] lottoNumbers;

    public Lotto(int[] lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public boolean contains(Integer integer) {
        return Arrays.stream(lottoNumbers)
            .anyMatch(lottoNumber -> Objects.equals(lottoNumber, integer));
    }

}
