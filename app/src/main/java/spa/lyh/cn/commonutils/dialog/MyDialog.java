package spa.lyh.cn.commonutils.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;

import spa.lyh.cn.commonutils.R;
import spa.lyh.cn.lib_utils.dialog.FullDialog;
import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;

public class MyDialog extends FullDialog {

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.pop_test, container, false);
      return view;
   }

   @Override
   public void onResume() {
      super.onResume();
      NavBarFontColorControler.setNavBarMode(window,true);
   }

   @Override
   public int setStyleId() {
      return R.style.CommonDialog;
   }

   @Override
   public int setBackgroundId() {
      return R.id.background;
   }

   @Override
   public int setStatusBarId() {
      return 0;
   }

   @Override
   public int setNavigationBarId() {
      return 0;
   }

   @Override
   public String setShowTag() {
      return "MyDialog";
   }
}
