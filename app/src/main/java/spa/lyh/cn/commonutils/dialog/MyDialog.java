package spa.lyh.cn.commonutils.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import spa.lyh.cn.commonutils.R;
import spa.lyh.cn.lib_utils.dialog.FullDialog;
import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;
import spa.lyh.cn.lib_utils.translucent.statusbar.StatusBarFontColorControler;

public class MyDialog extends FullDialog {
   private TextView is_status_bar;
   private ImageView status_bar_color;

   private TextView is_nav_bar;
   private ImageView nav_bar_color;

   private TextView is_immerse;

   private TextView is_dialog_style;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.pop_test, container, false);
      return view;
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      is_status_bar = view.findViewById(R.id.is_status_bar);
      status_bar_color = view.findViewById(R.id.status_bar_color);
      if (setStatusBarId() != 0){
         is_status_bar.setText(getYesNo(true));
         status_bar_color.setBackground(getContext().getResources().getDrawable(R.color.light_sky_blue));
      }else {
         is_status_bar.setText(getYesNo(false));
      }

      is_nav_bar = view.findViewById(R.id.is_nav_bar);
      nav_bar_color = view.findViewById(R.id.nav_bar_color);
      if (setNavigationBarId() != 0){
         is_nav_bar.setText(getYesNo(true));
         nav_bar_color.setBackground(getContext().getResources().getDrawable(R.color.light_salmon));
      }else {
         is_nav_bar.setText(getYesNo(false));
      }

      is_immerse = view.findViewById(R.id.is_immerse);
      is_immerse.setText(getYesNo(isUIimmerseNavbar()));

      is_dialog_style = view.findViewById(R.id.is_dialog_style);
      if (setStyleId() != 0){
         is_dialog_style.setText(getYesNo(true));
      }else {
         is_dialog_style.setText(getYesNo(false));
      }
   }

   @Override
   public void onResume() {
      super.onResume();
      StatusBarFontColorControler.setStatusBarMode(window,true);//状态栏文字颜色为白色
      NavBarFontColorControler.setNavBarMode(window,true);//导航栏文字颜色为黑色
   }


   private String getYesNo(boolean isYes){
      return isYes?"是":"否";
   }

   //设置dialog样式
   @Override
   public int setStyleId() {
      return 0;
   }

   //设置状态栏ResId
   @Override
   public int setStatusBarId() {
      return R.id.status_bar;
   }

   //设置导航栏ResId
   @Override
   public int setNavigationBarId() {
      return R.id.nav_bar;
   }

   //是否启用三大金刚键导航栏沉浸
   @Override
   public boolean isUIimmerseNavbar() {
      return false;
   }

   //设置dialog的showtag
   @Override
   public String setShowTag() {
      return "MyDialog";
   }

   @Override
   public int setWindowAnimationsThemesId() {
      return 0;
   }
}
