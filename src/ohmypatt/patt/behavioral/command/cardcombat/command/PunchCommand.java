package ohmypatt.patt.behavioral.command.cardcombat.command;

import ohmypatt.patt.behavioral.command.cardcombat.player.Player;

public class PunchCommand implements Command {
    private Player player, enemy;

    public PunchCommand(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void execute() {
        player.punch(enemy);
    }
}

