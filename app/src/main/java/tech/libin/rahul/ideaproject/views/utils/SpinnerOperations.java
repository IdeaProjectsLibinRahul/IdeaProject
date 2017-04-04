package tech.libin.rahul.ideaproject.views.utils;

import java.util.List;

import tech.libin.rahul.ideaproject.service.models.SpinnerData;

/**
 * Created by rahul on 3/24/2017.
 */

public  class SpinnerOperations {
    //region findSpinnerElementPosition
    public static String getSpinnerItem(int value, List<SpinnerData> spinnerData) {
        for (SpinnerData spnData : spinnerData) {
            if (spnData.getId() == value) {
                spnData.getValue();
            }
        }
        return "Nill";
    }
    //endregion
}
