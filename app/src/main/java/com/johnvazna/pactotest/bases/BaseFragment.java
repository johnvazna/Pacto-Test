package com.johnvazna.pactotest.bases;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {

    protected abstract T getViewModel();

    protected void showErrorDialog() {
        BaseDialogFragment dialogFragment = new BaseDialogFragment();
        dialogFragment.show(getChildFragmentManager(), "errorDialog");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().state.observe(getViewLifecycleOwner(), result -> {
            if (result.isError()) {
                showErrorDialog();
            }

            if (result.isLoading()) {
                ((BaseActivity) requireActivity()).showLoading();
            } else {
                ((BaseActivity) requireActivity()).hideLoading();
            }
        });
    }
}
