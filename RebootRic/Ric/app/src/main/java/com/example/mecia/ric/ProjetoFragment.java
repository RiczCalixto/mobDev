package com.example.mecia.ric;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjetoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjetoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjetoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProjetoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjetoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjetoFragment newInstance(String param1, String param2) {
        ProjetoFragment fragment = new ProjetoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_projeto, container, false);

        ListView listView = view.findViewById(R.id.listView);
        final ArrayList<String> projetos = new ArrayList<String>(asList(
                "Brain Trainner. Acerte o maior número de contas em apenas 10 sec.",
                "Conversor de moeda. Faça a conversão de real para dolar e dolar para real.",
                "Jogo da Velha",
                "Pomodoro. Após estabelecer o tempo, o app irá emitir um som ao término da contagem.",
                "Leitura dos Artigos disponibilizados no site Hacker News. WebView + SQLite. Pode demorar um pouco pois faz download do site e monta na webView.",
                "Lawyer App. SQLite + RecyclerView. Adicionar, editar e deletar processos."));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, projetos);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==0){
                    Intent intent = new Intent(getActivity(), BrainTrainner.class);
                    startActivityForResult(intent, 0);
                } if (position==1){
                    Intent intent = new Intent(getActivity(), ConversorMoeda.class);
                    startActivityForResult(intent, 0);

                }if (position==2) {
                    Intent intent = new Intent(getActivity(), JogoDaVelha.class);
                    startActivityForResult(intent, 0);
                } if (position==3) {
                    Intent intent = new Intent(getActivity(), TimerApp.class);
                    startActivityForResult(intent, 0);
                } if (position==4) {
                    Intent intent = new Intent(getActivity(), AppNews.class);
                    startActivityForResult(intent, 0);
                } if (position==5) {
                    Intent intent = new Intent(getActivity(), LawyerApp.class);
                    startActivityForResult(intent, 0);
                }


                Toast.makeText(getContext(), projetos.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
