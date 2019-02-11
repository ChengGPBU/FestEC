package com.diabin.latte.app.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * package: com.diabin.latte.app.ui.refresh
 * author: chengguang
 * created on: 2019/1/22 上午9:37
 * description:
 */
public class MultipleItemEntity implements MultiItemEntity {

    // 引用队列
    // 当gc（垃圾回收线程）准备回收一个对象时，如果发现它还仅有软引用(或弱引用，或虚引用)指向它，就会在回收该对象之前，把这个软引用（或弱引用，或虚引用）加入到与之关联的引用队列（ReferenceQueue）中。如果一个软引用（或弱引用，或虚引用）对象本身在引用队列中，就说明该引用对象所指向的对象被回收了。
    // 当软引用（或弱引用，或虚引用）对象所指向的对象被回收了，那么这个引用对象本身就没有价值了，如果程序中存在大量的这类对象（注意，我们创建的软引用、弱引用、虚引用对象本身是个强引用，不会自动被gc回收），就会浪费内存。因此我们这就可以手动回收位于引用队列中的引用对象本身。
    // 即在创建引用的同时关联一个引用队列。
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    // 软引用  当JVM内存不足的时候 且 没有 DirectReference（直接引用）的时候才会清除
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);

    public MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }


    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }


    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }



    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T)FIELDS_REFERENCE.get().get(key);
    }



    public final LinkedHashMap<?,?> getFields() {
      return  FIELDS_REFERENCE.get();
    }


    public final MultipleItemEntity  setField(Object key,Object value) {
        FIELDS_REFERENCE.get().put(key,value);
        return this;
    }
}
