package com.fullana.tablayoutyviewpager2;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 **/
public class Gallery extends Fragment {

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(new RecyclerAdapter(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        registerForContextMenu(recyclerView);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getTitle().toString()) {
            case "Editar":

                return true;
            case "Eliminar":

                return true;
            case "Compartir":

            default:
                return super.onContextItemSelected(item);
        }
    }

}
class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.GalleryHolder>{
    List<Integer> integers = respo();
    Activity activity ;
    public RecyclerAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    private List<Integer> respo() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.image1);
        list.add(R.drawable.image2);
        list.add(R.drawable.image3);
        list.add(R.drawable.image4);
        list.add(R.drawable.image5);
        list.add(R.drawable.image6);
        list.add(R.drawable.image7);
        list.add(R.drawable.image8);
        list.add(R.drawable.image9);
        return list;
    }

    static class GalleryHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        Activity activity;
        ImageView imageView;
        TextView textView;
        public GalleryHolder(@NonNull View itemView, Activity activity) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.cardID);
            this.activity = activity;
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.contextual_menu, menu);
        }

    }

    @NonNull
    @Override
    public GalleryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_gallery, parent, false);

        return new GalleryHolder(view,activity);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryHolder holder, int position) {
        holder.imageView.setImageResource(integers.get(position));
        holder.textView.setText("CARD"+(position+1));

    }

    @Override
    public int getItemCount() {
        return integers.size();
    }
}