package com.tuxt.mytest.algorithm;

import java.util.Deque;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiFunction;

import java.util.*;

enum LOCATION {
    LEFT, RIGHT
}

enum ACTION {
    GO_SELF,
    GO_WITH_WOLF,
    GO_WITH_SHEEP,
    GO_WITH_VEGETABLE,
    BACK_SELF,
    BACK_WITH_WOLF,
    BACK_WITH_SHEEP,
    BACK_WITH_VEGETABLE,
    INVALID
}

class ItemState {
    LOCATION farmer;
    LOCATION wolf;
    LOCATION sheep;
    LOCATION vegetable;
    ACTION curAction;

    public ItemState() {
        farmer = LOCATION.LEFT;
        wolf = LOCATION.LEFT;
        sheep = LOCATION.LEFT;
        vegetable = LOCATION.LEFT;
        curAction = ACTION.INVALID;
    }

    // 将映射定义为静态成员
    private static final Map<LOCATION, String> LocationMap = new EnumMap<>(LOCATION.class);
    private static final Map<ACTION, String> ActMsgMap = new EnumMap<>(ACTION.class);

    static {
        LocationMap.put(LOCATION.LEFT, "Left");
        LocationMap.put(LOCATION.RIGHT, "Right");
        ActMsgMap.put(ACTION.GO_SELF, "Farmer go over the river");
        ActMsgMap.put(ACTION.GO_WITH_WOLF, "Farmer take wolf go over the river");
        ActMsgMap.put(ACTION.GO_WITH_SHEEP, "Farmer take sheep go over the river");
        ActMsgMap.put(ACTION.GO_WITH_VEGETABLE, "Farmer take vegetable go over the river");
        ActMsgMap.put(ACTION.BACK_SELF, "Farmer go back");
        ActMsgMap.put(ACTION.BACK_WITH_WOLF, "Farmer take wolf go back");
        ActMsgMap.put(ACTION.BACK_WITH_SHEEP, "Farmer take sheep go back");
        ActMsgMap.put(ACTION.BACK_WITH_VEGETABLE, "Farmer take vegetable go back");
        ActMsgMap.put(ACTION.INVALID, "");
    }

    public boolean isSameState(ItemState state) {
        return farmer == state.farmer && wolf == state.wolf && sheep == state.sheep && vegetable == state.vegetable;
    }

    public void printStates() {
        System.out.println(ActMsgMap.get(curAction));
    }

    public boolean isFinalState() {
        return farmer == LOCATION.RIGHT && wolf == LOCATION.RIGHT && sheep == LOCATION.RIGHT && vegetable == LOCATION.RIGHT;
    }
}

class ActionProcess {
    ACTION actName;
    BiFunction<ItemState, ItemState, Boolean> takeAction;

    public ActionProcess(ACTION actName, BiFunction<ItemState, ItemState, Boolean> takeAction) {
        this.actName = actName;
        this.takeAction = takeAction;
    }
}

public class FarmerRiverCrossing {
    private static boolean isProcessedState(Deque<ItemState> states, ItemState newState) {
        return states.stream().anyMatch(state -> state.isSameState(newState));
    }

    private static void printResult(Deque<ItemState> states) {
        System.out.println("Find Result : ");
        for (ItemState state : states) {
            state.printStates();
        }
        System.out.println();
    }

    private static boolean isCurrentStateValid(ItemState current) {
        if (current.wolf == current.sheep && current.farmer != current.wolf) {
            return false;
        }

        if (current.vegetable == current.sheep && current.farmer != current.sheep) {
            return false;
        }


        return true;
    }

    private static boolean processFarmerGo(ItemState current, ItemState next) {
        if (current.farmer != LOCATION.LEFT) {
            return false;
        }

        next.farmer = LOCATION.RIGHT;
        next.sheep = current.sheep;
        next.vegetable = current.vegetable;
        next.wolf = current.wolf;
        next.curAction = ACTION.GO_SELF;
        return isCurrentStateValid(next);
    }

    private static boolean processFarmerGoTakeWolf(ItemState current, ItemState next) {
        if (current.farmer != LOCATION.LEFT || current.wolf != LOCATION.LEFT) {
            return false;
        }

        next.farmer = LOCATION.RIGHT;
        next.wolf = LOCATION.RIGHT;
        next.sheep = current.sheep;
        next.vegetable = current.vegetable;
        next.curAction = ACTION.GO_WITH_WOLF;

        return isCurrentStateValid(next);
    }

    private static boolean processFarmerGoTakeSheep(ItemState current, ItemState next) {
        if (current.farmer != LOCATION.LEFT || current.sheep != LOCATION.LEFT) {
            return false;
        }

        next.farmer = LOCATION.RIGHT;
        next.sheep = LOCATION.RIGHT;
        next.wolf = current.wolf;
        next.vegetable = current.vegetable;
        next.curAction = ACTION.GO_WITH_SHEEP;

        return isCurrentStateValid(next);
    }

    private static boolean processFarmerGoTakeVegetable(ItemState current, ItemState next) {
        if (current.farmer != LOCATION.LEFT || current.vegetable != LOCATION.LEFT) {
            return false;
        }

        next.farmer = LOCATION.RIGHT;
        next.vegetable = LOCATION.RIGHT;
        next.wolf = current.wolf;
        next.sheep = current.sheep;
        next.curAction = ACTION.GO_WITH_VEGETABLE;

        return isCurrentStateValid(next);
    }

    private static boolean processFarmerBack(ItemState current, ItemState next) {
        if (current.farmer != LOCATION.RIGHT) {
            return false;
        }

        next.farmer = LOCATION.LEFT;
        next.sheep = current.sheep;
        next.wolf = current.wolf;
        next.vegetable = current.vegetable;
        next.curAction = ACTION.BACK_SELF;
        return isCurrentStateValid(next);
    }

    private static boolean processFarmerBackTakeWolf(ItemState current, ItemState next) {
        if (current.farmer != LOCATION.RIGHT || current.wolf != LOCATION.RIGHT) {
            return false;
        }

        next.farmer = LOCATION.LEFT;
        next.wolf = LOCATION.LEFT;
        next.sheep = current.sheep;
        next.vegetable = current.vegetable;
        next.curAction = ACTION.BACK_WITH_WOLF;

        return isCurrentStateValid(next);
    }

    private static boolean processFarmerBackTakeSheep(ItemState current, ItemState next) {
        if (current.farmer != LOCATION.RIGHT || current.sheep != LOCATION.RIGHT) {
            return false;
        }

        next.farmer = LOCATION.LEFT;
        next.sheep = LOCATION.LEFT;
        next.vegetable = current.vegetable;
        next.wolf = current.wolf;
        next.curAction = ACTION.BACK_WITH_SHEEP;

        return isCurrentStateValid(next);
    }

    private static boolean processFarmerBackTakeVegetable(ItemState current, ItemState next) {
        if (current.farmer != LOCATION.RIGHT || current.vegetable != LOCATION.RIGHT) {
            return false;
        }

        next.farmer = LOCATION.LEFT;
        next.vegetable = LOCATION.LEFT;
        next.wolf = current.wolf;
        next.sheep = current.sheep;
        next.curAction = ACTION.BACK_WITH_VEGETABLE;

        return isCurrentStateValid(next);
    }

    private static final ActionProcess[] actMap = {
            new ActionProcess(ACTION.GO_SELF, FarmerRiverCrossing::processFarmerGo),
            new ActionProcess(ACTION.GO_WITH_WOLF, FarmerRiverCrossing::processFarmerGoTakeWolf),
            new ActionProcess(ACTION.GO_WITH_SHEEP, FarmerRiverCrossing::processFarmerGoTakeSheep),
            new ActionProcess(ACTION.GO_WITH_VEGETABLE, FarmerRiverCrossing::processFarmerGoTakeVegetable),
            new ActionProcess(ACTION.BACK_SELF, FarmerRiverCrossing::processFarmerBack),
            new ActionProcess(ACTION.BACK_WITH_WOLF, FarmerRiverCrossing::processFarmerBackTakeWolf),
            new ActionProcess(ACTION.BACK_WITH_SHEEP, FarmerRiverCrossing::processFarmerBackTakeSheep),
            new ActionProcess(ACTION.BACK_WITH_VEGETABLE, FarmerRiverCrossing::processFarmerBackTakeVegetable)
    };

    public static void searchStates(Deque<ItemState> states) {
        ItemState current = states.getFirst(); // 每次都从当前状态开始
        if (current.isFinalState()) {
            printResult(states);
            return;
        }

        ItemState next = new ItemState();
        for (ActionProcess act : actMap) {
            if (act.takeAction.apply(current, next)) {
                if (!isProcessedState(states, next)) {
                    states.push(next);
                    searchStates(states);
                    states.pop();
                }
            }
        }
    }

    public static void main(String[] args) {
        Deque<ItemState> states = new LinkedList<>();
        ItemState init = new ItemState();
        states.push(init);
        searchStates(states);
    }
}

