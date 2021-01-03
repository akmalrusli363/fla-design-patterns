package ohmypatt.patt.behavioral.mediator.main;

import ohmypatt.patt.behavioral.mediator.mediator.ChatMediator;
import ohmypatt.patt.behavioral.mediator.mediator.MessageMediator;
import ohmypatt.patt.behavioral.mediator.model.DatabaseAdmin;
import ohmypatt.patt.behavioral.mediator.model.Employee;
import ohmypatt.patt.behavioral.mediator.model.Programmer;
import ohmypatt.patt.behavioral.mediator.model.Research;

public class Main {

	public Main() {
		MessageMediator mediator = new ChatMediator();

		Employee andreas = new Research("Andreas", mediator);
		Employee johan = new Research("Johan", mediator);
		Employee supri = new DatabaseAdmin("Supri", mediator);
		Employee william = new DatabaseAdmin("William", mediator);
		Employee indra = new Programmer("Indra", mediator);

		andreas.sendMessage("Guys.. ku butuh data penting mengenai data mahasiswa kita..!", null, null);
		supri.sendMessage("Ya temans, lagi ku cari data-data mahasiswa kita nih", andreas, "Research");
		indra.sendMessage("Ya nih, biar mahasiswa bisa cek absensi secara mandiri nih :D", null, "Research");
		william.sendMessage("Oh iya, database kita harusnya aman kan?", supri, "Database Admin");
		supri.sendMessage("Aman kok :)", william, "Database Admin");
		william.sendMessage("Programmer kuliku, kami akan usahakan semaksimal mungkin", null, "Programmer");
		johan.sendMessage("Udah dapat belum dari databasenya?", null, "Database Admin");
		supri.sendMessage("Sudah sobat, akan ku bagikan ke kalian semua kok", johan, "Research");
		andreas.sendMessage("Oke siap sobatku :D", null, "Database Admin");
	}

	public static void main(String[] args) {
		new Main();
	}

}
