package com.wj.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.SimpleArrayMap;
import android.util.Base64;

import com.wj.base.Initialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by wj on 2018/1/9.
 * <p>
 * 借鉴至：Blankj
 * https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/src/main/java/com/blankj/utilcode/util/SPUtils.java
 */

public class SPUtils {

    public static SimpleArrayMap<String, SPUtils> SP_UTILS_MAP = new SimpleArrayMap<>();

    private SharedPreferences sp;

    public static SPUtils getInstance() {
        return getInstance("");
    }

    public static SPUtils getInstance(String spName) {
        if (isSpace(spName)) spName = "spUtils";
        SPUtils spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            spUtils = new SPUtils(spName);
            SP_UTILS_MAP.put(spName, spUtils);
        }
        return spUtils;
    }

    private SPUtils(final String spName) {
        sp = Initialization.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * sp中写入String
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        put(key, value, false);
    }

    /**
     * sp中写入String
     *
     * @param key      键
     * @param value    值
     * @param isCommit 是否使用commit方式提交
     */
    public void put(String key, String value, boolean isCommit) {
        if (isCommit) {
            sp.edit().putString(key, value).commit();
        } else {
            sp.edit().putString(key, value).apply();
        }
    }

    /**
     * sp中读取String
     *
     * @param key 键
     * @return
     */
    public String getString(String key) {
        return getString(key, "");
    }

    /**
     * sp中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * sp中写入int
     *
     * @param key
     * @param value
     */
    public void put(String key, int value) {
        put(key, value, false);
    }

    /**
     * sp中写入int
     *
     * @param key      键
     * @param value    值
     * @param isCommit 是否使用commit方式提交
     */
    public void put(String key, int value, boolean isCommit) {
        if (isCommit) {
            sp.edit().putInt(key, value).commit();
        } else {
            sp.edit().putInt(key, value).apply();
        }
    }

    /**
     * sp中读取int
     *
     * @param key 键
     * @return 没有返回值返回-1
     */
    public int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * sp中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }


    /**
     * sp中写入long
     *
     * @param key
     * @param value
     */
    public void put(String key, long value) {
        put(key, value, false);
    }

    /**
     * sp中写入long
     *
     * @param key      键
     * @param value    值
     * @param isCommit 是否使用commit方式提交
     */
    public void put(String key, long value, boolean isCommit) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit();
        } else {
            sp.edit().putLong(key, value).apply();
        }
    }

    /**
     * sp中读取long
     *
     * @param key 键
     * @return 没有返回值返回-1
     */
    public long getLong(String key) {
        return getLong(key, -1L);
    }

    /**
     * sp中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * sp中写入float
     *
     * @param key
     * @param value
     */
    public void put(String key, float value) {
        put(key, value, false);
    }


    /**
     * sp中写入float
     *
     * @param key      键
     * @param value    值
     * @param isCommit 是否使用commit方式提交
     */
    public void put(String key, float value, boolean isCommit) {
        if (isCommit) {
            sp.edit().putFloat(key, value).commit();
        } else {
            sp.edit().putFloat(key, value).apply();
        }
    }

    /**
     * sp中读取long
     *
     * @param key 键
     * @return 没有返回值返回-1
     */
    public float getFloat(String key) {
        return getFloat(key, -1f);
    }

    /**
     * sp中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * sp中写入boolean
     *
     * @param key
     * @param value
     */
    public void put(String key, boolean value) {
        put(key, value, false);
    }

    /**
     * sp中写入boolean
     *
     * @param key      键
     * @param value    值
     * @param isCommit 是否使用commit方式提交
     */
    public void put(String key, boolean value, boolean isCommit) {
        if (isCommit) {
            sp.edit().putBoolean(key, value).commit();
        } else {
            sp.edit().putBoolean(key, value).apply();
        }
    }

    /**
     * sp中读取boolean
     *
     * @param key 键
     * @return 没有返回值返回false
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * sp中读取boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * sp中写入String集合
     *
     * @param key
     * @param values
     */
    public void put(String key, Set<String> values) {
        put(key, values, false);
    }

    /**
     * sp中写入String集合
     *
     * @param key
     * @param values
     * @param isCommit
     */
    public void put(String key, Set<String> values, boolean isCommit) {
        if (isCommit) {
            sp.edit().putStringSet(key, values).commit();
        } else {
            sp.edit().putStringSet(key, values).apply();
        }
    }

    /**
     * sp中读取String集合
     *
     * @param key
     * @return
     */
    public Set<String> getStringSet(final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * SP 中读取 StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public Set<String> getStringSet(final String key, final Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * sp中写入对象
     *
     * @param key
     * @param values
     */
    public void put(String key, Object values) {
        put(key, values, false);
    }

    /**
     * sp中写入对象
     *
     * @param key
     * @param values
     * @param isCommit
     */
    public void put(String key, Object values, boolean isCommit) {
        put(key, obj2Str(values), isCommit);
    }

    /**
     * sp中读取Object
     *
     * @param key
     */
    public Object getObject(String key) {
        return getObject(key, "");
    }

    /**
     * sp中读取Object
     *
     * @param key
     * @param defaultValue
     */
    public Object getObject(String key, String defaultValue) {
        String str = getString(key, defaultValue);
        return str2Obj(str);
    }

    /**
     * SP 中获取所有键值对
     *
     * @return Map 对象
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * SP 中是否存在该 key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(final String key) {
        return sp.contains(key);
    }

    /**
     * SP 中移除该 key
     *
     * @param key 键
     */
    public void remove(final String key) {
        remove(key, false);
    }

    /**
     * SP 中移除该 key
     *
     * @param key      键
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void remove(final String key, final boolean isCommit) {
        if (isCommit) {
            sp.edit().remove(key).commit();
        } else {
            sp.edit().remove(key).apply();
        }
    }

    /**
     * SP 中清除所有数据
     */
    public void clear() {
        clear(false);
    }

    /**
     * SP 中清除所有数据
     *
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void clear(final boolean isCommit) {
        if (isCommit) {
            sp.edit().clear().commit();
        } else {
            sp.edit().clear().apply();
        }
    }

    /**
     * 对象转字符串
     *
     * @param obj
     * @return
     */
    private String obj2Str(Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        String str = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            str = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
            CloseUtils.closeIO(oos);
            CloseUtils.closeIO(baos);
        }
        return str;
    }

    /**
     * 字符串转对象
     *
     * @param str
     * @return
     */
    private Object str2Obj(String str) {
        if (str != null) {
            byte[] bytes = Base64.decode(str.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = null;
            Object obj = null;
            try {
                ois = new ObjectInputStream(bais);
                obj = ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                CloseUtils.closeIO(ois);
                CloseUtils.closeIO(bais);
            }
            return obj;
        } else {
            return null;
        }
    }
}