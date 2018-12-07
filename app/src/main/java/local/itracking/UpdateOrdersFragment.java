package local.itracking;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import local.itracking.adapters.RecyclerAdapterOrders;
import local.itracking.configs.Globals;
import local.itracking.helpers.HelperBase64;
import local.itracking.helpers.HelperSQLite;
import local.itracking.interfaces.InterfaceRetrofit;
import local.itracking.models.Order;
import local.itracking.repositories.RepositoryLogsSecurity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateOrdersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateOrdersFragment extends Fragment {

    String idToUpdate  = "6";
    String phoneToCall = "";
    //Anokis
    RepositoryLogsSecurity repository;
    Globals globals = new Globals();
    List<Order> orders;
    private ImageView photo;
    private ImageView phone;
    private ImageView maps;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UpdateOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateOrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateOrdersFragment newInstance(String param1, String param2) {
        UpdateOrdersFragment fragment = new UpdateOrdersFragment();
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

        final View view = inflater.inflate(R.layout.fragment_update_orders, container, false);

        final Spinner status = view.findViewById(R.id.spiStatus);
        final EditText order = view.findViewById(R.id.pTxtOrder);
        final EditText ruc = view.findViewById(R.id.pTxtRuc);
        final EditText client = view.findViewById(R.id.pTxtClient);
        final EditText source = view.findViewById(R.id.pTxtSource);
        final EditText destination = view.findViewById(R.id.pTxtTarget);
        final EditText date = view.findViewById(R.id.pTxtDate);
        final EditText boxes = view.findViewById(R.id.pTxtBoxes);

        try
        {
            HelperSQLite helperSQLite = new HelperSQLite(getContext(),"iTracking",null,1);
            RepositoryLogsSecurity repository = new RepositoryLogsSecurity();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(globals.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfaceRetrofit services = retrofit.create(InterfaceRetrofit.class);

            services.LoadOrder(idToUpdate).enqueue(new retrofit2.Callback<Order>() {
                @Override
                public void onResponse(retrofit2.Call<Order> call, retrofit2.Response<Order> response) {



                    if(response.body() == null)
                    {
                        Log.d("=iTracking=>", "getUser - No data");
                    }
                    else
                    {
                        final Order objects = response.body();
                        final Order orders = objects ;

                       order.setText(Integer.toString(orders.id));

                        Log.d("=iTracking", Integer.toString(orders.id));
                        ruc.setText(orders.clientId);
                        client.setText(orders.ClientName);
                        source.setText(orders.source);
                        destination.setText(orders.destination);
                        date.setText(orders.date);
                        boxes.setText(orders.boxes);

                        HelperBase64 helperBase64 = new HelperBase64();

          Spinner roleSpinner = view.findViewById(R.id.RoleSpinner);
                        ArrayList<String> roleItems = new ArrayList<String>();
                        roleItems.add("Delivered");
                        roleItems.add("Reprogrammed");

                        ArrayAdapter<CharSequence> statusAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,
                                roleItems
                        );
                        status.setAdapter(statusAdapter);

//ImageView



                        photo = view.findViewById(R.id.btnCam);


                        Log.d("=PHOTOLENGTH)=>",Integer.toString(orders.photo.length()));
                        if(orders.photo.length() < 50)
                        {

                        }
                        else
                        {
                            photo.setImageBitmap(helperBase64.Decode(orders.photo));
                        }

                        photo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,0);
                            }
                        });

                        phoneToCall = orders.phone;
                        phone = view.findViewById(R.id.btnPhone);
                        phone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneToCall, null)));
                            }
                        });


                        maps = view.findViewById(R.id.btnMapsV);
                        maps.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                GoToMaps(v);
                            }
                        });


                    }

                }

                @Override
                public void onFailure(retrofit2.Call<Order> call, Throwable t) {

                    Log.d("=iTracking=>", t.toString());


                }
            });

        }
        catch (Exception ex)
        {
            Log.d("=iTracking=>",ex.toString());
        }
        finally {

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

    public void GoToMaps(View view) {

    }
    public void GoToUpdateOrderFragment(View view) {
        //Declared in main
    }
    public void UpdateOrder(View view) {

        //Declared in main
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        photo.setImageBitmap(bitmap);
    }
}
