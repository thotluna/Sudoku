package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResolverTest {

    ResolverSupport support;

    @BeforeEach
    void setUp() {
        support = new ResolverSupport();
    }

    @Test
    void GiveSolvableGame_WhenResolverCalled_ReturnTrueToSolveBoard(){
        Resolver resolver = new Resolver();
        for (int[][] board: support.getSolvableGame()) {

            assertTrue(resolver.solveBoard(board));
        }

    }

    @Test
    void GiveUnsolvedGame_WhenResolverCalled_ReturnFalseToSolveBoard(){
        Resolver resolver = new Resolver();

        assertFalse(resolver.solveBoard(support.getNotSolvableGame()));
    }
}