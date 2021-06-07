package com.example.apptinthethao_java.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class NgayThiDau extends ExpandableGroup<TranDau> {
    public NgayThiDau(String title, List<TranDau> items) {
        super(title, items);
    }
}
