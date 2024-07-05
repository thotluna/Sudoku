package controllers;

import dtos.GameDto;
import mapper.Mapper;
import models.Game;
import models.Session;

import java.util.List;

public class LoadController implements Controller {
    private final Session session;
    private final Repository repository;

    public LoadController(Session session, Repository repository) {
        this.session = session;
        this.repository = repository;
    }

    @Override
    public void accept(ControllerVisitor view) {
        view.visit(this);
    }

    public List<String> getListFileName() {
        return repository.getListFileName();
    }

    public boolean hasFiles() {
        return repository.hasFile();
    }

    public void load(String fileName) {
        GameDto gameDto = repository.load(fileName);
        Mapper mapper = new Mapper();
        Game game = mapper.fromGameDtoToGame(gameDto);
        session.loadGame(game);
    }

    public void nextState() {
        session.nextState();
    }
}
