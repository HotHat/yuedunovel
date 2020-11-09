package com.lyhux.yuedunovel.ui.read;


import com.lyhux.yuedunovel.data.ChapterBean;

import java.util.List;

/**
 * Created by Liang_Lu on 2017/12/11.
 */

public interface IBookChapters {
    void bookChapters(List<ChapterBean> ChaptersBean);

    void finishChapters();

    void errorChapters();

}
