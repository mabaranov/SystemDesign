На swift больше уровней доступа: open, public, internal (по умолчанию), fileprivate, private. Вроде как возможно все варианты реализовать.

class A {
    open func method1() { print("Public-Public method class A") }
    private func method2() { print("Private-Private method class A") }
    public func method3() { print("Public-Private method class A") }
    fileprivate func method4() { print("Private-Public method class A") }
    init() {}
}

class B: A {
        override internal func method3() { print("Public-Private method class B") }
        override public func method4() { print("Private-Public method class B") }
}