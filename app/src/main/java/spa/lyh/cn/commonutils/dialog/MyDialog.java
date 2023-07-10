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
         View statusBar = view.findViewById(setStatusBarId());
         status_bar_color.setBackground(statusBar.getBackground());
      }else {
         is_status_bar.setText(getYesNo(false));
      }

      is_nav_bar = view.findViewById(R.id.is_nav_bar);
      nav_bar_color = view.findViewById(R.id.nav_bar_color);
      if (setNavigationBarId() != 0){
         is_nav_bar.setText(getYesNo(true));
         View navBar = view.findViewById(setNavigationBarId());
         nav_bar_color.setBackground(navBar.getBackground());
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
      StatusBarFontColorControler.setStatusBarMode(window,false);//状态栏文字颜色为白色
      NavBarFontColorControler.setNavBarMode(window,true);//导航栏文字颜色为黑色
   }

   private String getYesNo(boolean isYes){
      return isYes?"是":"否";
   }

   @Override
   public int setStyleId() {
      return R.style.CommonDialog;
   }

   @Override
   public int setStatusBarId() {
      return R.id.status_bar;
   }

   @Override
   public int setNavigationBarId() {
      return R.id.nav_bar;
   }

   @Override
   public boolean isUIimmerseNavbar() {
      return false;
   }

   @Override
   public String setShowTag() {
      return "MyDialog";
   }
}
