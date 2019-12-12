package com.example.miniprojectakthemmalek.model.entities;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Converter {


@TypeConverter
public static String dateToString(Date date)
{

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String strDate= formatter.format(date);
    return strDate;
}

@TypeConverter
public static Date stringToDate(String date)
{
    try
    {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);

    }catch (ParseException e)
    {
        e.printStackTrace();
        return null;
    }

}


}
