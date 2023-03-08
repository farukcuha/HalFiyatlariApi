package com.pandorina.data.local.photo

import com.pandorina.model.Photo

object PhotosDataSource{
    private val photos = listOf(
        Photo(
            "https://i.hizliresim.com/6lld6lj.jpg",
            "Antalya"
        ),
        Photo(
            "https://i.hizliresim.com/5i1o9nq.jpg",
            "Kumluca"
        ),
        Photo(
            "https://i.hizliresim.com/eb5bnoe.jpg",
            "Finike"
        ),
        Photo(
            "https://i.hizliresim.com/hhe8v28.jpg",
            "Serik"
        ),
        Photo(
            "https://i.hizliresim.com/62aadyg.jpg",
            "Demre"
        ),
        Photo(
            "https://i.hizliresim.com/v8h9fle.jpg",
            "Konaklı"
        ),
        Photo(
            "https://i.hizliresim.com/2h9rv7q.jpg",
            "Gazipaşa"
        ),
        Photo(
            "https://i.hizliresim.com/mqr1g2f.jpg",
            "Adana"
        ),
        Photo(
            "https://i.hizliresim.com/tac7vba.jpg",
            "Ankara"
        ),
        Photo(
            "https://i.hizliresim.com/rg9chyq.jpg",
            "İstanbul"
        ),
        Photo(
            "https://i.hizliresim.com/2k0zkns.jpg",
            "Denizli"
        ),
        Photo(
            "https://i.hizliresim.com/6tm7mbc.jpg",
            "Konya"
        ),
        Photo(
            "https://i.hizliresim.com/fryww54.jpg",
            "Trabzon"
        )
    )
    fun getPhoto(): Photo {
         return photos.random()
    }
}