package com.tfjybj.english.utils.job;

import java.io.Serializable;

/**
 * remote job handler
 *
 * @author xuxueli 2015-12-19 19:06:38
 */
public interface IBaseJob extends Serializable {
//    void task();

    void synchroCheckWord();

    void ReRank();
}
