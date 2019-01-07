package com.diabin.latte.app.util.log;

import com.orhanobut.logger.Logger;

/**
 * package: com.diabin.latte.app.util.log
 * author: chengguang
 * created on: 2019/1/7 上午10:07
 * description: 日志处理类
 */
public class LatteLogger {
    private static final int VERBOSE = 1; // 详细信息  所有日志信息
    private static final int DEBUG = 2;  // debug 日志信息
    private static final int INFO = 3;  // 基本信息
    private static final int WARN = 4; // 警告日志
    private static final int ERROR = 5; // 错误日志
    private static final int NOTHING = 6;

    //控制log等级
    private static int LEVEL = VERBOSE;

    public static void v(String tag, String message) {
        if (LEVEL <= VERBOSE) {
            Logger.t(tag).v(message);
        }
    }

    public static void d(String tag, Object message) {
        if (LEVEL <= DEBUG) {
            Logger.t(tag).d(message);
        }
    }

    public static void d(Object message) {
        if (LEVEL <= DEBUG) {
            Logger.d(message);
        }
    }

    public static void i(String tag, String message) {
        if (LEVEL <= INFO) {
            Logger.t(tag).i(message);
        }
    }

    public static void w(String tag, String message) {
        if (LEVEL <= WARN) {
            Logger.t(tag).w(message);
        }
    }

    public static void json(String tag, String message) {
        if (LEVEL <= WARN) {
            Logger.t(tag).json(message);
        }
    }

    public static void e(String tag, String message) {
        if (LEVEL <= ERROR) {
            Logger.t(tag).e(message);
        }
    }


    }
