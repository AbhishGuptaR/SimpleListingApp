package com.example.android.simplelistingapp;

public class Entry {
    //String entryTable = "CREATE TABLE entry(name VARCHAR,amount VARCHAR,expensegroup " +
            //"VARCHAR,type VARCHAR,image VARCHAR,time VARCHAR);";
    public static final String TABLE_NAME = "entry";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_AMOUNT = "amount";

    public static final String COLUMN_GROUP = "expensegroup";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_IMAGE = "image";

    public static final String COLUMN_TIME = "time";

    private String name,amount,expensegroup,type,image,time;

    public static final String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_NAME + "VARCHAR," + COLUMN_AMOUNT + "VARCHAR," + COLUMN_GROUP + "VARCHAR," + COLUMN_TYPE + "VARCHAR," + COLUMN_IMAGE + "VARCHAR," + COLUMN_TIME + "VARCHAR" + ")";

    public Entry(){

    }

    public String getName(){
        return name;
    }

    public String getAmount(){
        return amount;
    }

    public String getExpensegroup(){
        return expensegroup;
    }

    public String getType(){
        return type;
    }

    public String getImage(){
        return image;
    }

    public String getTime(){
        return time;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAmount(String amount){
        this.amount= amount;
    }

    public void setExpensegroup(String expensegroup){
        this.expensegroup= expensegroup;
    }

    public void setType(String type){
        this.type= type;
    }

    public void setImage(String image){
        this.image =  image;
    }

    public void setTime(String time){
        this.time= time;
    }

}
