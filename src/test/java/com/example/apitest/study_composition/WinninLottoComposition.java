package com.example.apitest.study_composition;


import java.util.Arrays;

// WinningLotto 클래스와 다르게 composition 을 사용한다 (상속대신)
// composition : 부모 역할의 클래스를 구성요소로 사용하는것! = 새로운 클래스를 만들고, private 필드로 기존 클래스의 구성 요소를 사용한다.
public class WinninLottoComposition{

    private LottoComposition lotto;
    private BonusBall bonusBall;

    // 1. 메서드를 호출하는 방식으로 동작하기 때문에 캡슐화를 깨트리지 않는다
    // 2. 부모 클래스 변화로부터 영향이 적음

    public long compare(LottoComposition lotto) {
        return lotto.lottoNumbers.stream()
            .filter(lotto::contains)
            .count();
    }


}
