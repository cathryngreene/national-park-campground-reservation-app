package com.techelevator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.CampgroundDAO;
import com.techelevator.model.ParkDAO;
import com.techelevator.model.ReservationDAO;
import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;
import com.techelevator.model.JDBC.JDBCcampgroundDAO;
import com.techelevator.model.JDBC.JDBCparkDAO;
import com.techelevator.model.JDBC.JDBCreservationDAO;
import com.techelevator.model.JDBC.JDBCsiteDAO;

public class CampgroundCLI {

	// MAIN MENU UI
	private static final String MAIN_MENU_OPTION_1 = "View Parks";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_1, MAIN_MENU_OPTION_EXIT };

	// VIEW PARK INTERFACE
	private ParkDAO parkDAO;
	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";
	private static final String MENU_PARK_1 = "Acadia";
	private static final String MENU_PARK_2 = "Arches";
	private static final String MENU_PARK_3 = "Cuyahoga Valley National Park";
	private static final String[] MENU_PARK_OPTIONS = new String[] { MENU_PARK_1, MENU_PARK_2, MENU_PARK_3,
			MENU_OPTION_RETURN_TO_MAIN };

	// PARK INFORMATION SCREEN
	private static final String PARK_INFO_1 = "View Campgrounds";
	private static final String PARK_INFO_2 = "Search for Reservation **Pending BONUS OPTION**";
	private static final String PARK_INFO_3 = "Return to previous Screen";
	// private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] PARK_INFO_OPTIONS = new String[] { PARK_INFO_1, PARK_INFO_2, PARK_INFO_3 };

	// PARK CAMPGROUND SCREEN
	private CampgroundDAO campgroundDAO;
	private String campName;
	private static final String CAMP_INFO_1 = "Search for Available Reservations";
	private static final String CAMP_INFO_2 = "Return to previous Screen";
	private static final String[] CAMP_INFO_OPTIONS = new String[] { CAMP_INFO_1, CAMP_INFO_2 };

	// CAMPGROUND RESERVE SCREEN
	private SiteDAO siteDAO;
	private Integer siteNumber;
	private static final String CANCEL_RESERVE = "Cancel Transaction";
	private static final String CAMP_DEVIL = "Devil's Garden";
	private static final String CAMP_WREN = "Canyon Wren Group Site";
	private static final String CAMP_JUNIPER = "Juniper Group Site";
	private static final String CAMP_BLACKWOODS = "Blackwoods";
	private static final String CAMP_SEAWALL = "Seawall";
	private static final String CAMP_SCHOODIC = "Schoodic Woods";
	private static final String CAMP_UNNAMED = "The Unnamed Primitive Campsites";

	// RESERVATION DATE
	private ReservationDAO reservationDAO;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	private String today = timeStamp;
	private String reserveName;

	private Scanner input = new Scanner(System.in);
	private Menu menu;
	private Object parkName;
	private Object[] campground_Options;
	private String arrival;
	private String departure;

	// DONE
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	// DONE
	public CampgroundCLI(DataSource datasource) {
		this.menu = new Menu(System.in, System.out);
		parkDAO = new JDBCparkDAO(datasource);
		campgroundDAO = new JDBCcampgroundDAO(datasource);
		siteDAO = new JDBCsiteDAO(datasource);
		reservationDAO = new JDBCreservationDAO(datasource);
	}

	public void run() { // MAIN MENU RUN (format as needed)

		displayApplicationBanner();
		while (true) {

			printHeading("*************");
			printHeading("* Main Menu *");
			printHeading("*************");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_1)) {
				viewParkInterface();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0); // exit the program
			}
		} // end of WHILE

	}// end of MAIN MENU RUN

	protected void viewParkInterface() { // VIEW PARKS INTERFACE (format as needed)

		while (true) {
			printHeading("************************");
			printHeading("* View Parks Interface *");
			printHeading("************************");
			printHeading("\n Select Park for Information" + "\n");

			String choice = (String) menu.getChoiceFromOptions(MENU_PARK_OPTIONS);

			if (choice.equals(MENU_PARK_1)) {
				// System.out.println("Acadia" + " selected");
				parkName = "Acadia";
				parkToCampFilter();
				viewParkInformationScreen();

			} else if (choice.equals(MENU_PARK_2)) {
				// System.out.println("Arches"+ " selected");
				parkName = "Arches";
				parkToCampFilter();
				viewParkInformationScreen();

			} else if (choice.equals(MENU_PARK_3)) {
				// System.out.println("Cuyahoga Valley" + " selected");
				parkName = "Cuyahoga Valley";
				parkToCampFilter();
				viewParkInformationScreen();

			} else if (choice.equals(MENU_OPTION_RETURN_TO_MAIN)) {
				run();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		} // end of WHILE

	}// END OF PARK INTERFACE SCREEN

	private void viewParkInformationScreen() { // PARKS INFORMATION SCREEN (format as needed)
		System.out.println("You selected: " + parkName);
		while (true) {
			printHeading("  ********************************************");
			printHeading("        Park Information for: " + parkName);
			printHeading("  ********************************************");
			parkDAO.parkInformation(parkName); // This method prints selected park information

			String choice = (String) menu.getChoiceFromOptions(PARK_INFO_OPTIONS);
			if (choice.equals(PARK_INFO_1)) { //
				viewCampgroundScreen();

			} else if (choice.equals(PARK_INFO_2)) { // Search for reservations methods

				// reservation search screen method here******BONUS***
			} else if (choice.equals(PARK_INFO_3)) { // Previous screen option
				viewParkInterface();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);

			}
		} // end of WHILE

	}// end of park information menu

	private void viewCampgroundScreen() { // PARK CAMPGROUNDS (format as needed)

		while (true) {
			printHeading("******************************************");
			printHeading("  Campgrounds in: " + parkName);
			printHeading("******************************************\n");
			campgroundDAO.displayCampgrounds(parkName);

			String choice = (String) menu.getChoiceFromOptions(CAMP_INFO_OPTIONS);

			if (choice.equals(CAMP_INFO_1)) { // Search for available reservations
				viewCampReservationScreen();

			} else if (choice.equals(CAMP_INFO_2)) { // Previous screen option
				viewParkInformationScreen();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);

			}

		} // end of WHILE

	}// END OF CAMPGROUND INFORMATION SCREEN

	private Object[] parkToCampFilter() {
		if (parkName == "Arches") {
			String[] CAMP_RESERVE_OPTIONS = { CAMP_DEVIL, CAMP_WREN, CAMP_JUNIPER, CANCEL_RESERVE };
			setCampground_Options(CAMP_RESERVE_OPTIONS);
			return CAMP_RESERVE_OPTIONS;

		} else if (parkName == "Acadia") {
			String[] CAMP_RESERVE_OPTIONS = { CAMP_BLACKWOODS, CAMP_SEAWALL, CAMP_SCHOODIC, CANCEL_RESERVE };
			setCampground_Options(CAMP_RESERVE_OPTIONS);
			return CAMP_RESERVE_OPTIONS;

		} else if (parkName == "Cuyahoga Valley") {
			String[] CAMP_RESERVE_OPTIONS = { CAMP_UNNAMED, CANCEL_RESERVE };
			setCampground_Options(CAMP_RESERVE_OPTIONS);
			return CAMP_RESERVE_OPTIONS;
		}
		return campground_Options;
	}// END OF PARKtoCAMPFILTER

	private void viewCampReservationScreen() {

		printHeading("*********************************************");
		printHeading(" Select a Camp to reserve a site at " + parkName + "");
		printHeading("*********************************************");

		parkToCampFilter();

		String choice = (String) menu.getChoiceFromOptions(campground_Options);
		if (choice.equals(CAMP_DEVIL)) {
			campName = CAMP_DEVIL;
		} else if (choice.equals(CAMP_WREN)) {
			campName = CAMP_WREN;
		} else if (choice.equals(CAMP_JUNIPER)) {
			campName = CAMP_JUNIPER;
		} else if (choice.equals(CAMP_BLACKWOODS)) {
			campName = CAMP_BLACKWOODS;
		} else if (choice.equals(CAMP_SEAWALL)) {
			campName = CAMP_SEAWALL;
		} else if (choice.equals(CAMP_SCHOODIC)) {
			campName = CAMP_SCHOODIC;
		} else if (choice.equals(CAMP_UNNAMED)) {
			campName = CAMP_UNNAMED;
		} else if (choice.equals(CANCEL_RESERVE)) {
			viewCampgroundScreen();
		}
		reservationDate();

	}// END OF CAMPGROUND SELECT METHOD

	protected void reservationDate() {
		boolean a = true;
		while (a) {
			try {

				System.out.println("Select an arrival date to " + campName + " : YYYY-MM-DD");
				arrival = input.nextLine();
				arrivalDate = LocalDate.parse(arrival, formatter);
			} catch (Exception nfe) {
				System.out.println("The date entered, " + arrival + " is invalid.");
				continue;
			}
			break;
		}
		while (a) {
			printHeading("Confirmed arrival date : " + arrival + "\n");
			try {
				System.out.println("Select a departure date from " + campName + " : YYYY-MM-DD");
				departure = input.nextLine();
				departureDate = LocalDate.parse(departure, formatter);
				if (!departureDate.isAfter(arrivalDate)) {
					System.out.println(departure + " is invalid. Depature date must be after" + arrival + "+ \n");
					continue;

				}
			} catch (Exception nfe) {
				System.out.println("The date entered, " + departure + " is invalid.");
				continue;
			}
			break;
		}
		printHeading("Departure date confirmed: " + departure + "\n");
		siteResultsBanner();
		siteDAO.getSiteResults(campName, arrival, departure);
		reservationResultsScreen();
	}// END OF RESERVATION METHOD

	private void reservationResultsScreen() {

		List<Integer> selectSites = siteDAO.returnAvaiableSites();
		printHeading(" Which site should be reserved? ");
		siteNumber = Integer.parseInt(input.nextLine());
		if (selectSites.size() == 0) {
			reservationDate();
		}
		Integer notSite = 0;
		for (Integer i = 0; i < selectSites.size(); i++)
			if (!siteNumber.equals(selectSites.get(i))) {
				notSite++;
			}
		if (notSite == 5) {
	printHeading("Invalid Site selected please try again.");
			reservationResultsScreen();
		}

		finishReservation();
	}

	// END OF SITERESERVATION METHOD

	private void finishReservation() {
		printHeading(" Under what name should the site be reserved? ");
		reserveName = input.nextLine();

		printHeading(" ******************************************");
		printHeading(" Thank you " + reserveName + ".\n " + "\n Confirmation number is "
				+ reservationDAO.makeReservation(siteNumber, reserveName, arrival, departure, today).longValue() + ". "
				+ "\n Duration: " + arrival + " until " + departure + ". \n At site # " + siteNumber + " in beautiful "
				+ parkName + ".");
		printHeading(" ******************************************");
		System.exit(0);
	}
	/* @return the campground_Options */

	public Object[] getCampground_Options() {
		return campground_Options;
	}

	/**
	 * @param campground_Options
	 *            the campground_Options to set
	 */
	public void setCampground_Options(Object[] campground_Options) {
		this.campground_Options = campground_Options;
	}

	private void printHeading(String heading) {
		System.out.printf("%20s", heading + "\n");
	}

	private void siteResultsBanner() {

		printHeading(" ******************************************");
		printHeading("  " + campName + " Search Results:  	   ");
		printHeading(" ******************************************");
	}

	private void displayApplicationBanner() {
		System.out.println("\n" + "\n" + " _   _          _____    ____            _____ \n"
				+ "| \\ | |        | ___ \\  / __ \\          | ___ \\\n"
				+ "|  \\| |        | |_/ / | /  \\/          | |_/ /\n"
				+ "| . ` |        |  __/  | |              |    / \n"
				+ "| |\\  |        | |     | \\__/\\          | |\\ \\ \n"
				+ "|_| \\_|ational |_|ark   \\____/ampground |_| \\_|eservation\n" + "                               \n"
				+ "");
		System.out.println();
	}
}
