package au.em.covidapp.util;

import io.reactivex.disposables.Disposable;

public class RxUtil {

  //Common functionalities to dispose values/fragments in all app
  public static void dispose(Disposable disposable) {
    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }
  }
}
