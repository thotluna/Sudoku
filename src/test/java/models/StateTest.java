package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    State state;

    @BeforeEach
    void setUp() {
        state = new State();
    }

    @Test
    void GivenTheStart_WhenCalledStateValue_ThenReturnInitialStateValue(){
        assertThat(state.getStateValue(), is(StateValue.INITIAL));
    }

    @Test
    void GivenTheStateValueAny_WhenRestart_ThenStateInitial() {
        state.nextState();
        state.restart();
        assertThat(state.getStateValue(), is(StateValue.INITIAL));
    }

    @Test
    void GivenAnyState_WhenNextStateCalled_ThenChangesState() {
        StateValue initial = state.getStateValue();
        state.nextState();

        assertThat(initial, is(not(state.getStateValue())));
    }

    @Test
    void GivenEndState_WhenNextStateCalled_ThrowAssertError() {
        state.endState();
        assertThrows(AssertionError.class, () -> state.nextState());
    }

    @Test
    void GivenAnyState_WhenEndStateCalled_thenEndState() {
        state.endState();
        assertThat(state.getStateValue(), is(StateValue.OUT_GAME));

        state.restart();
        state.nextState();
        state.endState();
        assertThat(state.getStateValue(), is(StateValue.OUT_GAME));
    }
}