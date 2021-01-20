package ohmypatt.patt.behavioral.command.cardcombat.command;

import ohmypatt.patt.behavioral.command.cardcombat.player.Player;

public class HitCommand implements Command {
    private Player player, enemy;

    public HitCommand(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void execute() {
        player.hit(enemy);
    }
}

