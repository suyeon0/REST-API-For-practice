package com.example.apitest.study_composition;

import java.util.ArrayList;
import java.util.List;

public class LottoComposition {

    protected List<Integer> lottoNumbers;

    public LottoComposition(List<Integer> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public  boolean contains(Integer integer) {
        return this.lottoNumbers.contains(integer);
    }
}
