package com.company;

import java.util.ArrayList;
import java.util.Enumeration;

class Base
{
    public static void PrintBases(Iterable<? extends Base> bases)
    {
        for (Base b : bases) { System.out.println(b); }
    }

    public void Method() { System.out.println("Base method"); }
}

class Derived extends Base
{
    @Override
    public void Method() { System.out.println("Derived method"); }
}


public class Main {
    public static void main(String[] args)
    {
        Derived derivedObj = new Derived();
        Base baseObj = derivedObj; // Полиморфизм
        baseObj.Method(); // Derived method

        ArrayList<Derived> dlist = new ArrayList<Derived>();
        Derived.PrintBases(dlist); // Ковариантный тип параментра метода
        Iterable<? extends Base> bIEnum1 = dlist; // Ковариантность
    }
}
