package com.company;

// Наследование вида, у фотографии несколько критериев: размер, тип бумаги, тип накладываемой коррекции
// выделить главный критерий нельзя и подразумевается активное комбинирование этих критериев

public abstract class FixedSize {}
public class TenByFifteen extends FixedSize {}
public class FifteenByTwenty extends FixedSize {}

public abstract class PaperType {}
public class Glossy extends PaperType {}
public class Matte extends PaperType {}

public abstract class CorrectionType {}
public class CorrectColor extends CorrectionType {}
public class CorrectEyes extends CorrectColor {}

public class Foto
{
    public FixedSize FixedSize;
    public PaperType PaperType;
    public CorrectionType CorrectionType;
}