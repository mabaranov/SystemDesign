package com.company;

abstract class General {
    public abstract void add(General rhs);
}

class Vector <T extends General> {
    T[] values;

    public void add(T [] rhv)
    {
        if (values.length != rhv.length )
            return;

        for(int i=0; i<values.length; i++) {
            values[i].add(rhv[i]);
        }
    }
 }