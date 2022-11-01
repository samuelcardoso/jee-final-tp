package br.puc.tp_final.track.response

data class Response<T> (
    var erros: ArrayList<String> = arrayListOf(),
    var data: T? = null
)