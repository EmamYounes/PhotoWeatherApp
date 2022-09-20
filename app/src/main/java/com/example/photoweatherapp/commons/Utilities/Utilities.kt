package com.example.photoweatherapp.commons.Utilities

import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


object Utilities {

    fun rxTextViewObservable(view: TextView): Observable<CharSequence>? {
        return RxTextView.textChanges(view).skip(1)
            .debounce(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}


