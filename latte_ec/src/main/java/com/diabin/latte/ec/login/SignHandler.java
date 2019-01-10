package com.diabin.latte.ec.login;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.diabin.latte.app.AccountManager;
import com.diabin.latte.ec.database.DatabaseManager;
import com.diabin.latte.ec.database.UserProfile;

/**
 * package: com.diabin.latte.ec.login
 * author: chengguang
 * created on: 2019/1/8 上午9:50
 * description: 登录  注册 助手
 */
public class SignHandler {


    //注册 数据解析
    public static void onSignUp(String response, ISignListener listener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");


        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);



        // 已经注册并登陆成功
        AccountManager.setSignState(true);
        listener.onSignInSuccess();
    }

}
