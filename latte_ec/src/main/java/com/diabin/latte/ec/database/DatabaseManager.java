package com.diabin.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * package: com.diabin.latte.ec.database
 * author: chengguang
 * created on: 2019/1/8 上午9:38
 * description: 数据库管理类
 */
public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager(){
    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }


    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }



    private void initDao (Context context) {
        // 创建数据库
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"fast_ex.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();

    }


    public final UserProfileDao getDao(){
        return mDao;
    }
}
