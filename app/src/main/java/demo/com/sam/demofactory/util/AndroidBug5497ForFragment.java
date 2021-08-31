package demo.com.sam.demofactory.util;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


/**
 * 为了修复在全屏幕模式下软键盘挡住输入框问题的解决方案类
 */
public final class AndroidBug5497ForFragment {

    private AndroidBug5497ForFragment() {
    }

    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.
    @Nullable
    public static AndroidBug5497ForFragment assistView(View view) {
        if (view == null) {
            return null;
        }
       return new AndroidBug5497ForFragment(view);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private int defaultHeightDifference = 0;
    private ViewGroup.LayoutParams frameLayoutParams;

    private AndroidBug5497ForFragment(View view) {
        mChildOfContent = view;
        if (mChildOfContent != null) {
            mChildOfContent.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            possiblyResizeChildOfContent();
                        }
                    });
            frameLayoutParams = mChildOfContent.getLayoutParams();
        }
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                if (mChildOfContent.getHeight() == usableHeightNow) {
                    // 部分机型，如 T9 带刘海版本，可以正常 resize ，无须兼容
                    return;
                }
                // keyboard probably just became visible
                mChildOfContent.setPadding(0,0,0, heightDifference - defaultHeightDifference);
                if (callBack != null) {
                    callBack.handleOpenKeyBoard();
                }
            } else {
                // keyboard probably just became hidden
                defaultHeightDifference = heightDifference;// 键盘未显示还不可用的区域是导航栏
                mChildOfContent.setPadding(0,0,0, 0);
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);// 全屏模式下： return r.bottom
    }

    @Nullable
    public CallBack callBack;

    public interface CallBack {
        // 处理打开键盘
        void handleOpenKeyBoard();
    }
}
