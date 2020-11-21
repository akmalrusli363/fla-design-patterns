package ohmypatt.patt.structural.adapter.smartphone.main;

import ohmypatt.patt.structural.adapter.smartphone.adapter.lightning.IPhonePort;
import ohmypatt.patt.structural.adapter.smartphone.adapter.usbc.LightningAdapter;
import ohmypatt.patt.structural.adapter.smartphone.adapter.usbc.SamsungPort;
import ohmypatt.patt.structural.adapter.smartphone.model.samsung.Samsung;

public class Main {
	public Main() {
		Samsung samsungS19 = new Samsung("128GB", "6GB", "4000mAH");
		System.out.println(samsungS19.toString());
		chargeUsingUsbTypeC(samsungS19);
		chargeUsingLightning(samsungS19);
	}

	private void chargeUsingUsbTypeC(Samsung smartphone) {
		SamsungPort samsungPort = new SamsungPort();
		smartphone.charge(samsungPort);
	}

	private void chargeUsingLightning(Samsung smartphone) {
		IPhonePort iPhonePort = new IPhonePort();
		LightningAdapter lightningAdapter = new LightningAdapter(iPhonePort);
		smartphone.charge(lightningAdapter);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
