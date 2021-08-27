package com.example.appgithub.interfaces

import com.example.appgithub.model.Repo

interface ClickableItem {

    fun onClickGoToDetail(repo: Repo)

}