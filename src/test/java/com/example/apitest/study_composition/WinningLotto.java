package com.example.apitest.study_composition;

import java.util.List;

// 당첨 로또번호 갖고 있는 클래스
public class WinningLotto extends Lotto{

    private final BonusBall bonusBall;

    public WinningLotto(int[] lottoNumbers, BonusBall bonusBall) {
        super(lottoNumbers);
        this.bonusBall = bonusBall;
    }

    // 부모 클래스 변경으로 인해 하위 클래스 모두 변경 필요해짐. --> 상속의 문제점 : 캡슐화 깨진다
//    public WinningLotto(List<Integer> lottoNumbers, BonusBall bonusBall) {
//        super(lottoNumbers);
//        this.bonusBall = bonusBall;
//    }

//    public long compare(Lotto lotto) {
//        return lottoNumbers.stream()
//            .filter(lotto::contains)
//            .count();
//    }


}
