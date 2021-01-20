package ohmypatt.patt.behavioral.command.cardcombat.command;

import java.util.ArrayList;

public class ComboAttackCommand implements Command {
    private ArrayList<Command> attackSets;

    public ComboAttackCommand(ArrayList<Command> attackSets) {
        this.attackSets = attackSets;
    }

    @Override
    public void execute() {
        for (Command attack : attackSets) {
            attack.execute();
        }
    }
}