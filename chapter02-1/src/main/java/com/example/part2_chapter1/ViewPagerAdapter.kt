package com.example.part2_chapter1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) :
    FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return WebViewFragment(position, "https://comic.naver.com/webtoon/weekday").apply {
                    listener = mainActivity
                }
            }
            1 -> {
                return WebViewFragment(position, "https://comic.naver.com/genre/bestChallenge").apply {
                    listener = mainActivity
                }
            }
            else -> {
                return WebViewFragment(position, "https://comic.naver.com/genre/challenge").apply {
                    listener = mainActivity
                }
            }
        }
    }
}