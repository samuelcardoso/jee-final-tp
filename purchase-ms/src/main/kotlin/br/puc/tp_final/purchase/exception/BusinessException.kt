package br.puc.tp_final.purchase.exception

class BusinessException : RuntimeException {

    constructor() : super();

    constructor(msg: String?) : super(msg);

    constructor(msg: String?, cause: Throwable?) : super(msg, cause);
}