package com.tfjybj.english.Enum;
import lombok.Getter;

@Getter
public enum PetListEnumUntil {
    PET_DOG("Dog","assets\\pet\\Dog.png"),
    PET_CAT("Cat","assets\\pet\\Cat.png"),
    PET_RABBIT("Rabbit","assets\\pet\\Rabbit.png"),
    PET_SUPER_RABBIT("SuperRabbit","assets\\pet\\SuperRabbit.png"),
    ;


    private String petName;
    private String petUrl;
    PetListEnumUntil(String petName,String petUrl){
        this.petName = petName;
        this.petUrl = petUrl;
    }
}
