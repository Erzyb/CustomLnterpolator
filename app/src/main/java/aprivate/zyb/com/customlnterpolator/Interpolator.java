package aprivate.zyb.com.customlnterpolator;

import static java.lang.Thread.sleep;

/**
 * Created by zhouyibo on 2017/6/26.
 * 运行时间time   暂停时间  插值间隔时间  是否重复运行  回调
 */

public class Interpolator implements IInterpolator ,Runnable{
    private float offsets = 0;//总偏移量
    private float offset = 0;//添加偏移量
    private int time = 1000;//差值器运行时间
    private boolean restart = false;//是否可循环
    private boolean stop = false;//停止
    private IInterpolator.StatueListener mStatueListener;//进度监听器

    public Interpolator(boolean restart, StatueListener listener) {
        this.restart = restart;
        mStatueListener = listener;
    }

    @Override
    public void setOffset(float f) {//外部添加差值
        offsets += refunc(f);
    }

    @Override
    public float refunc(float f) {//反差值函数
        return f;
    }

    @Override
    public float func(float f) {//差值函数
        return f;
    }

    @Override
    public void start() {//开始运行
        offset = (float) ((float) 10 * 1.0 / (float) time);//设置自动插值值
        stop = true;
        offsets = 0;//总插值重置
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (stop) {
            if (offsets == 0) {
                mStatueListener.startListener();
            }
            offsets += offset;
            mStatueListener.proListener(func(offsets));
            if (offsets >= 1) {
                mStatueListener.endListener();
                stop = false;
            }
            try {
                if (offsets < 1) {
                    sleep(100);
                } else {
                    sleep(4000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (restart && !stop) {
                mStatueListener.reListener();
                stop = true;
                offsets = 0;
            }
        }
    }
}
