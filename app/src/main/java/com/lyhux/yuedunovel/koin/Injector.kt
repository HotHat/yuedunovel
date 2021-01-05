package com.lyhux.yuedunovel.koin

import com.lyhux.yuedunovel.api.BookApi
import com.lyhux.yuedunovel.api.MockBookApi
import com.lyhux.yuedunovel.data.db.AppDatabase
import com.lyhux.yuedunovel.data.repository.BookshelfRepository
import com.lyhux.yuedunovel.ui.book.BookViewModel
import com.lyhux.yuedunovel.ui.library.LibraryFragmentModel
import com.lyhux.yuedunovel.ui.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object Injector {

    internal val serviceModule: Module = module {
        //BookApi单例注入
        single {
            // KcHttp.createApi<BookApi>(BookApi.BASE_URL)
            MockBookApi() as BookApi
        }
        //AppDatabase注入
        single {
            AppDatabase.getInstance(androidContext())
        }

        single {
            get<AppDatabase>().bookShelfDao()
        }

        single {
            get<AppDatabase>().readingRecordDao()
        }
        single {
            get<AppDatabase>().bookDao()
        }

    }
    /**
     * ！！！注意带参数的viewModel不能直接使用activityViewModel()注入
     */
    internal val viewModels = module {
//        viewModel {
            //使用activityViewModel()注入则是同一个
//            BookTotalViewModel()
//        }
        viewModel {
            LoginViewModel()
        }

        viewModel {
            LibraryFragmentModel()
        }

        viewModel {
            BookViewModel()
        }



    }

}
