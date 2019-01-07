package com.diabin.latte.ec.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * package: com.diabin.latte.ec.login
 * author: chengguang
 * created on: 2018/12/19 下午9:03
 * description: 登录页面
 */
public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_pwd)
    TextInputEditText mPwd = null;


    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if(chechForm()) {
//            RestClient.builder()
//                    .url("sign_")
//                    .params("","")
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//
//                        }
//                    })
//                    .build()
//                    .post();

            Toast.makeText(getContext(),"登录成功",Toast.LENGTH_LONG).show();
        }

    }


    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
        Toast.makeText(getContext(),"wechat登录",Toast.LENGTH_LONG).show();
    }


    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        // 去注册
        start(new SignUpDelegate());
    }





    private boolean chechForm() {
        final String email = mEmail.getText().toString();
        final String pwd = mPwd.getText().toString();


        boolean isPass = true;

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            mEmail.setError(null);
        }


        if(pwd.isEmpty()||pwd.length() <6) {
            mPwd.setError("请填写至少6位数密码");
            isPass = false;
        }else {
            mPwd.setError(null);
        }



        return isPass;

    }



    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
