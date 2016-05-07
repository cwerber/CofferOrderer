package ftry.backand.first_comp.cofferorderer.GUI;

import android.app.Dialog;

import ftry.backand.first_comp.cofferorderer.Common.IHiddable;

/**
 * Created by User on 4/29/2016.
 */
public class DialogHidder implements IHiddable {
    private Dialog mDialog;

    public DialogHidder(Dialog dialog)
    {
        mDialog=dialog;
    }

    @Override
    public void Hide()
    {
        mDialog.hide();
    }
}
