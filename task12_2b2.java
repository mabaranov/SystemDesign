class General implements Serializable {
    void assignment_attempt(General target, General source) {
        if (source instanceof General)
            target = source;
        else
            target = null;
    }
}

class Any extends General {
    void assignment_attempt(Any target, Any source) {
        if (source instanceof Any)
            target = source;
        else
            target = null;
    }
}

final class None extends Any /*A, B, ....*/ { }

//

class Test {
    public static Any getSome() {
        return new None();
    }

    public static void setSome(Any any) {
        if (any instanceof None) {
            System.out.println("wrong value!!!");
        }
    }
}