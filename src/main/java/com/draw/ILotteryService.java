package com.draw;

import java.util.List;

public interface ILotteryService<T> {

    T draw(List<T> awardList) throws DrawException;
}
