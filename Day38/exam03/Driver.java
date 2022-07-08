package Day38.exam03;

public class Driver {

	public void drive(Vehicle vehicle) {
		if (vehicle instanceof Novice) {
			Novice novice = (Novice) vehicle;
			novice.run();
		} else if (vehicle instanceof Expert) {
			Expert expert = (Expert) vehicle;
			expert.run();
		} else if (vehicle instanceof Master) {
			Master master = (Master) vehicle;
			master.run();
		} else {
			vehicle.run();
		}
	}

	public void driverun(Vehicle vehicle) {
		if (vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			bus.runaway();
		} else if (vehicle instanceof Taxi) {
			Taxi texi = (Taxi) vehicle;
			texi.runaway();
		}
		if(vehicle instanceof Novice) {
			Novice novice = (Novice) vehicle;
			novice.runaway();
		}
		else if(vehicle instanceof Expert) {
			Expert expert = (Expert) vehicle;
			expert.runaway();
		}
		else if(vehicle instanceof Master) {
			Master master = (Master) vehicle;
			master.runaway();
		}
	}

	public void driveCheck(Vehicle vehicle) {
		if (vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			bus.checkFare();
		}
		else if (vehicle instanceof Taxi) {
			Taxi texi = (Taxi) vehicle;
			texi.checkFare();
		}
		else if(vehicle instanceof Novice) {
			Novice novice = (Novice) vehicle;
			novice.checkFare();
		}
		else if(vehicle instanceof Expert) {
			Expert expert = (Expert) vehicle;
			expert.checkFare();
		}
		else if(vehicle instanceof Master) {
			Master master = (Master) vehicle;
			master.checkFare();
		}
	}

	public void hipassenter(Vehicle vehicle) {
		if (vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			bus.enter();
			bus.pay();
		}
		if (vehicle instanceof Taxi) {
			Taxi taxi = (Taxi) vehicle;
			taxi.enter();
			taxi.pay();
		}
		else if(vehicle instanceof Novice) {
			Novice novice = (Novice) vehicle;
			novice.enter();
			novice.pay();
		}
		else if(vehicle instanceof Expert) {
			Expert expert = (Expert) vehicle;
			expert.enter();
			expert.pay();
		}
		else if(vehicle instanceof Master) {
			Master master = (Master) vehicle;
			master.enter();
			master.pay();
		}
	}

	public void hipassexit(Vehicle vehicle) {
		if (vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			bus.exit();
		}
		else if (vehicle instanceof Taxi) {
			Taxi taxi = (Taxi) vehicle;
			taxi.exit();
		}
		else if(vehicle instanceof Novice) {
			Novice novice = (Novice) vehicle;
			novice.exit();
		}
		else if(vehicle instanceof Expert) {
			Expert expert = (Expert) vehicle;
			expert.exit();
		}
		else if(vehicle instanceof Master) {
			Master master = (Master) vehicle;
			master.exit();
			master.change();
		}
	}
}