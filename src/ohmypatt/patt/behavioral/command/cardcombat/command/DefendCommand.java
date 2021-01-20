package ohmypatt.patt.behavioral.command.cardcombat.command;

import ohmypatt.patt.behavioral.command.cardcombat.player.Player;

public class DefendCommand implements Command {
    private Player player;

    public DefendCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.defend();
    }
}

