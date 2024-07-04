package controllers;

import dtos.GameDto;
import mapper.Mapper;
import models.Session;

public class SaveController {

    private final Session session;
    private final Repository repository;

    public SaveController(Session session, Repository repository) {
        this.session = session;
        this.repository = repository;
    }

    public void saveGame(){
        Mapper mapper = new Mapper();
        GameDto dto = mapper.fromGameToGameDto(session.getGame());
        repository.save(dto);
    }



    public boolean hasGame() {
        return session.hasGame();
    }
}
