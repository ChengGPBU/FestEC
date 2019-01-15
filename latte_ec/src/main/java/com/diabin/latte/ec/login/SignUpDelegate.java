package com.diabin.latte.ec.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.net.RestClient;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.util.log.LatteLogger;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;
import com.orhanobut.logger.Logger;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * package: com.diabin.latte.ec.login
 * author: chengguang
 * created on: 2018/12/19 上午9:45
 * description:  注册的页面 fragment
 */
public class SignUpDelegate extends LatteDelegate {


    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_pwd)
    TextInputEditText mPwd = null;
    @BindView(R2.id.edit_sign_up_re_pwd)
    TextInputEditText mRePwd = null;


    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (true) {
            RestClient.builder()
                    .url("http://10.0.2.2:8080/latte/user.json")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPwd.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            // 日志打印
                            LatteLogger.json("USER_PROFILE", response);
                            //将用户数据持久化写进数据库
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .post();


        }

    }


    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        // 去登录
        start(new SignInDelegate());
    }


    private boolean chechForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String pwd = mPwd.getText().toString();
        final String rePwd = mRePwd.getText().toString();


        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }


        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }


        if (pwd.isEmpty() || pwd.length() < 6) {
            mPwd.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPwd.setError(null);
        }

        if (rePwd.isEmpty() || rePwd.length() < 6 || !(rePwd.equals(pwd))) {
            mRePwd.setError("密码验证错误");
            isPass = false;
        } else {
            mRePwd.setError(null);
        }

        return isPass;

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
