// Предусловия нельзя усиливать в ходе наследования

// Если рассмотреть класс Account (общий счет), который имеет метод Pay с ограничением платежа > 0, и его
// подкласс CardAccount (счет пластиковой карточки) и метод Pay с усиленным ограничением по платежу >0 и <10000,
// Т.е. нарушается принцип подстановки - использование CardAccount может привести к ошибке там где можно использовать Account.


// Постусловия нельзя ослаблять в ходе наследования

// Если функция базового класса возвращает сумму овердрафта как процент от счета, а в потомке ослабляется - возвращается любая сумма.
// что недопустимо, т.к. приводит к любым измениям счета.