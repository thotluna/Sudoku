package controllers;

import dtos.GameDto;

import java.util.List;

public interface Repository {
    void save(GameDto dto);

    List<String> getListFileName();

    boolean hasFile();

    GameDto load(String fileName);
}
