package com.diabin.latte.ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * package: com.diabin.latte.ec.database
 * author: chengguang
 * created on: 2019/1/8 上午9:34
 * description: GreenDao 默认是devOpenHepler 在应用重新启动的时候 会清空数据（我们不需要）
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    //必须先写Entity（UserProfile），才能写这个 （自动生成）
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }


}
