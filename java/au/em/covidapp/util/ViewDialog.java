package au.em.covidapp.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import au.em.covidapp.R;

public class ViewDialog {

  //Common functionalities to run dialog in all app
  public static void showDialog(Context mContext, String title, String msg, String DismissText) {
    final Dialog dialog = new Dialog(mContext);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setCancelable(false);
    dialog.setContentView(R.layout.custom_dialog);

    TextView textTitle = dialog.findViewById(R.id.tv_title);
    textTitle.setText(title);

    TextView textDescription = dialog.findViewById(R.id.tv_description);
    textDescription.setText(msg);

    Button dialogDismissButton = dialog.findViewById(R.id.btn_dismiss);
    dialogDismissButton.setText(DismissText);
    dialogDismissButton.setOnClickListener(v -> dialog.dismiss());

    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    dialog.show();
  }

  public static void showDialog(Context mContext, String title, String msg, String DismissText,
      DialogOkAlert dialogConfirmAlert) {
    final Dialog dialog = new Dialog(mContext);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setCancelable(false);
    dialog.setContentView(R.layout.custom_dialog);

    TextView textTitle = dialog.findViewById(R.id.tv_title);
    textTitle.setText(title);

    TextView textDescription = dialog.findViewById(R.id.tv_description);
    textDescription.setText(msg);

    Button dialogDismissButton = dialog.findViewById(R.id.btn_dismiss);
    dialogDismissButton.setText(DismissText);
    dialogDismissButton.setOnClickListener(v -> {
      dialog.dismiss();
      dialogConfirmAlert.onDialogOk();
    });

    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    dialog.show();
  }

  public static void showConfirmDialog(Context mContext, String title, String msg,
      String DismissText, DialogOkAlert dialogNoAlert, String ConfirmText,
      DialogOkAlert dialogYesAlert) {

    final Dialog dialog = new Dialog(mContext);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setCancelable(false);
    dialog.setContentView(R.layout.custom_confirm_dialog);

    TextView textTitle = dialog.findViewById(R.id.tv_title);
    textTitle.setText(title);

    TextView textDescription = dialog.findViewById(R.id.tv_description);
    textDescription.setText(msg);

    Button dialogDismissButton = dialog.findViewById(R.id.btn_dismiss);
    dialogDismissButton.setText(DismissText);
    dialogDismissButton.setOnClickListener(v -> {
      dialog.dismiss();
      dialogNoAlert.onDialogOk();
    });

    Button dialogConfirmButton = dialog.findViewById(R.id.btn_confirm);
    dialogConfirmButton.setText(ConfirmText);
    dialogConfirmButton.setOnClickListener(v -> {
      dialog.dismiss();
      dialogYesAlert.onDialogOk();
    });

    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    dialog.show();
  }

  public interface DialogOkAlert {
    void onDialogOk();
  }

}
