package com.company;

import java.util.stream.Stream;

public abstract class General
{
    public abstract General Copy(); //-- копирование объекта
    public abstract General Clone(); // -- клонирование объекта
    public abstract boolean Equals(General other); //-- сравнение объектов
    public abstract String Serialize(); //-- сериализация
    public abstract General Deserialize(String obj); //  -- десериализация
    public abstract String toString(); // -- печать
    public abstract boolean IsGeneralType(); //-- проверка типа
    public abstract Class<?> RealTypeOf(); //-- получение реального типа объекта (непосредственного класса, экземпляром которого он был создан).
}

public class Any extends General
{
    @Override public General Clone() { return this.Clone(); }
    @Override public General Copy() { return this.Copy(); }
    @Override public String Serialize() { return this.Serialize().toString(); }
    @Override public General Deserialize(String jsonString) { return Deserialize(jsonString); }
    @Override public boolean Equals(General other) { return this.equals(other); }
    @Override public boolean IsGeneralType() { return this instanceof General; }
    @Override public Class<?> RealTypeOf() { return this.getClass(); }
    @Override public String toString() { return this.toString(); }
}
