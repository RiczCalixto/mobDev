package io.github.mobileteacher.imagestoragesample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends Fragment {

    RecyclerView recyclerView;

    public ImageListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(
                R.layout.fragment_image_list,
                container, false);

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new PhotoAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

}
