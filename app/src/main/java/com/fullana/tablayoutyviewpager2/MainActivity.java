package com.fullana.tablayoutyviewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.fullana.tablayoutyviewpager2.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.viewPager.setAdapter(new FragmentTabAdapter(this));
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    String[] fragments = {"Tab1","Tab2","Tab3"};
                    tab.setText(fragments[position]);
                }
        ).attach();
        binding.viewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                if (position<-1)
                    page.setAlpha(0);
                else if (position<=0) {
                    page.setAlpha(1);
                    page.setPivotX(page.getWidth());
                    page.setRotationY(-90*Math.abs(position));
                } else if (position<=1) {
                    page.setAlpha(1);
                    page.setPivotX(0);
                    page.setRotationY(90*Math.abs(position));
                }else
                    page.setAlpha(0);
            }
        });
    }

}
class FragmentTabAdapter extends FragmentStateAdapter{

    String[] fragments = {"Tab1","Tab2","Tab3"};
    public FragmentTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new Tab1();
            case 1 : return new Tab2();
            default: return new Gallery();
        }
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}