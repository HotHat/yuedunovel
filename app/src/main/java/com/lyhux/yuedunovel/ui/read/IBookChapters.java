package com.lyhux.yuedunovel.ui.read;


import com.lyhux.yuedunovel.data.http.ChapterItemBean;

import java.util.List;

/**
 * Created by Liang_Lu on 2017/12/11.
 */

public interface IBookChapters {
    void bookChapters(List<ChapterItemBean> ChaptersBean);

    void finishChapters();

    void errorChapters();

}
