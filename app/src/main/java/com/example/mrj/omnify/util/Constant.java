package com.example.mrj.omnify.util;

import com.example.mrj.omnify.R;

/**
 * Created by MR.J on 9/21/2016.
 */
public class Constant
{
    public static String BASE_URL = "http://www.getomnify.co.za/index.php/servicepage";
    public static String IMAGE_BASE_URL = "http://getomnify.co.za/uploadsimage/";

    public static String SERVICE_LOGIN = "login";
    public static String USER_SIGNUP = "signup";
    public static String COMPAINGNS = "compaigns";
    public static String SETTING_USER = "set_user";
    public static String SETTING_CAT = "set_category";


    public static String PARAM_NAME = "name";
    public static String PARAM_EMAIL = "emailid";
    public static String PARAM_EDDAYSTONE_ID = "uniqueId";

    public static String PARAM_PASS = "pwd";
    public static String USER_ID = "user_id";
    public static String CATEGORY = "category";
    public static String GENDER = "gender";
    public static String BIRTH_DATE = "bod";


    public static int SERVICE_SIGNUP_RESPONSE = 002;
    public static int STOP_ALRAM = 001;
    public static int SERVICE_LOGIN_RESPONSE = 003;
    public static int SERVICE_COMPAINGNS_RESPONSE = 004;
    public static int SERVICE_SETTING_RESPONSE = 005;
    public static int SERVICE_SETTING_RESPONSE_OTHER = 006;



    public static String onSelectXML(String value)
    {
        String xml = "allbutton";
        switch (value)
        {
            case "":
                xml = "allbutton";
                break;
            case "1":
                xml = "secondbutton";
                break;
            case "2":
                xml =  "thirdbutton";
                break;
            case "3":
                xml =  "forthbutton";
                break;
            case "4":
                xml =  "fifthbutton";
                break;
            case "5":
                xml =  "sixthbutton";
                break;
            case "6":
                xml =  "seventhbutton";
                break;
            case "7":
                xml =  "eigthbutton";
                break;
            case "8":
                xml =  "ninethbutton";
                break;
            case "9":
                xml =  "tenthbutton";
                break;
            case "10":
                xml =  "elevenbutton";
                break;
            case "0":
                xml = "allbutton";
                break;
        }

        System.out.println(">> Constant.java >>>>> Constant >>>>> onSelectColor 11111>>>>> "+ xml);
        return xml;
    }

    public static String onSelectHeader(String value)
    {
        String header = "header_0";
        switch (value)
        {
            case "":
                header = "header_0";
                break;
            case "1":
                header = "header_1";
                break;
            case "2":
                header =  "header_2";
                break;
            case "3":
                header =  "header_3";
                break;
            case "4":
                header =  "header_4";
                break;
            case "5":
                header =  "header_5";
                break;
            case "6":
                header =  "header_6";
                break;
            case "7":
                header =  "header_7";
                break;
            case "8":
                header =  "header_8";
                break;
            case "9":
                header =  "header_9";
                break;
            case "10":
                header =  "header_10";
                break;
            case "0":
                header = "header_0";
                break;
        }
        return header;
    }


    public static String onSelectColor(String value)
    {
        System.out.println(">> Constant.java >>>>> Constant >>>>> onSelectColor >>>>> "+value);

        String colour = "ff0000";
        switch (value)
        {
            case "":
                colour = "FF2D67";
                break;
            case "1":
                colour = "7F4B24";
                break;
            case "2":
                colour =  "212121";
                break;
            case "3":
                colour =  "808080";
                break;
            case "4":
                colour =  "0177AD";
                break;
            case "5":
                colour =  "DA1A1A";
                break;
            case "6":
                colour =  "62C0DC";
                break;
            case "7":
                colour =  "784491";
                break;
            case "8":
                colour =  "F38725";
                break;
            case "9":
                colour =  "E75177";
                break;
            case "10":
                colour =  "6FB500";
                break;
            case "0":
                colour = "FF2D67";
                break;
        }

        System.out.println(">> Constant.java >>>>> Constant >>>>> onSelectColor >>>>> "+ colour);
        return colour;
    }

}
