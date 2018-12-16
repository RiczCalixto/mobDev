package io.github.mobileteacher.imagestoragesample;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ImageNameDialogFragment extends DialogFragment {


    OnPositiveButtonClickListener positiveButtonClickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View root = getActivity().getLayoutInflater()
                        .inflate(R.layout.imagename_dialog_layout, null);

        builder.setView(root)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = root.findViewById(R.id.imagename_field);
                        String imageName = editText.getText().toString();

                        Toast.makeText(getActivity(), imageName, Toast.LENGTH_SHORT).show();
                        if (positiveButtonClickListener != null){
                            positiveButtonClickListener.onPositiveButtonClick(imageName);
                        }

                    }
                });


        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            positiveButtonClickListener = (OnPositiveButtonClickListener) context;
        } catch (ClassCastException exception){

        }

    }
}
