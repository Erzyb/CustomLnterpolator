package aprivate.zyb.com.customlnterpolator;

/**
 * Created by zhouyibo on 2017/6/26.
 */

public interface IInterpolator {
    interface StatueListener {
        //插值开始运行
        void startListener();

        //插值数值反馈
        void proListener(float f);

        //结束插值
        void endListener();

        //重新运行
        void reListener();
    }

    void setOffset(float f);//设置插值

    float refunc(float f);//返插值函数

    float func(float f);//插值函数

    void start();//开始运行
}
