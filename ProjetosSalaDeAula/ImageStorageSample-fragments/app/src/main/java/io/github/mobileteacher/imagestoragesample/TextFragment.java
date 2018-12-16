package io.github.mobileteacher.imagestoragesample;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends Fragment {


    public TextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_text, container, false);
        Bundle args = getArguments();
        if (args != null){
            TextView textView = root.findViewById(R.id.message_text);
            textView.setText(args.getString("text", "XABU"));
        }
        Button button = root.findViewById(R.id.call_camera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mostrar Dialog
                TestDialogFragment dialogFragment = new TestDialogFragment();
                dialogFragment.show(getFragmentManager(), "TestDialogFragment");
            }
        });

        return root;
    }


    public static TextFragment newInstance(String message) {

        Bundle args = new Bundle();

        args.putString("text", message);

        TextFragment fragment = new TextFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
