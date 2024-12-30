/*
package com.example.gplxhanga;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TestActivity extends AppCompatActivity {

    private boolean isToolbarMoved = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_item);

        // Khởi tạo Toolbar và TextView
        Toolbar toolbar = findViewById(R.id.toolbarfirstitem);
        setSupportActionBar(toolbar);

        ConstraintLayout btn = findViewById(R.id.btn_open_test);

        // Xử lý sự kiện khi click vào TextView
        btn.setOnClickListener(v -> {
            // Kiểm tra trạng thái của cờ (isToolbarMoved) để di chuyển Toolbar
            if (isToolbarMoved) {
                // Nếu Toolbar đã di chuyển lên giữa, quay lại vị trí ban đầu
                moveToolbarBack(toolbar);
            } else {
                // Nếu Toolbar chưa di chuyển, di chuyển lên giữa màn hình
                moveToolbarToCenter(toolbar); // Truyền TextView vào hàm
            }
            // Đảo ngược trạng thái của cờ
            isToolbarMoved = !isToolbarMoved;
        });
    }

    // Hàm di chuyển Toolbar lên giữa màn hình với hiệu ứng chậm dần
    private void moveToolbarToCenter(Toolbar toolbar) {
        // Lấy vị trí hiện tại của Toolbar
        float currentTranslationY = toolbar.getTranslationY();

        // Tính toán vị trí giữa màn hình
        int targetY = getResources().getDisplayMetrics().heightPixels / 2 - toolbar.getHeight() / 2;

        // Di chuyển Toolbar từ vị trí hiện tại đến vị trí giữa màn hình với Interpolator
        ObjectAnimator toolbarAnimator = ObjectAnimator.ofFloat(
                toolbar, "translationY", currentTranslationY, -targetY);
        toolbarAnimator.setDuration(500);  // Thời gian hiệu ứng (350ms)
        toolbarAnimator.setInterpolator(new DecelerateInterpolator());  // Hiệu ứng chậm dần

        // Xoay biểu tượng trong TextView có ID icon_test
        TextView iconTextView = findViewById(R.id.icon_test);  // Lấy TextView theo ID
        ObjectAnimator iconAnimator = ObjectAnimator.ofFloat(
                iconTextView, "rotation", 0f, 180f);  // Xoay 180 độ
        iconAnimator.setDuration(300);
        iconAnimator.setInterpolator(new DecelerateInterpolator());  // Hiệu ứng chậm dần

        // Tạo một AnimatorSet để chạy các animation theo thứ tự
        AnimatorSet animatorSet = new AnimatorSet();

        // Chạy toolbarAnimator trước, sau đó iconAnimator
        animatorSet.playSequentially(toolbarAnimator, iconAnimator);

        // Bắt đầu AnimatorSet
        animatorSet.start();
    }


    // Hàm di chuyển Toolbar về vị trí ban đầu
    private void moveToolbarBack(Toolbar toolbar) {
        // Lấy vị trí hiện tại của Toolbar
        float currentTranslationY = toolbar.getTranslationY();

        // Di chuyển Toolbar trở lại vị trí ban đầu (vị trí dưới cùng)
        ObjectAnimator toolbarAnimator = ObjectAnimator.ofFloat(
                toolbar, "translationY", currentTranslationY, 0);
        toolbarAnimator.setDuration(500);
        toolbarAnimator.setInterpolator(new DecelerateInterpolator());  // Hiệu ứng chậm dần

        // Xoay biểu tượng trong TextView có ID icon_test
        TextView iconTextView = findViewById(R.id.icon_test);  // Lấy TextView theo ID
        ObjectAnimator iconAnimator = ObjectAnimator.ofFloat(
                iconTextView, "rotation", 180f, 360f);  // Xoay 180 độ
        iconAnimator.setDuration(300);
        iconAnimator.setInterpolator(new DecelerateInterpolator());  // Hiệu ứng chậm dần

        // Tạo một AnimatorSet để chạy các animation theo thứ tự
        AnimatorSet animatorSet = new AnimatorSet();

        // Chạy toolbarAnimator trước, sau đó iconAnimator
        animatorSet.playSequentially(toolbarAnimator, iconAnimator);

        // Bắt đầu AnimatorSet
        animatorSet.start();
    }
}
*/
