package ohmypatt.patt.behavioral.command.cardcombat.command;

import ohmypatt.patt.behavioral.command.cardcombat.player.Player;

public class KickCommand implements Command {
    private Player player, enemy;

    public KickCommand(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
    }
    
    @Override
    public void execute() {
        player.kick(enemy);
    }
}

