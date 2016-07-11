package com.silence.autoplayviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Created by wangsai on 2016/4/18.
 */
public abstract class AutoPlayPagerAdapter<V extends View, D> extends PagerAdapter {
    private static final int DEFAULT_ADAPTER_COUNT = Integer.MAX_VALUE;

    private List<V> viewList;

    public List<D> dataList;

    public AutoPlayPagerAdapter(List<D> datas) {
        this.dataList = datas;
        viewList = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return DEFAULT_ADAPTER_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        V view;

        if (viewList.size() != 0) {
            view = viewList.remove(0);
        } else {
            view = createNewItem();
        }
        container.addView(view);

        loadData(view, getItem(position));

        return view;
    }

    int getDefaultPos() {
        return getCount() / 2;
    }

    /**
     * 创建新的条目
     *
     * @return
     */
    protected abstract V createNewItem();

    /**
     * 加载数据
     *
     * @param view
     * @param data
     */
    protected abstract void loadData(V view, D data);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        V view = (V) object;

        container.removeView(view);
        viewList.add(view);
    }


    public D getItem(int viewPosition) {
        return dataList.get(getDataPosition(viewPosition));
    }

    /**
     * 获取实际数据对应的位置
     * @param position adapter的position
     * @return
     */
    public int getDataPosition(int position) {
        int dataSize = dataList.size();

        int offset;
        if (position >= getDefaultPos()) {
            offset = (position - getDefaultPos()) % dataSize;
        } else {
            offset = dataSize - (getDefaultPos() - position) % dataSize;
            if (offset == dataSize)
                offset = 0;
        }

        return offset;
    }
}
