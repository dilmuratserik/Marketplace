package com.example.marketplace.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.marketplace.R;
import com.example.marketplace.activities.ShowAllActivity;
import com.example.marketplace.adapters.CategoryAdapter;
import com.example.marketplace.adapters.NewProductsAdapter;
import com.example.marketplace.adapters.PopularProductsAdapter;
import com.example.marketplace.models.CategoryModel;
import com.example.marketplace.models.NewProductsModel;
import com.example.marketplace.models.PopularProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

  RecyclerView catRecyclerview,newProductRecyclerview,popularProductRecyclerview;
  CategoryAdapter categoryAdapter;
  List<CategoryModel>categoryModelList;
  NewProductsAdapter newProductsAdapter;
  List<NewProductsModel>newProductsModelList;
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel>popularProductsModelList;
    ProgressDialog progressDialog;
    LinearLayout linearlayout;


    TextView catShowAll,popularShowAll,newProductsShowAll;
  //fireStore
  FirebaseFirestore db ;
  public HomeFragment() {

  }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        catRecyclerview=root.findViewById(R.id.rec_category);
        newProductRecyclerview=root.findViewById(R.id.new_product_rec);
        popularProductRecyclerview=root.findViewById(R.id.popular_rec);
        catShowAll = root.findViewById(R.id.category_see_all);
        popularShowAll = root.findViewById(R.id.popular_see_all);
        newProductsShowAll = root.findViewById(R.id.newProducts_see_all);



        db=FirebaseFirestore.getInstance();

        linearlayout=root.findViewById(R.id.home_layout);
        linearlayout.setVisibility(View.GONE);
        progressDialog= new ProgressDialog(getActivity());
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel>slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1,"Discount on phones", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"70", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"70", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);
        progressDialog.setTitle("Welcome to my marketplace");
        progressDialog.setMessage("please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getContext(),ShowAllActivity.class);

            }
        });
        newProductsShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);

            }
        });  popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ShowAllActivity.class);

            }
        });

        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter=new CategoryAdapter(getActivity(),categoryModelList);
        catRecyclerview.setAdapter(categoryAdapter);



        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel=document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearlayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getContext(),newProductsModelList) ;
        newProductRecyclerview.setAdapter(newProductsAdapter );
        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                               newProductsModelList.add(newProductsModel );
                               newProductsAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        popularProductRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductsModelList = new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter(getContext(),popularProductsModelList) ;
        popularProductRecyclerview.setAdapter(popularProductsAdapter );
        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductsModel popularProductsModel = document.toObject(PopularProductsModel.class);
                                popularProductsModelList.add(popularProductsModel );
                                popularProductsAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(),""+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;

    }
}