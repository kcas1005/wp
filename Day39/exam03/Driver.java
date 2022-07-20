package Day39.exam03;

import java.util.Scanner;

public class Driver {

	// 일반 도로 달리는 것
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

	// 고속 도로 달리는 것
	public void driverun(Vehicle vehicle) {
		if (vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			bus.runaway();
		}
		else if (vehicle instanceof Taxi) {
			Taxi texi = (Taxi) vehicle;
			texi.runaway();
		}
		else if(vehicle instanceof Novice) {
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
	// 운전기사 확인하고 요금 내는 것
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
	// 하이패스 입장 하고 돈 내는 것
	public void hipassenter(Vehicle vehicle) {
		if (vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			bus.enter();
			bus.pay();
		}
		else if (vehicle instanceof Taxi) {
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
	//하이패스 나가는 것
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
	public void drrun(Vehicle vehicle){
		// Driver 인스턴스 생성
		Driver dr = new Driver();

		if (vehicle instanceof Bus) {
			Bus bus = (Bus) vehicle;
			//버스 일반도로 운행
			dr.drive(bus);

			//버스 고속도로 운행
			dr.driverun(bus);

			//버스 요금 정검
			dr.driveCheck(bus);

			// 하이패스 입장
			dr.hipassenter(bus);

			// 하이패스 퇴장
			dr.hipassexit(bus);

		}
		/*else if (vehicle instanceof Taxi) {
			Taxi taxi = (Taxi) vehicle;
			taxi.exit();

		}*/
		else if(vehicle instanceof Novice) {
			Novice novice = (Novice) vehicle;

			//버스 일반도로 운행
			dr.drive(novice);

			//버스 고속도로 운행
			dr.driverun(novice);

			//버스 요금 정검
			dr.driveCheck(novice);

			// 하이패스 입장
			dr.hipassenter(novice);

			// 하이패스 퇴장
			dr.hipassexit(novice);
		}
		else if(vehicle instanceof Expert) {
			Expert expert = (Expert) vehicle;

			//버스 일반도로 운행
			dr.drive(expert);

			//버스 고속도로 운행
			dr.driverun(expert);

			//버스 요금 정검
			dr.driveCheck(expert);

			// 하이패스 입장
			dr.hipassenter(expert);

			// 하이패스 퇴장
			dr.hipassexit(expert);

		}
		else if(vehicle instanceof Master) {
			Master master = (Master) vehicle;

				//버스 일반도로 운행
			dr.drive(master);

			//버스 고속도로 운행
			dr.driverun(master);

			//버스 요금 정검
			dr.driveCheck(master);

			// 하이패스 입장
			dr.hipassenter(master);

			// 하이패스 퇴장
			dr.hipassexit(master);
			// 운전자 교체
			master.change();
		}
	}
}