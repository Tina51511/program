package com.syneart.app.lemontree;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SQLserver {
    private SQLiteDatabase db;
    public static final String DRIVER = "com.sqlsrv.jdbc.Driver";
    public static final String SERVERNAME = "140.131.114.241";
    public static final String USER = "ntub108203";
    public static final String PASS = "@Ntub108203";
    public static final String DATABASE = "108-plantfun";
    public static final String URL = "jdbc:sqlsrv://";
    private static SQLserver per = null;
    private Connection conn = null;
    private Statement stmt = null;

    public static SQLserver createInstance() {
        if (per == null) {
            per = new SQLserver();
            per.initDB();
        }
        return per;
    }

    // 加載驅動
    public void initDB() {
        try {
            Class.forName("com.sqlsrv.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 連接資料庫
    public void connectDB() {
        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(SERVERNAME,USER,PASS);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("SqlManager:Connect to database successful.");
    }

    // 關閉資料庫
    public void closeDB() {
        System.out.println("Close connection to database..");
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Close connection successful");
    }



    //构造方法
    public SQLserver() {

        db = SQLiteDatabase.openDatabase("/data/data/com.example.garden/databases/test.db", null, SQLiteDatabase.OPEN_READWRITE);

    }

    //获取数据库的数据
    public List<QUESTION> getQuestion() {
        List<QUESTION> list = new ArrayList<>();
        //执行sql语句
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            //遍历
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                QUESTION question = new QUESTION();
                //ID
                question.ID = cursor.getInt(cursor.getColumnIndex("Field1"));
                //问题
                question.question = cursor.getString(cursor.getColumnIndex("Field2"));
                //四个选择
                question.answerA = cursor.getString(cursor.getColumnIndex("Field3"));
                question.answerB = cursor.getString(cursor.getColumnIndex("Field4"));
                question.answerC = cursor.getString(cursor.getColumnIndex("Field5"));
                question.answerD = cursor.getString(cursor.getColumnIndex("Field6"));
                //答案
                question.answer = cursor.getInt(cursor.getColumnIndex("Field7"));
                //解析
                question.explaination = cursor.getString(cursor.getColumnIndex("Field8"));
                //设置为没有选择任何选项
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;

    }

}



