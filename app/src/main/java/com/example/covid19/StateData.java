package com.example.covid19;

public class StateData {
    String state;
    String confirmState;
String recoverState;
String deathState;

    public StateData(String state, String confirmState,String recoverState, String deathState) {
        this.state = state;
        this.confirmState = confirmState;
        this.recoverState = recoverState;
        this.deathState = deathState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConfirmState() {
        return confirmState;
    }

    public void setConfirmState(String confirmState) {
        this.confirmState = confirmState;
    }
    public String getRecoverState() {
        return recoverState;
    }

    public void setRecoverState(String recoverState) {
        this.recoverState = recoverState;
    }

    public String getDeathState() {
        return deathState;
    }

    public void setDeathState(String deathState) {
        this.deathState = deathState;
    }
}
