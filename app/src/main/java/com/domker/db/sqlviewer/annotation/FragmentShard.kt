package com.domker.db.sqlviewer.annotation

/**
 * Fragment的注解，标识类型
 * Created by Maison on 2017/7/16.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentShard(val type: Int)
