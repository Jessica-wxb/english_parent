package com.tfjybj.english.model;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;
@Data
public class UserPetListModel {
    private String petName;
    private String petUrl;
    private List<String> petUrls;
}
