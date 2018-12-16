package com.diabin.latte.app.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.diabin.latte.app.Latte;
import com.diabin.latte.app.net.callback.IRequest;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * package: com.diabin.latte.app.net.download
 * author: chengguang
 * created on: 2018/12/16 下午2:10
 * description:  下载文件的时候 异步写入到文件中
 *
 * AsyncTask<Object, Void, File>  参数一：异步方法的入参  参数二：加载进度 参数三：异步执行完成的返回值
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest request;
    private final ISuccess success;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.request = request;
        this.success = success;
    }


    /**
     * 在子线程中执行
     * @param params
     * @return
     */
    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        String name = (String) params[3];
        final InputStream inputStream = body.byteStream();
        if(downloadDir == null || downloadDir.equals("")){
            downloadDir = "down_loads";
        }

        if(extension == null || extension.equals("")){
            extension = "";
        }

        if(name == null) {
            // 改方法  会自动生成一个文件名
            return FileUtil.writeToDisk(inputStream,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(inputStream,downloadDir,name);
        }
    }

    /**
     * 执行完异步 在主线程中的操作
     * @param file
     */
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(success != null) {
            success.onSuccess(file.getPath());
        }

        if(request != null) {
            request.onRequestEnd();
        }


        autoInstallApk(file);
    }


    /**
     * 自动安装apk
     * @param file
     */
    private void autoInstallApk(File file) {
        if(FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(intent);
        }
    }
}
