package local.itracking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import local.itracking.adapters.RecyclerAdapterUsers;
import local.itracking.configs.Globals;
import local.itracking.helpers.HelperSQLite;
import local.itracking.interfaces.InterfaceRetrofit;
import local.itracking.models.User;
import local.itracking.repositories.RepositoryLogsSecurity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UsersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    //Anokis
    RepositoryLogsSecurity repository;
    Globals globals = new Globals();
    List<User> users;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
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
        final View view =  inflater.inflate(R.layout.fragment_users, container, false);
        //Anokis
        try
        {
            HelperSQLite helperSQLite = new HelperSQLite(getContext(),"iTracking",null,1);
            repository = new RepositoryLogsSecurity();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(globals.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfaceRetrofit services = retrofit.create(InterfaceRetrofit.class);

            services.ListUsersGet().enqueue(new retrofit2.Callback<List<User>>() {
                @Override
                public void onResponse(retrofit2.Call<List<User>> call, retrofit2.Response<List<User>> response) {



                    if(response.body() == null)
                    {
                        Log.d("=iTracking=>", "getUser - No data");
                    }
                    else
                    {
                        List<User> objects = response.body();
                        Log.d("=iTracking=>", "getUser - Users: "+ objects.size());

                        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.RecyclerViewUsers);
                        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                        RecyclerAdapterUsers adapter = new RecyclerAdapterUsers((ArrayList)objects);
                        recycler.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {

                    Log.d("=iTracking=>", t.toString());


                }
            });

        }
        catch (Exception ex)
        {
            Log.d("=iTracking=>",ex.toString());
        }

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

    public void GoToRegisterUserFragment(View view) {

        //Declared in main
    }

    public void RegisterUser(View view) {
        //Declared in main
    }

    public void GoToUpdateUserFragment(View view) {
        //Declared in main
    }

    public void UpdateUser(View view) {
        //Declared in main
    }

}
